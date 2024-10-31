package pe.edu.upc.micasita.Micasitaplataform.Reservation.interfaces.rest.transform;

import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.interfaces.rest.resources.CreateReservationResource;

public class CreateReservationCommandFromResourceAssembler {
    public static CreateReservationCommand toCommandFromResource(CreateReservationResource resource){
        return new CreateReservationCommand(
                resource.userId(),
                resource.propertyId(),
                resource.dateTime(),
                resource.status()
        );
    }
}
