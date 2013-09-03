/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.negocios;

import br.com.acimnews.entidades.Arquivo;
import br.com.acimnews.supers.SuperFacade;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Felipe Marinzeck
 */
@Repository
public class ArquivoFacade extends SuperFacade<Arquivo> {

    @PersistenceContext(unitName = "acimnewsPU")
    private EntityManager em;

    public ArquivoFacade() {
        super(Arquivo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
