package spr22533.bug;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = CustomValidator.class)
public @interface Buggy {
    String message() default "{org.hibernate.validator.referenceguide.chapter06.CheckCase." +
            "message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
