package pe.edu.upc.micasita.Micasitaplataform.Transaction.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.aggregate.Buy;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuyRepository extends JpaRepository<Buy, Long> {
    Optional<Buy> findBuyById(Long idBuy);
    Optional<Buy> findBuyByUserInfoNameAndUserInfoDni(String name, String dni);
    List<Buy> findAll();
    Optional<Buy> findBuyByIdPropertyAndUserInfoNameAndUserInfoDni(Long idProperty, String name, String dni);
}