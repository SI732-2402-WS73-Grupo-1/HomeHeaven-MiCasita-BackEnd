package pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.commands;

import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.valueobjects.ReservationStatus;

import java.time.LocalDateTime;

public record CreateReservationCommand(Long userId,
                                       Long propertyId,
                                       LocalDateTime dateTime,
                                       ReservationStatus status) {
}
