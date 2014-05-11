package gcs.webapp.aspects;

// import gcs.webapp.utils.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

/**
 * @author Simon Turcotte-Langevin
 */
// @Aspect
public class ValidationAspect
{
    /** Validator to validate objects before methods execution. */
    private Validator validator;

    /**
     * Validates arguments of all service methods.
     * 
     * @param joinPoint
     * @throws Exception
     */
    // @Before("execution(* gcs.webservices.services.*.*(..)) || execution(* gcs.webservices.client.*.*(..))")
    /*public void beforeServiceMethods(JoinPoint joinPoint) throws Exception
    {
        Object[] arguments = joinPoint.getArgs();

        int iArg = 0;
        List<String> validatonMessageKeys = new ArrayList<>();
        for (Object argument : arguments) {
            Errors errors = new MapBindingResult(new HashMap<Object, Object>(), "arg" + (iArg + 1));
            validator.validate(argument, errors);
            for (ObjectError error : errors.getAllErrors()) {
                validatonMessageKeys.add(error.getDefaultMessage());
            }
        }

        if (!validatonMessageKeys.isEmpty()) {
            throw new Exception("Pas valide criss d'épais");
            // throw new ValidationException(validatonMessageKeys);
        }
    }*/

    /**
     * @return the validator
     */
    public Validator getValidator()
    {
        return validator;
    }

    /**
     * @param validator the validator to set
     */
    public void setValidator(Validator validator)
    {
        this.validator = validator;
    }
}
