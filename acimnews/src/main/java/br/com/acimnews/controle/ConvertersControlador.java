/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.controle;

import br.com.acimnews.entidades.Categoria;
import br.com.acimnews.negocios.CategoriaFacade;
import br.com.acimnews.util.ConverterAutoComplete;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Felipe Marinzeck
 */
@Controller
@Scope("request")
public class ConvertersControlador implements Serializable{

    // Converters
    private ConverterAutoComplete categoriaConverter;
    @Autowired
    private CategoriaFacade categoriaFacade;

    public ConvertersControlador() {
    }
    
    // Getters
    public ConverterAutoComplete getCategoriaConverter() {
        if (categoriaConverter == null){
            this.categoriaConverter = new ConverterAutoComplete(Categoria.class, categoriaFacade);
        }
        return categoriaConverter;
    }

    public CategoriaFacade getCategoriaFacade() {
        return categoriaFacade;
    }

    public void setCategoriaFacade(CategoriaFacade categoriaFacade) {
        this.categoriaFacade = categoriaFacade;
    }
    
}