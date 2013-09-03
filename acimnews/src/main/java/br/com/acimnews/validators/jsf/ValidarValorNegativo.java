/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acimnews.validators.jsf;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.component.inputtext.InputText;

/**
 *
 * @author Felipe Marinzeck
 */
public class ValidarValorNegativo implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null){
            return;
        }
        
        String inputFromField = value.toString();
        String simpleTextPatternText = "^[0-9]+[0-9]*$";
        Pattern textPattern = Pattern.compile(simpleTextPatternText);
        Matcher productValueMatcher = textPattern.matcher(inputFromField);

        if (!productValueMatcher.matches()) {
            FacesMessage msg = new FacesMessage("Valor negativo.", "O valor do campo " + ((InputText) component).getLabel() + " não pode ser negativo.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
