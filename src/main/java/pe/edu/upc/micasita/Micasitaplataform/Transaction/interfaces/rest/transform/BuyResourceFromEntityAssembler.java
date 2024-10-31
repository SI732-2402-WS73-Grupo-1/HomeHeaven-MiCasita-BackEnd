package pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.transform;

import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.aggregate.Buy;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources.BuyResource;

public class BuyResourceFromEntityAssembler {
    public static BuyResource toResourceFromEntity(Buy entity) {
        return new BuyResource(entity.getId(), entity.getUserInfo().name(), entity.getUserInfo().dni(),
                entity.getUserInfo().phoneNumber(), entity.getUserInfo().email(), entity.getBuyAmount(),entity.getIdProperty());
    }
}
