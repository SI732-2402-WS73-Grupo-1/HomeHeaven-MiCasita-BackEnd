package pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.transform;

import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands.UpdateRentCommand;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources.UpdateRentResource;

public class UpdateRentCommandFromResourceAssembler {
    public static UpdateRentCommand toCommandFromResource(Long intRent, UpdateRentResource resource) {
        return new UpdateRentCommand(intRent, resource.name(), resource.dni(), resource.phoneNumber(), resource.email());
    }
}
