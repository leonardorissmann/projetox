/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.negocios;

import br.com.acimnews.entidades.Notificacao;
import br.com.acimnews.supers.SuperFacade;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Renato Romanini
 */
@Repository
public class NotificacaoFacade extends SuperFacade<Notificacao> {

    @PersistenceContext(unitName = "acimnewsPU")
    private EntityManager em;

    public NotificacaoFacade() {
        super(Notificacao.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }    
    
    @Transactional
    public Notificacao recuperarNotificacaoAtiva() {
        String hql = "from Notificacao n where n.ativa = true";
        Query q = em.createQuery(hql);
        
        try{
            return (Notificacao) q.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    // TODO
//    public ResultadoValidacao validarCampos(Object obj) {
////        ResultadoValidacao resultado = super.validarCampos(obj);
//        ResultadoValidacao resultado = new ResultadoValidacao();
//        if (((Notificacao) obj).getAtiva()) {
//            if (existeNotificaoAtiva()) {
//                resultado.addErro("Erro ao tentar salvar.", "Já existe uma notificação ativa.");
//            }
//        }
//        return resultado;
//    }

    @Transactional
    public boolean existeNotificaoAtiva() {
        Query sql = em.createNativeQuery("select * from Notificacao where ativa = 1 ");
        if (sql.getResultList().isEmpty()) {
            return false;
        }
        return true;
    }
}
