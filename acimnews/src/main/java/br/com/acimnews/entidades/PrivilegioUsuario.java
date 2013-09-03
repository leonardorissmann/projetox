/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.entidades;

/**
 *
 * @author Felipe Marinzeck
 */
public enum PrivilegioUsuario {

    ROLE_ADMIN("Administrativo"),
    ROLE_USER("Usuário");
    private String descricao;

    private PrivilegioUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
