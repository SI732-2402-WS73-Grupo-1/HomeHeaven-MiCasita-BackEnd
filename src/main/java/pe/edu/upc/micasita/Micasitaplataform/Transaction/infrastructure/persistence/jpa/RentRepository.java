package pe.edu.upc.micasita.Micasitaplataform.Transaction.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.aggregate.Rent;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
    Optional<Rent> findRentById(Long idRent);
    Optional<Rent> findRentByUserInfoNameAndUserInfoDniAndUserInfoPhoneNumber(String name, String dni, String phoneNumber);
    List<Rent> findAll();
    Optional<Rent> findRentByIdPropertyAndUserInfoNameAndUserInfoDni(Long idProperty, String name, String dni);
}