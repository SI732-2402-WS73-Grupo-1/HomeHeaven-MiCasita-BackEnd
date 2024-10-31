package pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.services;

import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.aggregates.Reservation;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.queries.GetAllReservationQuery;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.queries.GetReservationByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReservationQueryService {
    Optional<Reservation>handle(GetReservationByIdQuery query);
    List<Reservation> handle(GetAllReservationQuery query);
}
