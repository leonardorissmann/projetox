/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.entidades;

import br.com.acimnews.supers.SuperEntidade;
import br.com.acimnews.util.anotacoes.Cadastravel;
import br.com.acimnews.util.anotacoes.Etiqueta;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Felipe Marinzeck
 */
@Entity
@Table(name="sugestao")
@Etiqueta(descricao="Sugestão", sexo="F")
public class Sugestao extends SuperEntidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Cadastravel(label="Código", obrigatorio=false)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Cadastravel(label="Data de Envio")
    @Column(name="enviadaem")
    private Date enviadaEm;
    @Cadastravel(label="Nome Enviador")
    private String nome;
    @Cadastravel(label="E-mail Enviador")
    private String email;
    @Cadastravel(label="Telefone Enviador")
    private String telefone;
    @Cadastravel(label="Sugestão")
    private String descricao;
    @Cadastravel(label="Foi lida?")
    private boolean foiLida;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEnviadaEm() {
        return enviadaEm;
    }

    public void setEnviadaEm(Date enviadaEm) {
        this.enviadaEm = enviadaEm;
    }

    public boolean isFoiLida() {
        return foiLida;
    }

    public void setFoiLida(boolean foiLida) {
        this.foiLida = foiLida;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
