/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author sinan.rassam
 */
public class Utils {
    
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static boolean isValid(Object objs[]) {
        boolean isValid = true;
        for (int i = 0; i < objs.length; i++) {
            if (objs[i] != null) {
                if (objs[i] instanceof String) {
                    boolean isEmpty = ((String) objs[i]).isEmpty();
                    if (!isEmpty) {
                        return true;
                    }
                }
            } else {
                return false;
            }
        }
        return isValid;
    }
}
