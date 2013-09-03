/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.controle;

import br.com.acimnews.entidades.Categoria;
import br.com.acimnews.exceptions.CampoObrigatorioException;
import br.com.acimnews.negocios.CategoriaFacade;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Felipe Marinzeck
 */
@ManagedBean(name="categoriaControlador")
@ViewScoped
@URLMappings(mappings = {
    @URLMapping(id = "novo-categoria", pattern = "/categoria/novo/", viewId = "/faces/modulos/administrativo/categoria/editar.xhtml"),
    @URLMapping(id = "editar-categoria", pattern = "/categoria/editar/#{categoriaControlador.id}/", viewId = "/faces/modulos/administrativo/categoria/editar.xhtml"),
    @URLMapping(id = "listar-categoria", pattern = "/categoria/listar/", viewId = "/faces/modulos/administrativo/categoria/listar.xhtml"),
    @URLMapping(id = "ver-categoria", pattern = "/categoria/ver/#{categoriaControlador.id}/", viewId = "/faces/modulos/administrativo/categoria/visualizar.xhtml")
})
public class CategoriaControlador extends SuperControlador<Categoria> implements Serializable {

    private Long id;
    @Autowired
    private CategoriaFacade categoriaFacade;

    /**
     * Creates a new instance of UsuarioControlador
     */
    public CategoriaControlador() {
        super(Categoria.class);
    }
    
    @Override
    public SuperFacade getEsteFacade() {
        return categoriaFacade;
    }

    @URLAction(mappingId = "listar-categoria", phaseId = PhaseId.RENDER_RESPONSE, onPostback = false)
    public void listar() {
        setOperacao(Operacoes.LISTAR);
    }

    @URLAction(mappingId = "novo-categoria", phaseId = PhaseId.RENDER_RESPONSE, onPostback = false)
    public void novo() {
        setOperacao(Operacoes.NOVO);
        this.selecionado = new Categoria();
    }

    @URLAction(mappingId = "editar-categoria", phaseId = PhaseId.RENDER_RESPONSE, onPostback = false)
    public void editar() {
        setOperacao(Operacoes.EDITAR);
        this.selecionado = (Categoria) categoriaFacade.recuperar(Categoria.class, this.id);
    }

    @URLAction(mappingId = "ver-categoria", phaseId = PhaseId.RENDER_RESPONSE, onPostback = false)
    public void visualizar() {
        setOperacao(Operacoes.VER);
        this.selecionado = (Categoria) categoriaFacade.recuperar(Categoria.class, this.id);
    }
    
    public void salvar() {
        try {
            if (this.getOperacao().equals(Operacoes.NOVO)) {
                categoriaFacade.inserir(this.selecionado);
            }

            if (this.getOperacao().equals(Operacoes.EDITAR)) {
                categoriaFacade.alterar(this.selecionado);
            }

            addInfo("Operação realizada com sucesso!", "O(a) categoria " + this.selecionado.toString() + " foi <b>" + this.operacao.getDescricao() + "</b> com sucesso!");
        } catch (RuntimeException ex) {
            if (ex instanceof CampoObrigatorioException) {
                addError(ex.getMessage(), ex.getMessage());
            } else {
                 addErrorPadrao(ex);
            }
            return;
        } catch (Exception ex) {
             addErrorPadrao(ex);
            return;
        }

        navegarEmbora();
    }

    public void navegarEmbora() {
        String caminho = getCaminhoOrigem();
        if (caminho == null) {
            caminho = "/categoria/listar/";
        }
        Util.redirecionamentoInterno(caminho);
    }


    /*
     * GETTERS E SETTERS
     */
    public CategoriaFacade getCategoriaFacade() {
        return categoriaFacade;
    }

    public void setCategoriaFacade(CategoriaFacade categoriaFacade) {
        this.categoriaFacade = categoriaFacade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Categoria> getLista() {
        System.out.println("Veio aqui com o cara..: "+categoriaFacade);
        return categoriaFacade.listar();
    }

    public List<Categoria> completarEstaEntidade(String parte) {
        return categoriaFacade.listarFiltrando(parte.trim(), "titulo", "id");
    }

    public List<SelectItem> selectItensCategoria() {
        List<SelectItem> toReturn = new ArrayList<SelectItem>();
        toReturn.add(new SelectItem("", "Todas"));
        for (Categoria catDaVez : categoriaFacade.listar()) {
            toReturn.add(new SelectItem(catDaVez.getId(), catDaVez.toString()));
        }
        return toReturn;
    }
}
