package gcs.webapp.utils.aspects.validation;

import gcs.webapp.utils.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

/**
 * @author Simon Turcotte-Langevin
 */
@Aspect
public class ValidationAspect
{
    /** Validator to validate objects before methods execution. */
    private Validator validator;

    @Pointcut("within(@gcs.webapp.utils.aspects.validation.Validatable *)")
    public void validatableType() {}
    
    @Pointcut("execution(@gcs.webapp.utils.aspects.validation.Validatable * *(..))")
    public void validatableMethod() {}

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {}
    
    @Before("validatableMethod()")
    public void beforeAnnotatedMethods(JoinPoint joinPoint) throws Throwable
    {
        validateThenProceed(joinPoint);
    }

    @Before("validatableType() && publicMethod()")
    public void beforeAnnotatedTypes(JoinPoint joinPoint) throws Throwable
    {
        validateThenProceed(joinPoint);
    }
    
    /**
     * Validates arguments of all service methods.
     * 
     * @param joinPoint
     * @throws Exception
     */
    // @Before("execution(* gcs.webservices.services..*(..))")
    private void validateThenProceed(JoinPoint joinPoint) throws Exception
    {
        Object[] arguments = joinPoint.getArgs();
        
        // If woven into a method that has no argument, getArgs() returns null.
        /*if (arguments == null || arguments.length == 0) {
            return;
        }*/

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
            throw new ValidationException(validatonMessageKeys);
        }
    }
    
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
