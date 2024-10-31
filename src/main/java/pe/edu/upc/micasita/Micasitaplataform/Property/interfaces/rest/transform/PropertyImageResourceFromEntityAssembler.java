package pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.transform;

import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.aggregate.PropertyImage;
import pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.resources.PropertyImageResource;

public class PropertyImageResourceFromEntityAssembler {
    public static PropertyImageResource toResourceFromEntity(PropertyImage entity) {
        return new PropertyImageResource(
                entity.getId(),
                entity.getUrl(),
                entity.getPropertyId()
        );
    }
}
