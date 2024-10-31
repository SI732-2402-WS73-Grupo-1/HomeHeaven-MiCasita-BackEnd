package pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.commands;

import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.valueobjects.ReservationStatus;

public record ChangeStatusCommand(Long id, ReservationStatus status) {
}
