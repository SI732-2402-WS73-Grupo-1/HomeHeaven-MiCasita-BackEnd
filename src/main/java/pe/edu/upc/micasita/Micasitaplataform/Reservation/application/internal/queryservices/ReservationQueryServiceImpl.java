package pe.edu.upc.micasita.Micasitaplataform.Reservation.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.aggregates.Reservation;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.queries.GetAllReservationQuery;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.queries.GetReservationByIdQuery;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.services.ReservationQueryService;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.infrastructure.persistence.jpa.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationQueryServiceImpl implements ReservationQueryService {
    private final ReservationRepository reservationRepository;

    public ReservationQueryServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    @Override
    public Optional<Reservation> handle(GetReservationByIdQuery query) {
        return reservationRepository.findById(query.reservationId());
    }

    @Override
    public List<Reservation> handle(GetAllReservationQuery query) {
        return reservationRepository.findAll();
    }
}
