package pe.edu.upc.micasita.Micasitaplataform.users.infrastructure.persistence.jpa.repositories;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String phone);
    Optional<User> findByNameAndDni(String name, String dni);
    boolean existsByPhone(String phone);
    boolean existsByNameAndDni(String name, String dni);
    void deleteById(@NonNull Long id);
}
