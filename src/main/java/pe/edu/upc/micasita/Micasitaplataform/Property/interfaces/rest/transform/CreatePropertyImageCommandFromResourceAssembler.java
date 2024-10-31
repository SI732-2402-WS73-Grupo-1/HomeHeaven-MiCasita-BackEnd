package pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.transform;

import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.AddPropertyImageCommand;
import pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.resources.CreatePropertyImageResource;

public class CreatePropertyImageCommandFromResourceAssembler {
    public static AddPropertyImageCommand toCommandFromResource(CreatePropertyImageResource resource) {
        return new AddPropertyImageCommand(
                resource.url(),
                resource.propertyId()
        );

    }
}
