/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.supers;

import br.com.acimnews.controle.SistemaControlador;
import br.com.acimnews.util.FacesUtil;
import br.com.acimnews.util.Persistencia;
import br.com.acimnews.util.anotacoes.Cadastravel;
import br.com.acimnews.util.anotacoes.Operacoes;
import com.ocpsoft.pretty.PrettyContext;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author Felipe Marinzeck
 */
public abstract class SuperControlador<T> {

    @Autowired
    protected SistemaControlador sistemaControlador;
    protected Operacoes operacao;
    protected Class<T> classe;
    public T selecionado;

    public SuperControlador() {
    }

    public SuperControlador(Class<T> classe) {
        injetarDependenciasSpring();
        this.classe = classe;
    }

    public final void injetarDependenciasSpring() {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, servletContext);
    }

    public abstract SuperFacade getEsteFacade();

    // GETTERS E SETTERS
    public T getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(T selecionado) {
        this.selecionado = selecionado;
    }

    public Operacoes getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacoes operacao) {
        this.operacao = operacao;
    }

    // MÉTODOS
    public void addInfo(String summary, String detail) {
        FacesUtil.addInfo(summary, detail);
    }

    public void addError(String summary, String detail) {
        FacesUtil.addError(summary, detail);
    }

    public void addWarn(String summary, String detail) {
        FacesUtil.addWarn(summary, detail);
    }

    public void addFatal(String summary, String detail) {
        FacesUtil.addFatal(summary, detail);
    }

    public void addErrorPadrao(Throwable t) {
        FacesUtil.addErrorPadrao(t);
    }

    public String getCaminhoOrigem() {
        String origem = null;
        String urlAtual = PrettyContext.getCurrentInstance().getRequestURL().toString();
        for (Iterator<Object[]> it = this.sistemaControlador.getCaminhos().listIterator(); it.hasNext();) {
            Object[] objDaVez = it.next();
            String urlHistorico = (String) objDaVez[1];
            String urlDestino = (String) objDaVez[0];

            if (urlHistorico.equals(urlAtual)) {
                it.remove();
                origem = urlDestino;
                break;
            }
        }
        return origem;
    }

    private void criarConversasao() {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("objetosConversacao") == null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("objetosConversacao", new HashMap<String, Object>());
        }
    }

    private Object getFromSession(String chave) {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(chave);
    }

    public void putToConversation(String chave, Object objeto) {
        criarConversasao();
        HashMap<String, Object> conversa = (HashMap<String, Object>) getFromSession("objetosConversacao");
        conversa.put(chave, objeto);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("objetosConversacao", conversa);
    }

    public Object getFromConversation(String chave) {
        criarConversasao();
        HashMap<String, Object> conversa = (HashMap<String, Object>) getFromSession("objetosConversacao");
        return conversa.get(chave);
    }

    public void removeFromConversation(String chave) {
        criarConversasao();
        HashMap<String, Object> conversa = (HashMap<String, Object>) getFromSession("objetosConversacao");
        conversa.remove(chave);
    }

    public void tratarCaminhos(String destino) {
        sistemaControlador.tratarCaminhos(destino);
    }

    public void guardarSelecionado() {
        putToConversation(this.selecionado.getClass().getSimpleName(), this.selecionado);
    }

    public void recuperarSelecionado() {
        if (getFromConversation(this.selecionado.getClass().getSimpleName()) != null) {
            this.selecionado = (T) getFromConversation(this.selecionado.getClass().getSimpleName());
            removeFromConversation(this.selecionado.getClass().getSimpleName());
        }
    }

    public void recuperarDependenciasSecao() {
        recuperarSelecionado();
    }

    public List<Field> getCamposDoSelecionado() {
        List<Field> campos = null;
        if (campos == null) {
            campos = new ArrayList<Field>();
        }
        for (Field f : Persistencia.getAtributos(classe)) {
            f.setAccessible(true);
            if (f.isAnnotationPresent(Cadastravel.class)) {
                if (f.getAnnotation(Cadastravel.class).estaNoVisualiza()) {
                    campos.add(f);
                }
            }
        }
        return campos;
    }

    public String obterLabelCampo(Field f) {
        f.setAccessible(true);
        if (f.isAnnotationPresent(Cadastravel.class)) {
            return f.getAnnotation(Cadastravel.class).label();
        }
        return "";
    }

    public Object obterConteudoCampo(Field f) {
        f.setAccessible(true);
        if (f.isAnnotationPresent(Cadastravel.class)) {
            return Persistencia.getAttributeValue(selecionado, f.getName());
        }
        return "";
    }

    public void excluir(SuperEntidade obj) {
        try {
            getEsteFacade().excluir(obj);
            addInfo("Operação realizada com sucesso.", obj.getArtigoDaEntidade().toUpperCase() + " " + obj.getNomeDaEntidade() + " " + obj.toString() + " foi <b>excluíd" + obj.getArtigoDaEntidade() + "</b>.");
        } catch (RuntimeException ex) {
            addError("Operação não permitida.", ex.getMessage());
        } catch (Exception ex) {
            addErrorPadrao(ex);
        }
    }
}
