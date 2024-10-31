package pe.edu.upc.micasita.Micasitaplataform.Reservation.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.aggregates.Reservation;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.commands.ChangeDateTimeCommand;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.commands.ChangeStatusCommand;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.commands.DeleteReservationCommand;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.services.ReservationCommandService;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.infrastructure.persistence.jpa.ReservationRepository;

import java.util.Optional;

@Service
public class ReservationCommandServiceImpl implements ReservationCommandService {

    private final ReservationRepository reservationRepository;

    public ReservationCommandServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    @Override
    public Long handle(CreateReservationCommand command) {
        var reservation = new Reservation(command);
        try {
            reservationRepository.save(reservation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving reservation: " + e.getMessage());
        }
        reservationRepository.save(reservation);
        return reservation.getId();
    }

    @Override
    public Optional<Reservation> handle(DeleteReservationCommand command) {
        if (!reservationRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Reservation does not exist");
        }
        try {
            reservationRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting reservation: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void handle(ChangeStatusCommand command) {
        reservationRepository.findById(command.id()).map(reservation->{
            reservation.changeStatus(command.status());
            reservationRepository.save(reservation);
            return reservation;
        });
    }

    @Override
    public void handle(ChangeDateTimeCommand command) {
        reservationRepository.findById(command.id()).map(reservation->{
            reservation.changeDateTime(command.dateTime());
            reservationRepository.save(reservation);
            return reservation;
        });
    }
}
