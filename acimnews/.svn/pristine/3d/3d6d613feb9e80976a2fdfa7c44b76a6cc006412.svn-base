/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.validators.listener;

import br.com.acimnews.negocios.CategoriaFacade;
import br.com.acimnews.supers.SuperEntidade;
import br.com.acimnews.util.Persistencia;
import br.com.acimnews.util.anotacoes.Etiqueta;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import javax.persistence.PreRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Felipe Marinzeck
 */
public class RelacionamentoValidator implements Serializable {

    @Autowired
    private CategoriaFacade categoriaFacade;

    public RelacionamentoValidator() {
    }

    @PreRemove
    public void preRemove(SuperEntidade se) {
        for (Field field : Persistencia.getAtributos(se.getClass())) {
            field.setAccessible(true);
            if (Persistencia.atributoPossuiRelacionamento(field)) {
                Object valores = Persistencia.getAttributeValue(se, field.getName());                                
                System.out.println("Facade é..: " + categoriaFacade);
                
                
                String artigoPrincipal = recuperarArtigoDaEntidade(se);
                String mensagem = artigoPrincipal + " " + recuperarNomeDaEntidade(se);
                mensagem += " " + se.toString();
                mensagem += " possui ";

                String messPadrao = "A CATEGORIA X POSSUI O(S) SEGUINTE(S) ACONTECIMENTO(S) RELACIONADO(S) A ELA: LASDLKAJSD LAKSJD ALKSJD ALKSDJ ALKJSD ALKJSD ALSKD. NÃO SERÁ POSSÍVEL REALIZAR A EXCLUSÃO";

                if (field.getType().equals(List.class)) {
                    Class clazz = (Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
                    System.out.println("Clazz..: " + clazz);
//                    System.out.println("A Mensagem é..: "+mensagem);
//                    System.out.println("O campo é.....: "+((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]);
//                    System.out.println("Valor do campo..: " + Persistencia.getAttributeValue(se, field.getName()));
                }

                if (field.getType().equals(Map.class)) {
                    System.out.println("Valor do campo..: " + Persistencia.getAttributeValue(se, field.getName()));
                }
            }
        }
    }

    private String recuperarNomeDaEntidade(SuperEntidade se) {
        Etiqueta e = se.getClass().getAnnotation(Etiqueta.class);
        if (e == null) {
            return se.getClass().getSimpleName();
        }

        return e.descricao();
    }

    private String recuperarArtigoDaEntidade(SuperEntidade se) {
        Etiqueta e = se.getClass().getAnnotation(Etiqueta.class);
        final String PADRAO_INDEFINIDO = "O(a)";

        if (e == null) {
            return PADRAO_INDEFINIDO;
        }

        if (e.sexo().equals("M")) {
            return "O";
        }

        if (e.sexo().equals("F")) {
            return "A";
        }

        return PADRAO_INDEFINIDO;
    }
}
