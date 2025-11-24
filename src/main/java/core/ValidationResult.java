package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidationResult {
    private final boolean isValid;
    private final List<String> errors;

    public ValidationResult() {
        this(true, new ArrayList<>());
    }

    private ValidationResult(boolean isValid, List<String> errors) {
        this.isValid = isValid;
        this.errors = errors;
    }

    public boolean isValid() {
        return isValid;
    }

    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public static ValidationResult addError(ValidationResult currentResult, String error) {
        if (currentResult == null) {
            currentResult = new ValidationResult();
        }
        List<String> newErrors = new ArrayList<>(currentResult.errors);
        newErrors.add(error);
        return new ValidationResult(false, newErrors);
    }
}