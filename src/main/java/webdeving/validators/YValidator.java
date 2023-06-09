package webdeving.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("yValidator")
public class YValidator implements Validator<Object> {

    private final int MIN = -3;
    private final int MAX = 3;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try {
            double y = Double.parseDouble(o.toString().replaceAll(",", "."));
            if (y < MIN || y > MAX) throw new NumberFormatException();
        } catch (Exception ex) {
            throw new ValidatorException(new FacesMessage("y: double [" + MIN + "; " + MAX + "]"));
        }
    }
}
