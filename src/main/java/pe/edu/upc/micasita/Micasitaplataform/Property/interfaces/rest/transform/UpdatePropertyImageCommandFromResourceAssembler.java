package pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.transform;

import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.UpdatePropertyCommand;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.UpdatePropertyImageCommand;
import pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.resources.UpdatePropertyImageResource;
import pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.resources.UpdatePropertyResource;

public class UpdatePropertyImageCommandFromResourceAssembler {
    public static UpdatePropertyImageCommand toCommandFromResource(Long propertyImageId, UpdatePropertyImageResource resource) {
        return new UpdatePropertyImageCommand(
                propertyImageId,
                resource.url(),
                resource.propertyId()
        );
    }
}
