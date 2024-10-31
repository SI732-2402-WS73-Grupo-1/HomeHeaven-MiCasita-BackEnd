package pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.transform;

import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands.UpdateBuyCommand;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources.UpdateBuyResource;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources.UpdateRentResource;

public class UpdateBuyCommandFromResourceAssembler {
    public static UpdateBuyCommand toCommandFromResource(Long intBuy, UpdateBuyResource resource) {
        return new UpdateBuyCommand(intBuy, resource.name(), resource.dni(), resource.phoneNumber(), resource.email()
        );
    }

}
