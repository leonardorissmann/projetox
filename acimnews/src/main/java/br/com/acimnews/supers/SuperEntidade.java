/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.supers;

import br.com.acimnews.util.Persistencia;
import br.com.acimnews.util.anotacoes.Etiqueta;
import javax.persistence.Transient;

/**
 *
 * @author Felipe Marinzeck
 */
public abstract class SuperEntidade {
    
    @Transient
    public Long criadoEm;

    public SuperEntidade() {        
        this.criadoEm = System.nanoTime();
    }

    /*
     * Criado como método estático para evitar impor uma superclasse a todas as
     * entidades...
     */
    public static boolean calcularEquals(Object obj, Object outro) {
        if (outro == null) {
            return false;
        }
        Object idObj = Persistencia.getId(obj);
        Object idOutro = Persistencia.getId(outro);

        Long criadoEmObj = (Long) Persistencia.getAttributeValue(obj, "criadoEm");
        Long criadoEmOutro = (Long) Persistencia.getAttributeValue(outro, "criadoEm");

        if (obj.getClass() != outro.getClass()) {
            return false;
        }
        if (idObj == null) {
            if (!criadoEmObj.equals(criadoEmOutro)) {
                return false;
            }
        } else {
            if (!idObj.equals(idOutro)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Criado como método estático para evitar impor uma superclasse a todas as
     * entidades...
     */
    public static int calcularHashCode(Object obj) {
        Object id = Persistencia.getId(obj);
        Long criadoEm = (Long) Persistencia.getAttributeValue(obj, "criadoEm");
        if (id == null) {
            int hash = 3;
            hash = 97 * hash + (criadoEm != null ? criadoEm.hashCode() : 0);
            return hash;
        } else {
            int hash = 7;
            hash = 71 * hash + (id != null ? id.hashCode() : 0);
            return hash;
        }
    }
    
    public String getArtigoDaEntidade() {
        Etiqueta e = this.getClass().getAnnotation(Etiqueta.class);
        final String PADRAO_INDEFINIDO = "O/A";

        if (e == null) {
            return PADRAO_INDEFINIDO;
        }

        if (e.sexo().equals("M")) {
            return "o";
        }

        if (e.sexo().equals("F")) {
            return "a";
        }

        return PADRAO_INDEFINIDO;
    }
    
    public String getTerceiraPessoaDaEntidade() {
        Etiqueta e = this.getClass().getAnnotation(Etiqueta.class);
        final String PADRAO_INDEFINIDO = "O/A";

        if (e == null) {
            return PADRAO_INDEFINIDO;
        }

        if (e.sexo().equals("M")) {
            return "ele";
        }

        if (e.sexo().equals("F")) {
            return "ela";
        }

        return PADRAO_INDEFINIDO;
    }
    
    public String getNomeDaEntidade() {
        Etiqueta e = this.getClass().getAnnotation(Etiqueta.class);
        if (e == null) {
            return this.getClass().getSimpleName();
        }

        return e.descricao();
    }
}
