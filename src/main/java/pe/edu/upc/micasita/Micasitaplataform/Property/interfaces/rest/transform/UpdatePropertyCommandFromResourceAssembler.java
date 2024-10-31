package pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.transform;

import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.aggregate.Property;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.UpdatePropertyCommand;
import pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.resources.UpdatePropertyResource;

public class UpdatePropertyCommandFromResourceAssembler {
    public static UpdatePropertyCommand toCommandFromResource(Long propertyId,UpdatePropertyResource resource) {
        return new UpdatePropertyCommand(
                propertyId,
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