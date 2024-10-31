package pe.edu.upc.micasita.Micasitaplataform.Reservation.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.aggregates.Reservation;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.commands.DeleteReservationCommand;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.queries.GetAllReservationQuery;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.queries.GetReservationByIdQuery;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.services.ReservationCommandService;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.services.ReservationQueryService;

import java.util.List;
import java.util.Optional;

@Service
public class ProfilesContextFacade {

    private final ReservationCommandService reservationCommandService;
    private final ReservationQueryService reservationQueryService;

    public ProfilesContextFacade(ReservationCommandService reservationCommandService, ReservationQueryService reservationQueryService) {
        this.reservationCommandService = reservationCommandService;
        this.reservationQueryService = reservationQueryService;
    }

    public Long createReservation(CreateReservationCommand command) {
        return reservationCommandService.handle(command);
    }

    public void deleteReservation(DeleteReservationCommand command) {
        reservationCommandService.handle(command);
    }

    public List<Reservation> getAllReservations() {
        return reservationQueryService.handle(new GetAllReservationQuery());
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationQueryService.handle(new GetReservationByIdQuery(id));
    }
}
