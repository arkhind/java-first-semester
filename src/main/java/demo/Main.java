package demo;

import core.ValidationResult;
import core.Validator;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Валидация объекта с ошибками ---");
        final User userWithError = new User();
        userWithError.setName("A");
        userWithError.setEmail("invalid-email");
        userWithError.setAge(200);

        final ValidationResult resultWithError = Validator.validate(userWithError);

        if (!resultWithError.isValid()) {
            System.out.println("Ошибки валидации:");
            resultWithError.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Объект валиден.");
        }

        System.out.println("\n--- Валидация корректного объекта ---");
        final User userValid = new User();
        userValid.setName("Valid Name");
        userValid.setEmail("test@example.com");
        userValid.setAge(30);
        userValid.setPassword("StrongPass123");

        final ValidationResult resultValid = Validator.validate(userValid);
        if (!resultValid.isValid()) {
            System.out.println("Ошибки валидации:");
            resultValid.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Объект валиден.");
        }
    }
}