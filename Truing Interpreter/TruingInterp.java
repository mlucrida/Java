/*
 * Author: Daniel Sacdalan
 * Reviewed by: Matt Lucrida
 * OPL 2016
 * Project 0
 * Last Modified: 2-22-16
 */
package truinginterp;
import java.util.Scanner;

public class TruingInterp
{
    //Substitutes values in a definition
    public static String substitute(String[] what, String[] OldName, String in)
    {
        if (in.equals("(Value false)"))
            return in;
        else if (in.equals("(Value true)"))
            return in;
        //If in is an ID
        else if (in.substring(0, 3).equals("(ID"))
        {
            //ERROR HERE
            //Can only handle apps with one argument
            //Same issue as Daniel Sacdalan's HW2
            int OldNameLength = OldName.length;
            String[] Answer = new String[OldNameLength];
            for (int i = 0; i < OldNameLength; i++)
            {
                //Check each WHAT strings and see if there is a match for any IN
                int WhatLength = what.length;
                for (int j = 0; j < WhatLength; j ++)
                {
                    if (in.equals(OldName[i]))
                        Answer[i] = what[j];
                    return what[j];
                }
                return in;
            }
        }
        else if (in.substring(0, 5).equals("(NAND"))
        {
            //Parse the string to find all the arguments.
            String arguments = in.substring(6, in.length()-1);

            //Loop finds the end of the first argument
            int Open = 0;
            int Closed = 0;
            int counter = 0;
            do{
                if(arguments.substring(counter, counter+1).equals("("))
                    Open++;
                if(arguments.substring(counter, counter+1).equals(")"))
                    Closed++;
                counter++;

            }while(Open != Closed);

            String Arg0 = arguments.substring(0, counter);
            String Arg1 = arguments.substring((counter + 1), arguments.length());

            return "(NAND "+substitute(what, OldName, Arg0)+" "+substitute(what, OldName, Arg1);
        }
        else if (in.substring(0, 4).equals("(app"))
        {
            int InLength = in.length();
            int Def = in.indexOf("(", 1);

            //"(app 'NAME"
            String LeftSide = in.substring(0, (Def - 1));
            //Body
            String RightSide =in.substring(Def, (InLength - 1));

            return LeftSide + " " + substitute(what, OldName, RightSide) + ")";
        }
        else
            throw new IllegalArgumentException("Invalid input: "+in);

        //SHOULD NEVER REACH THIS POINT
        return "Invalid input: " +in;
    }

    //Finds the definition for a name if it exists
    public static String lookup(String name, String[] list)
    {
        int NameLength = name.length();
        int ListLength = list.length;

        //Loop over the array of defintions
        //until the correct defintion is found
        for (int i = 0; i < ListLength; i++)
            if (name.equals(list[i].substring(5, (NameLength + 5))))
                return list[i];

        //Only reaches this if no definition exists
        throw new IllegalArgumentException("No definition for: "+name);
    }

    //Evaluates a Truing AST
    public static boolean interp(String arg, String[] list)
    {
        if (arg.equals("(Value false)"))
            return false;
        else if (arg.equals("(Value true)"))
            return true;
        else if (arg.substring(0, 3).equals("(ID"))
            throw new IllegalArgumentException("Free Variable: "+arg.substring(4, (arg.length() - 1)));
        //NAND
        else if (arg.substring(0, 5).equals("(NAND"))
        {
            //Parse the string to find all the arguments.
            String arguments = arg.substring(6, arg.length()-1);

            //Loop finds the end of the first argument
            int Open = 0;
            int Closed = 0;
            int counter = 0;
            do{
                if(arguments.substring(counter, counter+1).equals("("))
                    Open++;
                if(arguments.substring(counter, counter+1).equals(")"))
                    Closed++;
                counter++;

            }while(Open != Closed);

            String Arg0 = arguments.substring(0, counter);
            String Arg1 = arguments.substring((counter + 1), arguments.length());

            //NOT (Arg0 AND Arg1)
            return !((interp (Arg0, list) & (interp (Arg1, list))));
        }
        //app
        else if (arg.substring(0, 4).equals("(app"))
        {
            //Get function name
            //Only used to get definition
            int EndName = arg.indexOf(" ", 5);
            String CallName = arg.substring(5, EndName);

            //Get defintion
            String Def = lookup(CallName, list);

            //Get arguments of definition
            int Open = 0;
            int Closed = 0;
            //Parse the string to find all the arguments.
            String DefArg = Def.substring(EndName + 1);

            //Loop finds the end of the first argument
            int counter = 0;
            do{
                if(DefArg.substring(counter, counter+1).equals("("))
                    Open++;
                if(DefArg.substring(counter, counter+1).equals(")"))
                    Closed++;
                counter++;

            }while(Open != Closed);
            String Arg0 =DefArg.substring(0, counter);

            //Get body of defintion
            String Body = Def.substring((EndName + counter + 2), (Def.length() - 1));
            //Arg paramater
            String Param = arg.substring((EndName + 1), (arg.length() - 1));

            String[] what = new String[1];
            what[0] = Param;
            String[] OldName = new String[1];
            OldName[0] = Arg0;

            return interp(substitute(what, OldName, Body), list);
        }
        else
           throw new IllegalArgumentException("Invalid Input");
    }

    public static void main(String[] args)
    {
        //Run all tests
        boolean PassedTests = false;
        if (tests.tests())
            PassedTests = true;

        //If all tests passed
        //run program
        while (PassedTests == true)
        {
            //Get user input
            Scanner input = new Scanner(System.in);
            String Userinput;
            System.out.println("Enter TruingExpr: ");
            Userinput = input.nextLine();

            //Current list of defintions
            int NumDefs = 1;
            String[] List = new String[NumDefs];
            List[0] = "(app 'myfalse (ID 'x) (Value false))";
            System.out.println("Answer: "+interp(Userinput, List));
        }

        System.out.println("NOT ALL TESTS PASSED");
    }
}
