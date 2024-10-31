package pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.transform;

import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.AddPropertyCommand;
import pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.resources.CreatePropertyResource;

public class CreatePropertyCommandFromResourceAssembler {
    public static AddPropertyCommand toCommandFromResource(CreatePropertyResource resource) {
        return new AddPropertyCommand(
                resource.title(),
                resource.description(),
                resource.owner(),
                resource.price(),
                resource.location(),
                resource.status(),
                resource.type(),
                resource.currency(),
                resource.size(),
                resource.bedrooms(),
                resource.bathrooms(),
                resource.garageSpace(),
                resource.yearBuilt()
        );
    }
}