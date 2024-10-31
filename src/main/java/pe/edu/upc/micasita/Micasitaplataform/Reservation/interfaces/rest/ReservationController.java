package pe.edu.upc.micasita.Micasitaplataform.Reservation.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.commands.DeleteReservationCommand;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.queries.GetAllReservationQuery;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.queries.GetReservationByIdQuery;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.interfaces.acl.ProfilesContextFacade;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.interfaces.rest.resources.CreateReservationResource;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.interfaces.rest.resources.ReservationResource;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.interfaces.rest.transform.CreateReservationCommandFromResourceAssembler;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.interfaces.rest.transform.ReservationResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Reservation", description = "Reservation Management Endpoints")
public class ReservationController {
    private final ProfilesContextFacade profileContextFacade;

    public ReservationController(ProfilesContextFacade profileContextFacade) {
        this.profileContextFacade = profileContextFacade;
    }


    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationResource> getReservationById(@PathVariable Long reservationId){
        var getReservationByIdQuery = new GetReservationByIdQuery(reservationId);
        var reservation = profileContextFacade.getReservationById(reservationId);
        if(reservation.isEmpty())
            return ResponseEntity.badRequest().build();
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(reservation.get());
        return ResponseEntity.ok(reservationResource);
    }
    @GetMapping
    public ResponseEntity<List<ReservationResource>> getAllReservation(){
        var getAllReservationQuery = new GetAllReservationQuery();
        var reservations = profileContextFacade.getAllReservations();
        var reservationResources = reservations.stream()
                .map(ReservationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reservationResources);
    }
    //Add Reservation
    @PostMapping
    public ResponseEntity<ReservationResource>addReservation(@RequestBody CreateReservationResource resource){
        CreateReservationCommand createReservationCommand =
                CreateReservationCommandFromResourceAssembler.toCommandFromResource(resource);
        Long reservationId = profileContextFacade.createReservation(createReservationCommand);

        if(reservationId == 0){
            return ResponseEntity.badRequest().build();
        }
        var reservation = profileContextFacade.getReservationById(reservationId);

        if(reservation.isEmpty())
            return ResponseEntity.badRequest().build();
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(reservation.get());
        return new ResponseEntity<>(reservationResource, HttpStatus.CREATED);
    }
    //Delete Reservation
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long reservationId){
        var deleteReservationCommand = new DeleteReservationCommand(reservationId);
        profileContextFacade.deleteReservation(deleteReservationCommand);
        return ResponseEntity.ok("Reservation with given id successfully deleted");
    }
}
