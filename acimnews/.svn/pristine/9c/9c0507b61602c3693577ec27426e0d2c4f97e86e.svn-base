/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.controle;

import br.com.acimnews.entidades.*;
import br.com.acimnews.negocios.SistemaFacade;
import br.com.acimnews.negocios.UsuarioFacade;
import br.com.acimnews.supers.SuperControlador;
import br.com.acimnews.supers.SuperFacade;
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
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Felipe Marinzeck
 */
@Controller
@Scope("view")
@URLMappings(mappings = {
    @URLMapping(id = "novo-usuario", pattern = "/usuario/novo/", viewId = "/faces/modulos/informacao/usuario/editar.xhtml"),
    @URLMapping(id = "editar-usuario", pattern = "/usuario/editar/#{usuarioControlador.id}/", viewId = "/faces/modulos/informacao/usuario/editar.xhtml"),
    @URLMapping(id = "listar-usuario", pattern = "/usuario/listar/", viewId = "/faces/modulos/informacao/usuario/listar.xhtml"),
    @URLMapping(id = "visualizar-usuario", pattern = "/usuario/ver/#{usuarioControlador.id}/", viewId = "/faces/modulos/informacao/usuario/visualizar.xhtml")
})
public class UsuarioControlador extends SuperControlador<Usuario> implements Serializable {

    private Long id;
    @Autowired
    private UsuarioFacade usuarioFacade;
    @Autowired
    private SistemaFacade sistemaFacade;

    /**
     * Creates a new instance of UsuarioControlador
     */
    public UsuarioControlador() {
        super(Usuario.class);
    }
    
    @Override
    public SuperFacade getEsteFacade() {
        return usuarioFacade;
    }

    @URLAction(mappingId = "listar-usuario", phaseId = PhaseId.RENDER_RESPONSE, onPostback = false)
    public void listar() {
        setOperacao(Operacoes.LISTAR);
    }

    @URLAction(mappingId = "novo-usuario", phaseId = PhaseId.RENDER_RESPONSE, onPostback = false)
    public void novo() {
        setOperacao(Operacoes.NOVO);
        this.selecionado = new Usuario();
        recuperarDependenciasSecao();
    }

    @URLAction(mappingId = "editar-usuario", phaseId = PhaseId.RENDER_RESPONSE, onPostback = false)
    public void editar() {
        setOperacao(Operacoes.EDITAR);
        this.selecionado = (Usuario) usuarioFacade.recuperar(Usuario.class, this.id);
        recuperarDependenciasSecao();
    }

    @URLAction(mappingId = "visualizar-usuario", phaseId = PhaseId.RENDER_RESPONSE, onPostback = false)
    public void visualizar() {
        setOperacao(Operacoes.VER);
        this.selecionado = (Usuario) usuarioFacade.recuperar(Usuario.class, this.id);
    }

    public void salvar() {
//        try {
//            if (this.getOperacao().equals(Operacoes.NOVO)) {
//                this.resultado = usuarioFacade.inserir(this.selecionado);
//            }
//
//            if (this.getOperacao().equals(Operacoes.EDITAR)) {
//                this.resultado = usuarioFacade.alterar(this.selecionado);
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
            caminho = "/usuario/listar/";
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

    public List<Usuario> getLista() {
        if (sistemaFacade.obtemLogin() != null) {
            Usuario usuario = usuarioFacade.recuperarUsuarioLogin(sistemaFacade.obtemLogin());
            if (usuario.getPrivilegioUsuario().equals(PrivilegioUsuario.ROLE_USER)) {
                ArrayList<Usuario> lista = new ArrayList<Usuario>();
                lista.add(usuario);
                return lista;
            }
        }
        return usuarioFacade.listar();
    }

    public List<SelectItem> filtroPrivilegioUsuario() {
        List<SelectItem> toReturn = new ArrayList<SelectItem>();
        toReturn.add(new SelectItem("", "Todos"));
        for (PrivilegioUsuario priv : PrivilegioUsuario.values()) {
            toReturn.add(new SelectItem(priv, priv.getDescricao()));
        }
        return toReturn;
    }

    public List<SelectItem> filtroStatusUsuario() {
        List<SelectItem> toReturn = new ArrayList<SelectItem>();

        toReturn.add(new SelectItem("Ativo", "Ativo"));
        toReturn.add(new SelectItem("Inativo", "Inativo"));
        toReturn.add(new SelectItem("", "Todos"));

        return toReturn;
    }

    public void alterarStatusUsuario(Usuario usuario) {
        try {
            usuario.setAtivo(!usuario.getAtivo());
//            usuarioFacade.altera(usuario);
            addInfo("Sucesso", "O usuario " + usuario + " foi atualizado com sucesso.");
        } catch (Exception e) {
            addError("Erro ao alterar sugestão.", "Ocorreu um erro ao alterar a sugestão, entre em contato com suporte técnico. " + e.getMessage());
        }

    }

    public void navegarParaAlterarSenhaUsuarioAtual() {
        String login = sistemaFacade.obtemLogin();
        if (login != null) {
            Usuario usuario = usuarioFacade.recuperarUsuarioLogin(login);
            if (usuario != null) {
                Util.redirecionamentoInterno("/usuario/editar/" + usuario.getId() + "/");
            }
        }
    }

    public Usuario recuperarUsuarioAtual() {
        String login = sistemaFacade.obtemLogin();
        if (login != null) {            
            Usuario usuario = usuarioFacade.recuperarUsuarioLogin(login);
            return usuario;
        }
        return new Usuario();
    }
}
