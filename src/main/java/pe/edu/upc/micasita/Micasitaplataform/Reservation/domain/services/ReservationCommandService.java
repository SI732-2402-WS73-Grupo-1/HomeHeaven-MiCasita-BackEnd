package pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.services;

import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.aggregates.Reservation;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.commands.ChangeDateTimeCommand;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.commands.ChangeStatusCommand;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.commands.DeleteReservationCommand;

import java.util.Optional;

public interface ReservationCommandService {
    Long handle(CreateReservationCommand command);
    Optional<Reservation> handle(DeleteReservationCommand command);
    void handle (ChangeStatusCommand command);
    void handle (ChangeDateTimeCommand command);
}
