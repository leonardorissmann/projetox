/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.controle;

import br.com.acimnews.util.Util;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Felipe Marinzeck
 */
@Controller
@Scope("request")
public class UtilControlador implements Serializable {

    public final static Locale localeBrasil = new Locale("pt_BR_", "pt", "BR");
    public final static SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy", localeBrasil);
    public final static SimpleDateFormat sdfHoraMinuto = new SimpleDateFormat("HH:mm", localeBrasil);
    public final static SimpleDateFormat sdfHoraMinutoSegundo = new SimpleDateFormat("hh:mm:ss", localeBrasil);
    public final static SimpleDateFormat sdfTimeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", localeBrasil);

    public UtilControlador() {
    }

    public String converterTimeStampEmString(Date dataVez) {
        try {
            return sdfTimeStamp.format(dataVez);
        } catch (Exception e) {
            return "Erro ao converter data.";
        }
    }

    public String converterDateEmString(Date dataVez) {
        if (dataVez == null) {
            return " - ";
        }
        try {
            return sdfDate.format(dataVez);
        } catch (Exception e) {
            return "Erro ao converter data.";
        }
    }

    public static void redirecionarParaURL(String url) {
        url = Util.getRequestContextPath() + url;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Converte um boolean em sim ou não
     * @param valor indica o parâmetro a ser verificado
     * @return uma String com Sim ou não, para verdadeiro ou falso respectivamente
     */
    public String booleanSimNao(Boolean valor){
        return valor == true ? "Sim":"Não";
    }
    
    
    /**
     * Converte o estado do usuário, para que seja possível a pesquisa na tabela do primefaces
     * @param valor indica se o usuário está ou não ativo
     * @return uma string com um nome mais adequado ao usuário
     */    
    public String booleanAtivoInativo(Boolean valor){
        return valor == true ? "Ativo":"Inativo";
    }
}