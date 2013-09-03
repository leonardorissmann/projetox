/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.entidades;

import br.com.acimnews.negocios.OuvinteAuditoria;
import br.com.acimnews.supers.SuperEntidade;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

/**
 *
 * @author Felipe Marinzeck
 */
@Entity
@RevisionEntity(value=OuvinteAuditoria.class)
@Table(name="revisaoauditoria")
public class RevisaoAuditoria extends SuperEntidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @RevisionNumber
    private Long id;
    @RevisionTimestamp
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataHora;
    private String usuario;
    private String ip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "entidades.RevisaoAuditoria[id=" + id + "]";
    }
}
