/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.negocios;

import br.com.acimnews.entidades.Categoria;
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
public class CategoriaFacade extends SuperFacade<Categoria> {

    @PersistenceContext(unitName = "acimnewsPU")
    private EntityManager em;

    public CategoriaFacade() {
        super(Categoria.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Transactional
    @Override
    public List<Categoria> listar() {
        String hql = "from Categoria cat order by cat.titulo asc";
        Query q = getEntityManager().createQuery(hql);

        return q.getResultList();
    }
}
