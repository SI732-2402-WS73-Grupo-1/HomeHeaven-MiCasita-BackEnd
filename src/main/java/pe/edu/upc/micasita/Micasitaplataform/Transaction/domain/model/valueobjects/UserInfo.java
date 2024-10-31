package pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;


@Embeddable
public record UserInfo(
        String name,
        String dni,
        String phoneNumber,
        @Email String email) {

    public UserInfo() {
        this(null, null, null, null);
    }

    public UserInfo {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (dni == null || dni.length() != 8) {
            throw new IllegalArgumentException("DNI must be exactly 8 characters long");
        }
        if (phoneNumber == null || phoneNumber.length() != 9) {
            throw new IllegalArgumentException("Phone number must be exactly 9 digits long");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
    }
}