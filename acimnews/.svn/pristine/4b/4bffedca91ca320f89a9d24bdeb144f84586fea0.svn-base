/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.negocios;

import br.com.acimnews.entidades.RevisaoAuditoria;
import org.hibernate.envers.RevisionListener;

/**
 *
 * @author Felipe Marinzeck
 */
public class OuvinteAuditoria implements RevisionListener {

    @Override
    public void newRevision(Object revisaoAuditoria) {
        RevisaoAuditoria ra = (RevisaoAuditoria) revisaoAuditoria;
        ra.setIp(SistemaFacade.obtemIp());
        ra.setUsuario(SistemaFacade.obtemLogin());
    }
}
