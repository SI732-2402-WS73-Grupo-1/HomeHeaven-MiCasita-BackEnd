package pe.edu.upc.micasita.Micasitaplataform.users.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.micasita.Micasitaplataform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.commads.CreateUserCommand;

import java.util.Date;

@Setter
@Getter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotEmpty
    @Column(nullable = false)
    private String dni;

    @NotEmpty
    @Column(nullable = false)
    @Email
    private String email;

    @NotEmpty
    @Column(nullable = false)
    private String password;

    @NotEmpty
    @Column(nullable = false)
    private String phone;

    @NotEmpty
    @Column(nullable = false)
    private String address;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public User() {
    }

    public User(Long id, String name, String dni, String email, String password, String phone, String address) {
        this.id = id;
        this.name = name;
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public User(CreateUserCommand command) {
        this.name = command.name();
        this.dni = command.dni();
        this.email = command.email();
        this.password = command.password();
        this.phone = command.phone();
        this.address = command.address();
    }

    public User update(String name, String dni, String email, String password, String phone, String address) {
        // update the user properties
        this.name = name;
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;

        // return the updated user
        return this;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}
