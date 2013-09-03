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
import org.hibernate.envers.Audited;

/**
 *
 * @author Renato Romanini
 */
@Entity
@Audited
@Table(name="notificacao")
@Etiqueta(descricao="Notificação", sexo="F")
public class Notificacao extends SuperEntidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Cadastravel(label="Código", obrigatorio=false)
    private Long id;
    @Cadastravel(label = "Mensagem da Barra de Status")
    @Column(name="mensagembarrastatus")
    private String mensagemBarraStatus;
    @Cadastravel(label = "Título")
    private String titulo;
    @Cadastravel(label = "Mensagem")
    private String mensagem;
    @Cadastravel(label="Ativa?")
    private Boolean ativa;

    public Notificacao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagemBarraStatus() {
        return mensagemBarraStatus;
    }

    public void setMensagemBarraStatus(String mensagemBarraStatus) {
        this.mensagemBarraStatus = mensagemBarraStatus;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    @Override
    public String toString() {
        return "Código " + this.id;
    }
}
