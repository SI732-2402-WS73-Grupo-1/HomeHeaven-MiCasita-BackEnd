package pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.transform;

import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands.CreateRentCommand;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources.CreateRentResource;

public class CreateRentCommandFromResourceAssembler {
public static CreateRentCommand toCommandFromResource(CreateRentResource resource) {
        return new CreateRentCommand(
                resource.name(),
                resource.dni(),
                resource.phoneNumber(),
                resource.email(),
                resource.rentAmount(),
                resource.idProperty()
        );
    }
}
