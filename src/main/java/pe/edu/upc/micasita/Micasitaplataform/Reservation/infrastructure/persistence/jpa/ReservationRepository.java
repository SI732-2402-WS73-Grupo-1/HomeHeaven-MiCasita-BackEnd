package pe.edu.upc.micasita.Micasitaplataform.Reservation.infrastructure.persistence.jpa;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.aggregates.Reservation;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    boolean existsById(@NonNull Long id);
    void deleteById(@NonNull Long id);
    @NonNull
    Optional<Reservation> findById(@NonNull Long id);
    @NonNull
    List<Reservation> findAll();
}
