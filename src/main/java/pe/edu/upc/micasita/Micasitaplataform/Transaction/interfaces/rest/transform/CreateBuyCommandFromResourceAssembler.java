package pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.transform;

import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands.CreateBuyCommand;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources.CreateBuyResource;

public class CreateBuyCommandFromResourceAssembler {
    public static CreateBuyCommand toCommandFromResource(CreateBuyResource resource) {
        return new CreateBuyCommand(
                resource.name(),
                resource.dni(),
                resource.phoneNumber(),
                resource.email(),
                resource.buyAmount(),
                resource.idProperty()
        );
    }
}
