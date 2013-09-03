/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.exceptions;

/**
 *
 * @author Felipe Marinzeck
 */
public class CampoObrigatorioException extends RuntimeException{

    public CampoObrigatorioException(String message) {
        super(message);
    }
}
