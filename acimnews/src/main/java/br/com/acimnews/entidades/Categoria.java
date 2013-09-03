/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.entidades;

import br.com.acimnews.supers.SuperEntidade;
import br.com.acimnews.util.anotacoes.Cadastravel;
import br.com.acimnews.util.anotacoes.Etiqueta;
import br.com.acimnews.util.anotacoes.Filtravel;
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
@Table(name="categoria")
@Etiqueta(descricao="Categoria", sexo="F")
public class Categoria extends SuperEntidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Cadastravel(label="Código", obrigatorio=false)
    @Filtravel
    private Long id;
    @Filtravel
    @Cadastravel(label = "Título")
    private String titulo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        try {
            return this.id + " - " + this.titulo;
        } catch (Exception e) {
            return this.titulo.trim().length() > 0 ? this.titulo : "CATEGORIA DESCONHECIDA";
        }
    }

    public String toStringAutoComplete() {
        return this.toString();
    }
}
