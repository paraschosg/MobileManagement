package com.example.mobilemanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NetworkTechnologyValidator implements ConstraintValidator<ValidNetworkTechnology, String> {

    private final Set<String> allowed = new HashSet<>(Arrays.asList("GSM", "HSPA", "LTE", "3G", "4G", "5G"));

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) return false;

        String[] values = value.split(",");
        for (String v : values) {
            if (!allowed.contains(v.trim().toUpperCase())) {
                return false;
            }
        }
        return true;
    }
}
