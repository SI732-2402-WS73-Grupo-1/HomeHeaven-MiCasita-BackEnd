package pe.edu.upc.micasita.Micasitaplataform.users.interfaces.rest.transform;

import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.commads.CreateUserCommand;
import pe.edu.upc.micasita.Micasitaplataform.users.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(
                resource.name(),
                resource.dni(),
                resource.email(),
                resource.password(),
                resource.phone(),
                resource.address()
        );
    }
}
