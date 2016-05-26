/*
 * Daniel Sacdalan
 * OPL 2016
 * Project 0
 * Last Modified: 2-22-16
 */
package truinginterp;

import static truinginterp.TruingInterp.interp;
import static truinginterp.TruingInterp.lookup;
import static truinginterp.TruingInterp.substitute;

public class tests 
{
     public static boolean tests()
        {
            //Current list of defintions
            int NumDefs = 1;
            String[] List = new String[NumDefs];
            //'myfalse 
            List[0] = "(app 'myfalse (ID 'x) (Value false))";
            
            //Lookup tests
            if ((lookup("'myfalse", List).equals("(app 'myfalse (ID 'x) (Value false))")) == false)
                return false;
            
            //Substitute tests
            String[] what = new String[1];
            String[] OldName = new String[1];
            what[0] = "(Value true)";
            OldName[0] = "(ID 'x)";
            String in = "(Value false)";
            if (substitute(what, OldName, in).equals("(Value false)") == false)
                return false;
            
            what[0] = "(Value true)";
            OldName[0] = "(ID 'x)";
            in = "(ID 'x)";
            if (substitute(what, OldName, in).equals("(Value true)") == false)
                return false;
            
            what[0] = "(Value true)";
            OldName[0] = "(ID 'x)";
            in = "(NAND (Value true) (ID 'x))";
            if (substitute(what, OldName, in).equals("(NAND (Value true) (Value true)") == false)
                return false;
            
            what[0] = "(Value true)";
            OldName[0] = "(ID 'x)";
            in = "(app 'notnot (ID 'x)";
            if (substitute(what, OldName, in).equals("(app 'notnot (Value true))") == false)
                return false;
                    
            //interp tests
            //Current list of defintions
            NumDefs = 1;
            List = new String[NumDefs];
            //'myfalse 
            List[0] = "(app 'myfalse (ID 'x) (Value false))";
            if (interp("(Value true)", List) != true)
                return false;
            
            if (interp("(Value false)", List) != false)
                return false;
            
            if (interp("(NAND (Value true) (Value true))", List) != false)
                return false;
            
            if (interp("(app 'myfalse (Value true)", List) != false)
                return false;
            
            //Only reaches if all tests passed
            return true;
        }
    
}
