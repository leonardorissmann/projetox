/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.entidades;

import br.com.acimnews.supers.SuperEntidade;
import br.com.acimnews.util.anotacoes.Cadastravel;
import br.com.acimnews.util.anotacoes.Etiqueta;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.hibernate.envers.Audited;

/**
 *
 * @author Felipe Marinzeck
 */
@Entity
@Audited
@Table(name="usuario")
@Etiqueta(descricao="Usuário", sexo="M")
public class Usuario extends SuperEntidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Cadastravel(label="Código", obrigatorio=false)
    private Long id;
    @Cadastravel(label = "Nome")
    private String nome;
    @Cadastravel(label = "Login")
    private String login;
    @Cadastravel(label = "Senha", estaNoVisualiza=false)
    private String senha;
    @Transient
    private String confirmacaoSenha;
    @Enumerated(EnumType.STRING)
    @Cadastravel(label = "Privilégio", estaNoVisualiza=false)
    @Column(name="privilegiousuario")
    private PrivilegioUsuario privilegioUsuario;
    @Cadastravel(label = "Ativo?", estaNoVisualiza=false)
    private Boolean ativo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public PrivilegioUsuario getPrivilegioUsuario() {
        return privilegioUsuario;
    }

    public void setPrivilegioUsuario(PrivilegioUsuario privilegioUsuario) {
        this.privilegioUsuario = privilegioUsuario;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    @Override
    public String toString() {
        return login;
    }

    public String toStringAutoComplete() {
        return this.toString();
    }
}
