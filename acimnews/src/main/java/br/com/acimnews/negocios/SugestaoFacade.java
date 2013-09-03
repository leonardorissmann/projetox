/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.negocios;

import br.com.acimnews.entidades.Sugestao;
import br.com.acimnews.supers.SuperFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Felipe Marinzeck
 */
@Repository
public class SugestaoFacade extends SuperFacade<Sugestao> {

    @PersistenceContext(unitName = "acimnewsPU")
    private EntityManager em;

    public SugestaoFacade() {
        super(Sugestao.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Transactional
    @Override
    public List<Sugestao> listar() {
        String hql = "from Sugestao s order by s.enviadaEm desc";
        Query q = getEntityManager().createQuery(hql);

        return q.getResultList();
    }
}
