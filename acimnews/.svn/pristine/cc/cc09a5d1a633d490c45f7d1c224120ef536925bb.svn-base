/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.controle;

import br.com.acimnews.entidades.Arquivo;
import br.com.acimnews.entidades.LogSistema;
import br.com.acimnews.util.Util;
import com.ocpsoft.pretty.PrettyContext;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Felipe Marinzeck
 */
@Controller
@Scope("session")
public class SistemaControlador implements Serializable {
    
    private List<Object[]> caminhos;
    private Boolean mostraRodape;
    private Boolean mostraCabecalho;
    
    /**
     * Creates a new instance of SistemaControlador
     */
    @SuppressWarnings("unchecked")
    public SistemaControlador() {
        this.caminhos = new ArrayList<Object[]>();
        limparTodasMensagens();
    }
    
    public String recuperarClasseGravidadeMensagem(FacesMessage mensagem) {
        String retorno = "";

        if (mensagem == null) {
            return retorno;
        }

        if (mensagem.getSeverity().equals(FacesMessage.SEVERITY_INFO)) {
            retorno = "info";
        }
        if ((mensagem.getSeverity().equals(FacesMessage.SEVERITY_WARN))) {
            retorno = "warn";
        }
        if ((mensagem.getSeverity().equals(FacesMessage.SEVERITY_ERROR))) {
            retorno = "error";
        }
        if ((mensagem.getSeverity().equals(FacesMessage.SEVERITY_FATAL))) {
            retorno = "fatal";
        }

        return retorno;
    }

    public List<LogSistema> getMensagens() {
        return (List<LogSistema>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mensagensLogSistema");
    }

    public void tornarMensagensLidas() {
        ArrayList<LogSistema> lista = (ArrayList<LogSistema>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("mensagensLogSistema");
        if (lista != null){
            for (LogSistema logSistema : lista) {
                logSistema.setFoiVisualizada(Boolean.TRUE);
            }
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("mensagensLogSistema", lista);
        }
        
        RequestContext.getCurrentInstance().execute("mensagensDialog.hide()");
    }

    public LogSistema getPrimeiraMensagem() {
        return (LogSistema) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("primeiraMensagem");
    }

    public void limparTodasMensagens() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("mensagensLogSistema", null);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("primeiraMensagem", null);
    }

    public void tratarCaminhos(String destino) {
        Object[] obj = new Object[2];        
        obj[0] = PrettyContext.getCurrentInstance().getRequestURL().toString();
        obj[1] = destino;
        this.caminhos.add(obj);
        Util.redirecionamentoInterno(destino);
    }

    public List<Object[]> getCaminhos() {
        return caminhos;
    }

    public void setCaminhos(List<Object[]> relacionamentos) {
        this.caminhos = relacionamentos;
    }
    
    public void navegarPara(String destino) {
        limparObjetosConversacao();
        Util.redirecionamentoInterno(destino);
    }

    public void navegarParaUrl(String destino) {
        limparObjetosConversacao();
        Util.redirecionarParaURL(destino);
    }
    
    public void limparObjetosConversacao(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("objetosConversacao", null);
    }

    public Boolean getMostraCabecalho() {
        return mostraCabecalho;
    }

    public void setMostraCabecalho(Boolean mostraCabecalho) {
        this.mostraCabecalho = mostraCabecalho;
    }

    public Boolean getMostraRodape() {
        return mostraRodape;
    }

    public void setMostraRodape(Boolean mostraRodape) {
        this.mostraRodape = mostraRodape;
    }
    
    public DefaultStreamedContent gerarArquivoDeEntidade(Arquivo arquivo){
        if (arquivo == null || arquivo.getDados() == null || arquivo.getDados().toString().trim().length() <= 0){
            return null;
        }
        InputStream is = new ByteArrayInputStream(arquivo.getDados());
        return new DefaultStreamedContent(is, arquivo.getMimeType(), arquivo.getNome());
    }
    
    public DefaultStreamedContent gerarImagem(byte[] dados){
        if(dados == null){
            return null;
        }
        InputStream is = new ByteArrayInputStream(dados);
        return new DefaultStreamedContent(is, "image/jpeg", "autoGenerated");
    }
}
