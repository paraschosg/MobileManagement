package com.example.mobilemanagement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NetworkTechnologyValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidNetworkTechnology {
    String message() default "Μη έγκυρη δικτυακή τεχνολογία. Επιτρεπτές τιμές: GSM, HSPA, LTE, 3G, 4G, 5G.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
