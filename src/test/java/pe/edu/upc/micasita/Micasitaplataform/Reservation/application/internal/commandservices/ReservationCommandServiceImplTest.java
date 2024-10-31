package pe.edu.upc.micasita.Micasitaplataform.Reservation.application.internal.commandservices;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.aggregates.Reservation;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.commands.ChangeStatusCommand;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.commands.DeleteReservationCommand;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.valueobjects.ReservationStatus;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.infrastructure.persistence.jpa.ReservationRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReservationCommandServiceImplTest {


    @Test
    void DeleteReservationCommandTestUnit() {
        // Configurar el mock del repositorio
        ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
        ReservationCommandServiceImpl reservationCommandService = new ReservationCommandServiceImpl(reservationRepository);

        // Crear una reserva existente
        Reservation existingReservation = new Reservation(1L, 1L, 1L, null, ReservationStatus.PENDING);
        when(reservationRepository.findById(any(Long.class))).thenReturn(Optional.of(existingReservation));
        when(reservationRepository.existsById(any(Long.class))).thenReturn(true);

        // Crear el comando
        DeleteReservationCommand command = new DeleteReservationCommand(1L);

        // Ejecutar el método
        Optional<Reservation> deletedReservation = reservationCommandService.handle(command);

        // Verificar el resultado
        assertNull(deletedReservation);
        verify(reservationRepository, times(1)).deleteById(1L);
    }

    @Test
    void ChangeStatusTestUnit() {
        // Configurar el mock del repositorio
        ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
        ReservationCommandServiceImpl reservationCommandService = new ReservationCommandServiceImpl(reservationRepository);

        // Crear una reserva existente
        Reservation existingReservation = new Reservation(1L, 1L, 1L, null, ReservationStatus.PENDING);
        when(reservationRepository.findById(any(Long.class))).thenReturn(Optional.of(existingReservation));

        // Crear el comando
        ChangeStatusCommand command = new ChangeStatusCommand(1L, ReservationStatus.CONFIRMED);

        // Ejecutar el método
        reservationCommandService.handle(command);

        // Verificar el resultado
        assertEquals(ReservationStatus.CONFIRMED, existingReservation.getStatus());
        verify(reservationRepository, times(1)).save(existingReservation);
    }
}