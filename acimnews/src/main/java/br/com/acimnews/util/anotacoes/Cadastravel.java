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
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface Cadastravel {
    public String label() default "";
    public boolean obrigatorio() default true;
    public boolean aceitaNegativo() default true;
    public boolean estaNoVisualiza() default true;
}
