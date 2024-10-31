package pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.transform;

import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.aggregate.Rent;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources.RentResource;

public class RentResourceFromEntityAssembler {
    public static RentResource toResourceFromEntity(Rent entity) {
        return new RentResource(entity.getId(), entity.getUserInfo().name(), entity.getUserInfo().dni(),
                entity.getUserInfo().phoneNumber(), entity.getUserInfo().email(), entity.getRentAmount(), entity.getIdProperty()
        );
    }
}
