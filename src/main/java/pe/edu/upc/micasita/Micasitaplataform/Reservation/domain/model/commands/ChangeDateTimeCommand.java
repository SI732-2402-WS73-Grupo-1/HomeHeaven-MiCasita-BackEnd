package pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.commands;

import java.time.LocalDateTime;

public record ChangeDateTimeCommand(Long id, LocalDateTime dateTime) {
}
