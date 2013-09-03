/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.controle;

import br.com.acimnews.entidades.*;
import br.com.acimnews.negocios.SugestaoFacade;
import br.com.acimnews.negocios.UsuarioFacade;
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
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Felipe Marinzeck
 */
@Controller
@Scope("view")
@URLMapping(id = "listar-sugestao", pattern = "/sugestao/listar/", viewId = "/faces/modulos/informacao/sugestao/listar.xhtml")
public class SugestaoControlador extends SuperControlador<Sugestao> implements Serializable {

    private Long id;
    @Autowired
    private SugestaoFacade sugestaoFacade;

    /**
     * Creates a new instance of UsuarioControlador
     */
    public SugestaoControlador() {
        super(Sugestao.class);
    }
    
    @Override
    public SuperFacade getEsteFacade() {
        return sugestaoFacade;
    }

    @URLAction(mappingId = "listar-sugestao", phaseId = PhaseId.RENDER_RESPONSE, onPostback = false)
    public void listar() {
        setOperacao(Operacoes.LISTAR);
    }
    
    public void salvar() {
//        try {
//            if (this.getOperacao().equals(Operacoes.NOVO)) {
//                this.resultado = sugestaoFacade.inserir(this.selecionado);
//            }
//
//            if (this.getOperacao().equals(Operacoes.EDITAR)) {
//                this.resultado = sugestaoFacade.alterar(this.selecionado);
//            }
//
//            if (resultado.isValidado()) {
//                this.resultado.addInfo("Sucesso!", "O(a) usuario " + this.selecionado.toString() + " foi <b>" + this.operacao.getDescricao() + "</b> com sucesso!");
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
            caminho = "/sugestao/listar/";
        }
        Util.redirecionamentoInterno(caminho);
    }

    /*
     * GETTERS E SETTERS
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Sugestao> getLista() {
        return sugestaoFacade.listar();
    }

    public SugestaoFacade getSugestaoFacade() {
        return sugestaoFacade;
    }

    public void setSugestaoFacade(SugestaoFacade sugestaoFacade) {
        this.sugestaoFacade = sugestaoFacade;
    }

    public void marcarSugestaoComoLida(Sugestao sugestao) {
        try {
            sugestao.setFoiLida(true);
            sugestaoFacade.alterar(sugestao);
            FacesUtil.addInfo("Sucesso", "A sugestão enviada por " + sugestao.getNome() + " foi alterada com sucesso.");
        } catch (Exception e) {
            FacesUtil.addError("Erro ao alterar sugestão.", "Ocorreu um erro ao alterar a sugestão, entre em contato com suporte técnico. " + e.getMessage());
        }

    }
}
