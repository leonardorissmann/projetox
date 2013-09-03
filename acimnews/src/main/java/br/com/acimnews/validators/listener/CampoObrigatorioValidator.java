/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.validators.listener;

import br.com.acimnews.exceptions.CampoObrigatorioException;
import br.com.acimnews.supers.SuperEntidade;
import br.com.acimnews.util.FacesUtil;
import br.com.acimnews.util.Persistencia;
import br.com.acimnews.util.anotacoes.Cadastravel;
import java.lang.reflect.Field;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author Felipe Marinzeck
 */
public class CampoObrigatorioValidator {
    
    @PrePersist
    @PreUpdate
    private void preSaveOrUpdate(SuperEntidade se) {
        System.out.println("Veio no CampoObrigatorioValidator");
        int camposInvalidos = 0;
        for (Field field : Persistencia.getAtributos(se.getClass())) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Cadastravel.class) && field.getAnnotation(Cadastravel.class).obrigatorio()) {
                if (Persistencia.getAttributeValue(se, field.getName()) == null || Persistencia.getAttributeValue(se, field.getName()).toString().trim().length() <= 0){
                    String nomeCampo = field.getAnnotation(Cadastravel.class).label() == null || field.getAnnotation(Cadastravel.class).label().trim().length() <= 0 ? field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1, field.getName().length()) : field.getAnnotation(Cadastravel.class).label();
                    FacesUtil.addError("Campo obrigatório.", "O campo "+nomeCampo+" é obrigatório.");
                    camposInvalidos ++;
                }
            }
        }
        
        if (camposInvalidos == 1){
            throw new CampoObrigatorioException("Um campo obrigatório não foi informado.");
        }
        
        if (camposInvalidos > 1){
            throw new CampoObrigatorioException("Existem campos obrigatórios que não foram informados");
        }
    }
}
