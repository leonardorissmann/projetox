/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.negocios;

import br.com.acimnews.entidades.Usuario;
import br.com.acimnews.supers.SuperFacade;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Felipe Marinzeck
 */
@Repository
public class UsuarioFacade extends SuperFacade<Usuario> {

    @PersistenceContext(unitName = "acimnewsPU")
    private EntityManager em;

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
//    private ResultadoValidacao validarRegrasNegocio(Object obj, ResultadoValidacao resultado) {
//        Usuario usuario = (Usuario) obj;
//
//        if (usuario.getSenha().trim().length() <= 5) {
//            resultado.addErro("Impossível continuar.", "A senha deve conter no mínimo 6 caracteres.");
//        }
//
//        if (!usuario.getSenha().equals(usuario.getConfirmacaoSenha())) {
//            resultado.addErro("Impossível continuar.", "A senha digitada não é igual a senha de confirmação.");
//        }
//
//        // Novo
//        if (usuario.getId() == null) {
//            if (recuperarUsuarioLogin(usuario.getLogin()) != null) {
//                resultado.addErro("Impossível continuar.", "Já existe um usuário cadastrado com o login " + usuario.getLogin());
//            }
//        }
//
//        if (resultado.isValidado()) {
//            usuario.setSenha(Seguranca.criptografar(usuario.getSenha()));
//        }
//        
//        return resultado;
//    }

    @Transactional
    public Usuario recuperarUsuarioLogin(String login) {
        String hql = "from Usuario usu where usu.login = :login";
        Query q = getEntityManager().createQuery(hql);
        q.setMaxResults(1);
        q.setParameter("login", login);
        try {
            return (Usuario) q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
