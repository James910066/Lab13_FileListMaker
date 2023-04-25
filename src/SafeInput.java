import java.util.Scanner;

public class SafeInput
{
    //Part A: getNonZeroLenString
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String returnString = "";  // Initializing this variable to zero length.
        do //Start of User input loop
            {
                System.out.print("\n" +prompt + ": "); // Adds new line in output and shows prompt to user
                returnString = pipe.nextLine(); //Set user input to Variable
            }
        while(returnString.length() == 0); //Check length of user input. Loop continues if length = 0
        return returnString; //Value returned to the program
    }

    //Part B: getInt
    public static int getInt(Scanner pipe, String prompt)
    {   //Declaration and assignment of variables
        int returnValue = 0;
        String trash = "";
        boolean done = false;

        do //Start of User input loop
            {
            System.out.println("\n" + prompt + ": "); // Adds new line in output and shows prompt to user
                if(pipe.hasNextInt()) //Start here if user entered an integer (legit value)
                    {
                        returnValue = pipe.nextInt(); //Set user input to Variable
                        pipe.nextLine(); //Clear the buffer
                        done = true; //End loop by changing boolean variable
                    }
                else //Run this block if user input is invalid
                    {
                        trash = pipe.nextLine(); //Assignment of bad input from user
                        System.out.println("You entered " + trash + ". " + "You must enter an int "); //Output statement of invalid input
                    }
            }
        while (!done); //Keep looping for correct input if this is false
        return  returnValue; //Value returned to the program
    }

    //Part C: getDouble
       public static double getDouble(Scanner pipe, String prompt)
    {   //Declaration and assignment of variables
        double returnValue = 0;
        boolean done = false;
        String trash = "";
        do //Start of User input loop
        {
            System.out.println("\n" + prompt + ": "); // Adds new line in output and shows prompt to user
            if(pipe.hasNextDouble()) //Start here if user entered an double (legit value)
                {
                    returnValue = pipe.nextDouble(); //Set user input to Variable
                    pipe.nextLine(); //Clearing the buffer
                    done = true; //End loop by changing boolean variable
                }
            else //Run this block if user input is invalid
                {
                    trash = pipe.nextLine(); //Assignment of bad input from user
                    System.out.println("You entered " + trash + ". " + "You must enter a double "); //Output statement of invalid input
                }
        }while(!done); //Keep looping for correct input if this is false
        return  returnValue; //Value returned to the program
    }
    //Part D: getRangedInt
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {   //Declaration and assignment of variables
        int result = 0;
        boolean done = false;
        String trash = "";
        do //Start of User input loop
        {
            // Code and control logic to loop until validated
            System.out.print(prompt + "[" + low + " - " + high + "]: "); //Prompt user for a low and high value
            if(pipe.hasNextInt()) //Start here if user entered an integer (legit value)
            {
                result = pipe.nextInt(); //Assign user input to variable
                pipe.nextLine(); // clear input buffer
                if(result >= low && result <= high) //Check if input is within correct range
                {
                    done = true; //End loop for correct input
                }
                else //Run this block if user input is invalid
                {
                    System.out.println("You must enter a value in range [" + low + " - " + high + "]: " + result); //Output statement of invalid input
                }
            }
            else //Run this block if user input is invalid
            {
                trash = pipe.nextLine(); //Assignment of bad input from user
                System.out.println("You entered " + trash + ". " + "You must enter an int [" + low + " - " + high + "] " ); //Output statement of invalid input
            }

        }while(!done); //Keep looping for correct input if this is false
        return result; //Value returned to the program
    }
    //Part E: getRangedDouble
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high)
    {   //Declaration and assignment of variables
        double returnValue = 0;
        boolean done = false;
        String trash = "";
        do //Start of User input loop
        {
            // Code and control logic to loop until validated
            System.out.print("\n" + prompt + "[" + low + " - " + high + "]: "); //Prompt user for a low and high value
            if(pipe.hasNextDouble()) //Start here if user entered an double (legit value)
            {
                returnValue = pipe.nextDouble(); //Assign user input to variable
                pipe.nextLine(); // clear input buffer
                if(returnValue >= low && returnValue <= high) //Check if input is within correct range
                {
                    done = true; //End loop for correct input
                }
                else //Run this block if user input is invalid
                {
                    System.out.println("\nNumber is out of range [" + low + " - " + high + "]: " + returnValue); //Output statement of invalid input
                }
            }
            else //Run this block if user input is invalid
            {
                trash = pipe.nextLine(); //Assignment of bad input from user
                System.out.println("You entered " + trash + ". " + "You must enter a double "); //Output statement of invalid input
            }
        }while(!done); //Keep looping for correct input if this is false
        return returnValue; //Value returned to the program
    }

    //Part F: getYNConfirm
    public static boolean getYNConfirm(Scanner pipe, String prompt)
    {   //Declaration and assignment of variables
        boolean returnValue = true;
        String response = "";
        boolean gotValue = false;

        do //Start of User input loop
        {
            System.out.println("\n" + prompt + " [Y/N] "); //Prompt the user for input
            response = pipe.nextLine(); //Assign user input to variable
            if(response.equalsIgnoreCase("Y")) //Check for exact user input ignoring case sensitivity for Yes
                {
                    returnValue = false; //Change boolean value to false
                    break; //Jump out of checking user input loop to return value to program
                }
            else if(response.equalsIgnoreCase("N")) //Check for exact user input ignoring case sensitivity for No
                {
                    gotValue = true; //Change boolean value to end loop asking user for input
                }
            else //Run this block if user input is invalid
                {
                    System.out.println("You must answer [Y/N]! " + response); //Output statement of invalid input
                }
        }while(!gotValue); //Keep looping for input if this is false
        return returnValue; //Value returned to the program
    }

    //Part G: getRegExString
    public static String getRegExString(Scanner pipe, String prompt, String regExPattern)
    {   //Declaration and assignment of variables
        String response = "";
        boolean gotValue = false;

        do //Start of User input loop
        {
            System.out.println("\n" + prompt + ": "); //Add new line and prompt user for input
            response = pipe.nextLine(); //Assign user input to variable
            if(response.matches(regExPattern)) //Check user input for pattern match
                {
                    gotValue = true; //Change boolean value when pattern is matched
                }
            else //Run this block if user input is invalid
                {
                    System.out.println("\n" + "You entered " + response + "." + " You must match the pattern like " + regExPattern); //Output statement of invalid input
                    System.out.println("Please try again!"); //Output Statement
                }
        }while(!gotValue); //Keep looping for correct input if this is false
        return response; //Value returned to the program
    }

    //Part H Pretty Header
    public static String PrettyHeader(Scanner pipe, String prompt)
    {
        //Declaration of variables
        Scanner in = new Scanner(System.in);
        final int CHAR_COUNT = 60; //Maximum header width
        int leftSpaces;
        int rightSpaces;
        String message = ""; //Initializing header message
        final int MAX_LENGTH = 54; //Maximum length message can be for header
        String trash = "";
        boolean inLengthRange = false;
        String response = "";

        do {
            System.out.println("\n" + prompt + ": "); //Add new line and prompt user for input
            //System.out.println(" Enter your header message here. Must be " + MAX_LENGTH + " characters or less ");
            message = in.nextLine();
            if (message.length() <= MAX_LENGTH) //Message is in length range
                {
                    inLengthRange = true;
                    System.out.println("Your message is " + message);
                    int totalSpaces = CHAR_COUNT - 6 - message.length(); //Calculation of spaces between stars and the message

                    if (totalSpaces % 2 == 0) //testing for even spaces using modulo, this makes it exactly centered!
                    {
                        leftSpaces = rightSpaces = totalSpaces / 2; //this division by two gives me side spaces
                    }
                    else //odd, this is a problem and my message won't be exactly centered, but oh well
                    {
                        rightSpaces = totalSpaces / 2;  //division by two for space on either side of message
                        leftSpaces = rightSpaces + 1;  //this could be switched, I just chose left to have one more space
                    }

                    {   //Outer loop for first line of stars
                        for (int row = 0; row < 1; row++) //Loops once for one line of stars
                        {
                            for (int col = 0; col < 60; col++) //Loops until all stars are printed per count of outer loop
                            {
                                System.out.print("*"); //Output of stars for line
                            }
                            System.out.println(); //Go to next line for start of 2 row
                        }
                        //Inner loop for middle line with message
                        for (int row2 = 0; row2 < 1; row2++) //Loops once for line with message
                        {
                            for (int col2 = 0; col2 < 3; col2++) //Loops 3 times
                            {
                                System.out.print("*"); //Output of first 3 stars for line 2
                            }
                            for (int ch = 0; ch < leftSpaces; ch++) //Loops until leftSpace count is met
                                System.out.print(" "); //Output of spaces base on count of leftSpaces

                            System.out.print(message);  //Output header message
                            for (int ch = 0; ch < rightSpaces; ch++) //Loops until rightSpace count is met
                                System.out.print(" "); //Output of spaces based on count of rightSpaces

                            //Loop for last 3 stars of row 2
                            for (int col2 = 0; col2 < 3; col2++) //Loops 3 times
                            {
                                System.out.print("*"); //Output of last 3 stars for line 2
                            }
                            System.out.println(); //Go to next line for start of 3 row
                        }
                        //Outer loop for last line of stars
                        for (int row3 = 0; row3 < 1; row3++) //Loops once for single line
                        {
                            for (int col3 = 0; col3 < 60; col3++) //Loops for the entire length of the line
                            {
                                System.out.print("*"); //Output of stars for line
                            }
                        }
                    }
                }
                else //This runs when user input is too long for header
                    {
                        trash = in.nextLine(); //Assign bad user input to variable
                        System.out.println("Too long of a message. You must enter a message in length up to " + MAX_LENGTH + " characters"); //Output statement of bad input to user
                    }
        } while (!inLengthRange); //Keeps looping for correct length of input
        return response; //Returns the value to program
    }
}
