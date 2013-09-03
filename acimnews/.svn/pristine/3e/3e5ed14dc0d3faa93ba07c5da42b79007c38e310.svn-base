/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 *
 * @author Felipe Marinzeck
 */
public class Util {

    /**
     * Redireciona o usuário para uma determinada URL.
     *
     * @param url indica a url na qual o sistema será redirecionado.
     */
    public static void redirecionarParaURL(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Este método recupera o local de origem de implantação do sistema
     *
     * @return uma String com o local de origem (sem barras antes ou depois) ex:
     * 'localorigem' ou 'local'
     */
    public static String getRequestContextPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }

    /**
     * Este método recupera o local de origem de implantação do sistema e redireciona o usuário
     * para a URL designada no parâmetro.
     * Método a ser utilizado nos redirecionamentos internos.     
     */
    public static void redirecionamentoInterno(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(getRequestContextPath()+url);
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
