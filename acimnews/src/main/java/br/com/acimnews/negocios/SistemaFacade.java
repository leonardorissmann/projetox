/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.negocios;

import java.security.Principal;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Felipe Marinzeck
 */
@Repository
public class SistemaFacade {
        
    public static String obtemLogin() {
        String login = "SEM_LOGIN";
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            Principal principal = facesContext.getExternalContext().getUserPrincipal();
            if (principal != null) {
                login = principal.getName();
            }
        } catch (Exception ex) {
            System.out.println("Impossível determinar o Usuário");
            ex.printStackTrace();
        }
        return login;
    }

    public static String obtemIp() {
        String ip = "Local";
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            ip = httpServletRequest.getRemoteAddr();
        } catch (Exception ex) {
            System.out.println("Impossível determinar o IP");
            ex.printStackTrace();
        }
        return ip;
    }    
}
