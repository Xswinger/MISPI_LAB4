package webdeving.validators;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("rValidator")
public class RValidator implements Validator<Object> {

    private final int MIN = 2;
    private final int MAX = 5;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try {
            int radius = Integer.parseInt(o.toString());
            if (radius < MIN || radius > MAX) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            throw new ValidatorException(new FacesMessage("r: int [" + MIN + "; " + MAX + "]"));
        }
    }
}
