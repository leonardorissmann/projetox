/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.entidades;

import br.com.acimnews.supers.SuperEntidade;
import br.com.acimnews.util.anotacoes.Etiqueta;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.envers.Audited;

/**
 *
 * @author Felipe Marinzeck
 */
@Entity
@Audited
@Table(name="arquivo")
@Etiqueta(descricao="Arquivo", sexo="M")
public class Arquivo extends SuperEntidade implements Serializable {

    public static final int TAMANHO_MAXIMO = 10485760;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String mimeType;
    private Long tamanho;
    @Lob
    @Column(length = TAMANHO_MAXIMO) //Principalmente por causa do Mysql
    private byte dados[];

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getDados() {
        return dados;
    }

    public void setDados(byte[] dados) {
        this.dados = dados;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "br.com.acimnews.entidades.Arquivo[id=" + id + "]";
    }
}
