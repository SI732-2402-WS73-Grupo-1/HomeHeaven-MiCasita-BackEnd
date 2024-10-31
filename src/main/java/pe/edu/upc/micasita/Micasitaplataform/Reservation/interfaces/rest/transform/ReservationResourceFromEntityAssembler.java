package pe.edu.upc.micasita.Micasitaplataform.Reservation.interfaces.rest.transform;

import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.aggregates.Reservation;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.interfaces.rest.resources.ReservationResource;

public class ReservationResourceFromEntityAssembler {
    public static ReservationResource toResourceFromEntity(Reservation entity)  {
        return new ReservationResource(
                entity.getId(),
                entity.getUserId(),
                entity.getPropertyId(),
                entity.getDateTime(),
                entity.getStatus()
        );
    }
}
