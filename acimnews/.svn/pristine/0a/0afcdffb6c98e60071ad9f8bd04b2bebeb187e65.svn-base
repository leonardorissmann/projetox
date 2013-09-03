/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.acimnews.util.anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Felipe Marinzeck
 * O campo sexo indica se uma entidade é masculina ou feminina
 * "M" - para masculino
 * "F" - para feminino
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
public @interface Etiqueta {
    public String descricao() default "";
    public String sexo() default "M";
}
