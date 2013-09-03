/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.controle;

import br.com.acimnews.entidades.*;
import br.com.acimnews.negocios.NotificacaoFacade;
import br.com.acimnews.supers.SuperControlador;
import br.com.acimnews.supers.SuperFacade;
import br.com.acimnews.util.FacesUtil;
import br.com.acimnews.util.Util;
import br.com.acimnews.util.anotacoes.Operacoes;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLAction.PhaseId;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Felipe Marinzeck
 */
@Controller
@Scope("view")
@URLMappings(mappings = {
    @URLMapping(id = "novo-notificacao", pattern = "/notificacao/novo/", viewId = "/faces/modulos/administrativo/notificacao/editar.xhtml"),
    @URLMapping(id = "editar-notificacao", pattern = "/notificacao/editar/#{notificacaoControlador.id}/", viewId = "/faces/modulos/administrativo/notificacao/editar.xhtml"),
    @URLMapping(id = "listar-notificacao", pattern = "/notificacao/listar/", viewId = "/faces/modulos/administrativo/notificacao/listar.xhtml"),
    @URLMapping(id = "visualizar-notificacao", pattern = "/notificacao/ver/#{notificacaoControlador.id}/", viewId = "/faces/modulos/administrativo/notificacao/visualizar.xhtml")
})
public class NotificacaoControlador extends SuperControlador<Notificacao> implements Serializable {

    private Long id;
    @Autowired
    private NotificacaoFacade notificacaoFacade;

    /**
     * Creates a new instance of UsuarioControlador
     */
    public NotificacaoControlador() {
        super(Notificacao.class);
    }
    
    @Override
    public SuperFacade getEsteFacade() {
        return notificacaoFacade;
    }

    @URLAction(mappingId = "listar-notificacao", phaseId = PhaseId.RENDER_RESPONSE, onPostback = false)
    public void listar() {
        setOperacao(Operacoes.LISTAR);
    }

    @URLAction(mappingId = "novo-notificacao", phaseId = PhaseId.RENDER_RESPONSE, onPostback = false)
    public void novo() {
        setOperacao(Operacoes.NOVO);
        this.selecionado = new Notificacao();
        recuperarDependenciasSecao();
    }

    @URLAction(mappingId = "editar-notificacao", phaseId = PhaseId.RENDER_RESPONSE, onPostback = false)
    public void editar() {
        setOperacao(Operacoes.EDITAR);
        this.selecionado = (Notificacao) notificacaoFacade.recuperar(Notificacao.class, this.id);
        recuperarDependenciasSecao();
    }

    @URLAction(mappingId = "visualizar-notificacao", phaseId = PhaseId.RENDER_RESPONSE, onPostback = false)
    public void visualizar() {
        setOperacao(Operacoes.VER);
        this.selecionado = (Notificacao) notificacaoFacade.recuperar(Notificacao.class, this.id);
    }

    public void desativarNotificacaoAtivaAtualmente() {
        Notificacao nAtiva = notificacaoFacade.recuperarNotificacaoAtiva();
        if (nAtiva != null) {
            nAtiva.setAtiva(Boolean.FALSE);
            notificacaoFacade.alterar(nAtiva);
        }
    }

    public void marcarNotificacaoComAtiva(Notificacao notificacao) {
        try {
            notificacao.setAtiva(Boolean.TRUE);
            desativarNotificacaoAtivaAtualmente();
            notificacaoFacade.alterar(notificacao);
            addInfo("Operação realizada com sucesso", "A notificação " + notificacao.getId() + " - " + notificacao.getTitulo() + " foi alterada com sucesso.");
        } catch (Exception e) {
            addErrorPadrao(e);
        }
    }

    public List<SelectItem> filtroStatus() {
        List<SelectItem> toReturn = new ArrayList<SelectItem>();

        toReturn.add(new SelectItem("Ativo", "Ativo"));
        toReturn.add(new SelectItem("Inativo", "Inativo"));
        toReturn.add(new SelectItem("", "Todos"));

        return toReturn;
    }

    public void salvar() {
//        try {
//            if (selecionado.getAtiva()) {
//                desativarNotificacaoAtivaAtualmente();
//            }
//
//            if (this.getOperacao().equals(Operacoes.NOVO)) {
//                this.resultado = notificacaoFacade.inserir(this.selecionado);
//            }
//
//            if (this.getOperacao().equals(Operacoes.EDITAR)) {
//                this.resultado = notificacaoFacade.alterar(this.selecionado);
//            }
//
//            if (resultado.isValidado()) {
//                this.resultado.addInfo("Sucesso!", "A Notificacação " + this.selecionado.toString() + " foi <b>" + this.operacao.getDescricao() + "</b> com sucesso!");
//            }
//        } catch (Exception e) {
//            addError("Erro ao executar operação.", "Por favor tente novamente, se o problema persistir entre em contato com o suporte técnico.");
//        }
//
//        resultado.printAllMessages();
//        if (resultado.isValidado()) {
//            navegarEmbora();
//        }
    }

    public void navegarEmbora() {
        String caminho = getCaminhoOrigem();
        if (caminho == null) {
            caminho = "/notificacao/listar/";
        }
        Util.redirecionamentoInterno(caminho);
    }

    /*
     * GETTERS E SETTERS
     */
    public NotificacaoFacade getNotificacaoFacade() {
        return notificacaoFacade;
    }

    public void setNotificacaoFacade(NotificacaoFacade notificacaoFacade) {
        this.notificacaoFacade = notificacaoFacade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Notificacao> getLista() {
        return notificacaoFacade.listar();
    }
}
