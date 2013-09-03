/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.util;

import br.com.acimnews.entidades.Categoria;
import br.com.acimnews.supers.SuperFacade;
import br.com.acimnews.util.anotacoes.Filtravel;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Felipe Marinzeck
 */
public class ConverterAutoComplete implements Converter, Serializable {

    protected static final Logger logger = Logger.getLogger("br.com.acimnews.util.ConverterAutoComplete");
    private SuperFacade facade;
    private Class classe;

    public ConverterAutoComplete(Class classe, SuperFacade f) {
        this.facade = f;
        this.classe = classe;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            if (value.trim().length() <= 0) {
                return null;
            }
            Object chave = Persistencia.getFieldId(classe).getType().getConstructor(String.class).newInstance(value);
            RequestContext.getCurrentInstance().update(component.getClientId());
            return facade.recuperar(classe, chave);
        } catch (Exception ex) {
            return null;            
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return Persistencia.getAttributeValue(value, Persistencia.getFieldId(classe).getName()).toString();
        } else {
            return null;
        }
    }
}
