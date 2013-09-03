/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.acimnews.seguranca;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe Marinzeck
 */
public class Seguranca {

    private static MessageDigest md = null;

    // Método estático para geração do algoritmo de criptografia
    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Seguranca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** Criptografa a Senha
     * @param pwd String A senha normal
     * @return String A senha criptografada
     */    
    public static String criptografar(String pwd){
        if (md != null){
            try {
                return new String(hexCodes(md.digest(pwd.getBytes("UTF-8"))));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Seguranca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    private static char[] hexCodes(byte[] text) {
        char[] hexOutput = new char[text.length * 2];
        String hexString;

        for (int i = 0; i < text.length; i++){
            hexString = "00" + Integer.toHexString(text[i]);
            hexString.toUpperCase().getChars(hexString.length() - 2, hexString.length(), hexOutput, i*2);
        }
        return hexOutput;
    }
}
