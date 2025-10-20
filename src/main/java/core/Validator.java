package core;

import annotations.Email;
import annotations.NotNull;
import annotations.Range;
import annotations.Size;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class Validator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");

    public static ValidationResult validate(Object object) {
        ValidationResult result = new ValidationResult();

        if (object == null) {
            return ValidationResult.addError(result, "Object cannot be null for validation.");
        }

        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                result = validateField(field, value, result);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static ValidationResult validateField(Field field, Object value, ValidationResult currentResult) {
        Annotation[] annotations = field.getDeclaredAnnotations();

        for (Annotation annotation : annotations) {
            String errorMessage = null;

            if (annotation instanceof NotNull) {
                if (value == null) {
                    errorMessage = ((NotNull) annotation).message();
                }
            } else if (annotation instanceof Size) {
                Size sizeAnnotation = (Size) annotation;
                if (value instanceof String) {
                    String strValue = (String) value;
                    if (strValue != null && (strValue.length() < sizeAnnotation.min() || strValue.length() > sizeAnnotation.max())) {
                        errorMessage = sizeAnnotation.message();
                    }
                }
            } else if (annotation instanceof Range) {
                Range rangeAnnotation = (Range) annotation;
                if (value instanceof Number) {
                    long longValue = ((Number) value).longValue();
                    if (longValue < rangeAnnotation.min() || longValue > rangeAnnotation.max()) {
                        errorMessage = rangeAnnotation.message();
                    }
                }
            } else if (annotation instanceof Email) {
                if (value instanceof String) {
                    String strValue = (String) value;
                    if (strValue != null && !EMAIL_PATTERN.matcher(strValue).matches()) {
                        errorMessage = ((Email) annotation).message();
                    }
                }
            }

            if (errorMessage != null) {
                currentResult = ValidationResult.addError(currentResult, field.getName() + ": " + errorMessage);
            }
        }
        return currentResult;
    }
}