import java.io.*;
import java.util.Scanner; //Import of Scanner
import java.util.ArrayList; //Import of Array list utility
import java.nio.file.Files; //Imports file. Uses built in methods for working the actual files and directories and creating input and output streams
import java.nio.file.Path; //Imports the path. Needed for creation of hierarchy of file path. Provides info about file location, size, type and permissions etc.
import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JFileChooser;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class FileListMaker

{
    private  static ArrayList<String> list = new ArrayList<>(); //Creation of global dynamic variable array list called list
    private static String item = ""; //Creation of global dynamic variable item initialized to nothing
    private static int toDelete; //Creation of global dynamic variable for user's numeric list choice for deletion
    private static ArrayList<String> lines = new ArrayList<>(); //Array used to put lines in that are read from the chosen file

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in); //Scanner utility for user input
        final String MENU = "A - Add; D - Delete;Q - Quit; O - Open; S - Save; C - Clear; V - View"; //Display of menu choice options for user
        boolean done = false; //Variable used for main program loop
        String input = ""; //Variable for user menu input
        boolean finalConfirm = false; //Used for confirmation on asking user to quit program
        boolean unSaved = false; //Keep track of changes that need saving
        JFileChooser fileChooser = new JFileChooser(); //Built in class method used to show dialog box of a directory with files to choose from
        File chosenFile; //Variable used for the selected file to be read
        String readLine = ""; //Empty variable to store  the lines of the file into
        boolean saveChanges = false; //Used to flip between a change in the array list or not

        do //Start of program loop
        {
            input = SafeInput.getRegExString(in, MENU, "[AaDdPpQqOoSsCcVv]"); //Call method and display choice options for user to use from menu
            input = input.toUpperCase(); //Convert users menu choice to upper case

            try
            {
                switch (input) //Used to execute users choice
                {
                    case "A": //Menu choice for Add
                        item = SafeInput.getNonZeroLenString(in, "Enter item to add to list"); //Call method for user to enter item. Check for an actual value.
                        lines.add(item); //Call built in method to add user's item to list
                        unSaved = true; //Use for file having a change that hasn't been saved yet
                        break; //Jump out of loop
                    case "D": //Menu choice for Delete
                        displayList(); //display the list of items that can be deleted
                        toDelete = SafeInput.getRangedInt(in, "Pick the number of an item to delete", 1, lines.size()) - 1; //Call method for User's choice of item to delete. Subtract 1 to match index
                        lines.remove(toDelete); //Call built in method to delete item from array list
                        System.out.println("Here is your Updated List"); //Message to user that current list display is the modified version
                        unSaved = true; //Use for file having a change that hasn't been saved yet
                        displayList(); //Display updated list after delete
                        break; //Jump out of loop
                    case "V": //Menu choice for printing list to screen
                        displayList(); //Show the list of items to user
                        break; //Jump out of switch
                    case "Q": //Menu choice for Quiting the program
                        if(unSaved == true) //Run this block when changes have been detected
                        {
                           saveChanges = SafeInput.getYNConfirm(in,"You have file changes that haven't been saved. Would you like to save?"); //Prompt user yes or no on saving changes before quiting
                           if(saveChanges == false) //Run this block when user decides to save their changes
                           {
                               System.out.println("You chose to save file"); //Output to user
                               break; //Break out of loop to choose save option from menu
                           }
                           else //Run this block when user wants to quit and not save any changes
                           {
                               finalConfirm = SafeInput.getYNConfirm(in, "Are you sure?"); //Prompt user one more time to confirm they do want to quit program
                               if (!finalConfirm) //Boolean to determine if user quits program
                               {
                                   System.out.println("You chose not to save file and quit"); //Output to user
                                   System.exit(0); //Call Exit method with no error message
                               }
                           }
                            break; //Jump out of switch
                        }
                        else //Block runs when no changes on list have been detected
                        {
                            finalConfirm = SafeInput.getYNConfirm(in, "Are you sure?"); //Prompt user one more time to confirm they do want to quit program
                            if (!finalConfirm) //Boolean to determine if user quits program
                            {
                                System.exit(0); //Call Exit method with no error message
                            }
                        }
                    case "O": //Opens the file and  writes data into the array for user to work with
                        File workingDirectory = new File(System.getProperty("user.dir")); //Use file class and built in method to set as current working directory
                        fileChooser.setCurrentDirectory(workingDirectory); //Force user to choose file from working directory initially.
                        if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) //This block executes if user picks a file. If not then goes to Else block
                        {
                            chosenFile = fileChooser.getSelectedFile(); //Call method to get the file that users chooses
                            Path file = chosenFile.toPath(); //Build the path to the chosen file
                            InputStream stream = new BufferedInputStream(Files.newInputStream(file, CREATE)); //Create stream with Files class using variable "in" to send it through the pipe
                            BufferedReader fileReader = new BufferedReader(new InputStreamReader(stream)); //Create bufferedReader object used to read the lines of the file using InputStream variable as parameter
                            while(fileReader.ready()) //Start of loop to read the file. If buffer is not empty or not at end of file, then this keeps executing
                            {
                                readLine = fileReader.readLine(); //Line is read and stored in variable
                                lines.add(readLine);  //read the line from the variable and write into the array
                            }
                            fileReader.close(); // Closes the file after it has been read and flush the buffer. Prevents file from being locked
                            System.out.println("\n\nData file has been opened and read into the array!"); //Output to user to notify that their file has been read completely
                            System.out.println(lines); //Show file data stored in the array
                        }
                        else //This runs when user doesn't choose a file
                        {
                            System.out.println("Failed to choose a file to process"); //Output to user that they did not pick a file
                            System.out.println("Run the program again!");//Output to user
                            System.exit(0); //Exit program with no error
                        }
                        break; //Jump out of switch
                    case "S": //Saves changes in array to the file
//                        if(fileChooser == null) //User is creating a new file
//                        {
//                            System.out.println("Saving a new file");
//                        }
//                        else //User is modifying an existing file
//                        {
                            chosenFile = fileChooser.getSelectedFile(); //Call method to get the file that users chooses
                            Path fileSaved = chosenFile.toPath(); //Build the path to the chosen file
                            OutputStream out = new BufferedOutputStream((Files.newOutputStream(fileSaved, CREATE))); //Used to write all bytes at once instead of individually.
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out)); //Efficient way to write all text to the output stream
                            for(String rec : lines) //Loop through all lines of the file
                            {
                                writer.write(rec, 0, rec.length()); //Write to end of the file
                                writer.newLine(); //Adds new line to file
                            }
                            writer.close(); // Closes the file after it has been read and flush the buffer. Prevents file from being locked
                            System.out.println("File has been saved"); //Output message to user
                            unSaved = false; //Reset variable when changes have been saved
                            break; //Jump out of switch
                        //}
                    case "C": //This block will wipe the data out of the array
                        lines.clear(); //Call method to delete data from array
                        System.out.println("The list has been cleared"); //Output to user that all data has been wiped from file
                        displayList(); //Displays current changes in the array list
                        unSaved = true; //Use for file having a change that hasn't been saved yet
                        break; //Jump out of switch
                    default:
                }
            }
            catch (FileNotFoundException e) //Catch block if file pick was not available. This really isn't necessary if user is choosing files from dialog box that show that they exists. This may be helpful if someone else removes file during the choosing process
            {
                System.out.println("File was not found!"); //Output to user that the file they chose does not exists
                e.printStackTrace(); //Return error message
            }
            catch (IOException e) //Catch block
            {
                e.printStackTrace(); //Return error message
            }
        } while(!done); //Determines if program continues or not based on user response
    }
    private static void displayList() //Method to show user list of items in the array list
    {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++"); //Used for cosmetic purposes for display
        if (lines.size() != 0) //Execute if list have items in it
        {
            for (int i = 0; i < lines.size(); i++) //For loop to iterate through the array list to the end based on size
            {
                System.out.println(i + 1 + ":" + lines.get(i)); //Display each list item by adding 1 to index value which gives user numeric choice for each item
            }
        }
        else //Execute this block if no items in user list
            System.out.println("+++              List is empty                  +++"); //Used for cosmetic purposes for display and show user that they have no items in their list
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++"); //Used for cosmetic purposes for display
    }
    //Add item with numeric choice value
    private static String addListItem(String item) //Method for adding an item to array list
    {
        Scanner in = new Scanner(System.in); //Scanner for feeding user input into method
        list.add(item); //Built in method for adding item to array list
        return item; //Returns value back to main program
    }
    //Method to remove item from array list
    private static int deleteListItem(int toDelete) //Method for removing item from array list
    {
        list.remove(toDelete); //Calls built in method to remove item from list
        return toDelete; //Returns value back to main program
    }

}