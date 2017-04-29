package services.validation;

import org.apache.commons.lang.StringUtils;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import static javax.xml.bind.ValidationEvent.FATAL_ERROR;
import static javax.xml.bind.ValidationEvent.WARNING;

/**
 * Created by Zoli on 30/01/2017.
 */
public class CxfSchemaValidationOffValidationEventHandler implements ValidationEventHandler{

    protected static final String[] ALLOWABLE_SCHEMA_VALIDATION_ERRORS = new String[]{
            "cvc-complex-type.2.4.a", //Invalid content was found starting with element ''{0}''. One of ''{1}'' is expected.
            "cvc-complex-type.2.4.b", //The content of element ''{0}'' is not complete. One of ''{1}'' is expected.
            "cvc-complex-type.3.2.2" //Attribute ''{1}'' is not allowed to appear in element ''{0}''
    };

    public static final ValidationEventHandler INSTANCE = new CxfSchemaValidationOffValidationEventHandler();

    private CxfSchemaValidationOffValidationEventHandler(){}

    @Override
    public boolean handleEvent(final ValidationEvent event) {
        if(event == null){
            throw new IllegalArgumentException("ValidationEvent must not be null");
        }

        final int severity = event.getSeverity();
        final String validationMessage = event.getMessage();

        if(severity == FATAL_ERROR){
            return false;
        }

        return severity < WARNING || StringUtils.indexOfAny(validationMessage, ALLOWABLE_SCHEMA_VALIDATION_ERRORS) != -1;
    }
}

