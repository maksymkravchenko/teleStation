/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.util;

/**
 *
 * @author MAX
 */
public class Utility {
    
    //checks for number format exception
    public static Boolean checkIfDigit(String str) {
        if(str == null) {
            return false;
        }
        return str.matches("^-?\\d+$");
    }
}
