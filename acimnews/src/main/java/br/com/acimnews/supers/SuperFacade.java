/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.supers;

import br.com.acimnews.exceptions.CampoObrigatorioException;
import br.com.acimnews.util.FacesUtil;
import br.com.acimnews.util.Persistencia;
import br.com.acimnews.util.anotacoes.Cadastravel;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Felipe Marinzeck
 */
public abstract class SuperFacade<T> {

    /**
     * Instancia do logador
     */
    protected static final Logger logger = Logger.getLogger("br.com.acimnews.negocios.SuperFacade");
//    @PersistenceContext(unitName="acimnewsPU")
//    protected EntityManager em;
    /**
     * Atributo que representa a classe da entidade
     */
    private Class<T> classe;

    /**
     * Deve ser executado no construtor da subclasse para indicar qual é a
     * classe da entidade
     *
     * @param classe Classe da entidade
     */
    public SuperFacade(Class<T> classe) {
        this.classe = classe;
    }

    public Class<T> getClasse() {
        return classe;
    }

    /**
     * Método abstrato que deve ser implementado para retornar a instância do
     * EntityManager atual.
     *
     * @return EntityManager atual
     */
//    protected EntityManager getEntityManager(){
//        System.out.println("Veio pegar o entityManager..: "+em.hashCode());
//        return em;
//    }
    protected abstract EntityManager getEntityManager();

    /**
     * Salva a entidade através do método persist
     *
     * @param entity Entidade a ser salva
     */
    @Transactional
    public void inserir(T entity) {
        try {
            preSaveOrUpdate((SuperEntidade) entity);
            getEntityManager().persist(entity);
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Problema ao inserir");
        }
    }

    /**
     * Salva a entidade através do método merge
     *
     * @param entity Entidade a ser salva
     */
    @Transactional
    public void alterar(T entity) {
        try {
            preSaveOrUpdate((SuperEntidade) entity);
            getEntityManager().merge(entity);
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Problema ao inserir", ex);
        }
    }

    /**
     * Remove a entidade. Este método recupera a entidade com o método recuperar
     * e em seguida exclui a entidade. Isto é necessário pois a entidade deve
     * ser recuperada e excluída dentro da mesma trasação. Caso não seja
     * possível recuperar a entidade, por exemplo devido a uma exclusão recente,
     * o método não faz nada.
     *
     * @param entity Entidade a ser salva
     */
    @Transactional
    public void excluir(T entity) {
        try {
            Object chave = Persistencia.getId(entity);
            Object obj = recuperar(chave);
            if (obj != null) {
                preRemove(obj);
                getEntityManager().remove(obj);
            }
        } catch (ClassCastException cce) {
            logger.log(Level.SEVERE, "Problema ao excluir objeto. Provavelmente o objeto que você está tentando excluir não é subclasse de " + SuperEntidade.class.getSimpleName(), cce);
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Problema ao excluir", ex);
        }
    }

    @Transactional
    public T recarregar(T entity) {
        try {
            if (entity == null) {
                return null;
            }

            Object chave = Persistencia.getId(entity);
            if (chave == null) {
                return entity;
            }
            Object obj = recuperar(chave);
            return (T) obj;
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Problema ao Recarregar", ex);
            return null;
        }
    }

    /**
     * Retorna a entidade do facade com uma determinada chave
     *
     * @param id chave da entidade
     * @return a entidade recuperada ou null caso não exista entidade com tal
     * chave.
     */
    @Transactional
    public T recuperar(Object id) {
        try {
            return getEntityManager().find(classe, id);
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Problema ao Recuperar", ex);
            return null;
        }
    }

    /**
     * Recupera uma entidade específica a partir da chave. Se não encontrar
     * retorna null
     *
     * @param entidade classe da entidade
     * @param id valor da chave
     * @return entidade recuperada ou null se não encontrar
     */
    @Transactional
    public Object recuperar(Class entidade, Object id) {
        try {
            return getEntityManager().find(entidade, id);
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Problema ao recuperar", ex);
            return null;
        }
    }

    /**
     * Lista todos as entidades da entidade do facade ordenado pelo primeiro
     * atributo
     *
     * @return List contendo as entidades
     */
    @Transactional
    public List<T> listar() {
        String hql = "from " + classe.getSimpleName() + " obj order by obj." + Persistencia.getFieldId(classe).getName();
        Query q = getEntityManager().createQuery(hql);

        return q.getResultList();
    }

    @Transactional
    public List<T> listarPorId(Long id, String campo) {
        String hql = "from " + classe.getSimpleName() + " obj where obj." + campo + " = " + id;
        Query q = getEntityManager().createQuery(hql);
        return q.getResultList();
    }

    @Transactional
    public List<T> listarFiltrando(String s, String... atributos) {
        String hql = "from " + classe.getSimpleName() + " obj where ";        
        for (String atributo : atributos) {
            hql += "cast(lower(obj." + atributo + ") as string) like :filtro OR ";
        }        
        hql = hql.substring(0, hql.length() - 3);
        Query q = getEntityManager().createQuery(hql);
        q.setParameter("filtro", "%" + s.toLowerCase() + "%");
        q.setMaxResults(50);
        return q.getResultList();
    }

    /**
     * Retorna o número de entidades armazenadas no banco
     *
     * @return número de entidades
     */
    @Transactional
    public long count() {
        Query query = getEntityManager().createQuery("SELECT COUNT(obj." + Persistencia.getFieldId(classe).getName() + ") FROM " + classe.getSimpleName() + " obj");
        Long quantidade = (Long) query.getSingleResult();
        return quantidade;
    }

    private void preSaveOrUpdate(Object se) {
        int camposInvalidos = 0;
        for (Field field : Persistencia.getAtributos(se.getClass())) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Cadastravel.class) && field.getAnnotation(Cadastravel.class).obrigatorio()) {
                if (Persistencia.getAttributeValue(se, field.getName()) == null || Persistencia.getAttributeValue(se, field.getName()).toString().trim().length() <= 0) {
                    String nomeCampo = field.getAnnotation(Cadastravel.class).label() == null || field.getAnnotation(Cadastravel.class).label().trim().length() <= 0 ? field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1, field.getName().length()) : field.getAnnotation(Cadastravel.class).label();
                    FacesUtil.addError("Campo obrigatório.", "O campo " + nomeCampo + " é obrigatório.");
                    camposInvalidos++;
                }
            }
        }

        if (camposInvalidos == 1) {
            throw new CampoObrigatorioException("Um campo obrigatório não foi informado.");
        }

        if (camposInvalidos > 1) {
            throw new CampoObrigatorioException("Existem campos obrigatórios que não foram informados");
        }
    }

    @Transactional
    public Object getPrimeiroDoRelacionamento(Object father, String son) {
        String hql = "select son from " + father.getClass().getSimpleName() + " f"
                + " inner join f." + son + " son"
                + " where f = :father";
        Query q = getEntityManager().createQuery(hql);
        q.setParameter("father", father);
        q.setMaxResults(1);
        try{
            return q.getSingleResult();
        } catch (NoResultException ex){
            return null;
        }
    }

    public void preRemove(Object obj) throws ClassCastException {
        SuperEntidade se = (SuperEntidade) obj;
        for (Field field : Persistencia.getAtributos(se.getClass())) {
            field.setAccessible(true);
            if (Persistencia.atributoPossuiRelacionamento(field)) {
                SuperEntidade cl = (SuperEntidade) getPrimeiroDoRelacionamento(obj, field.getName());
                if (cl != null) {
                    String mensagem = se.getArtigoDaEntidade().toUpperCase() + " '" + se.getNomeDaEntidade(); // A Categoria
                    mensagem += " "+se.toString()+"' possui "; // A Categoria 3 - Doidão possui
                    mensagem += cl.getNomeDaEntidade() + "(s) relacionad" + cl.getArtigoDaEntidade() + "(s) a " + se.getTerceiraPessoaDaEntidade()+".";
                    throw new RuntimeException(mensagem);
                }
            }
        }
    }
}
