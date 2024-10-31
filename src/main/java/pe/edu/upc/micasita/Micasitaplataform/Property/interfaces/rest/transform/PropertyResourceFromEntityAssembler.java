package pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.transform;

import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.aggregate.Property;
import pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.resources.PropertyResource;

public class PropertyResourceFromEntityAssembler {
    public static PropertyResource toResourceFromEntity(Property entity) {
        return new PropertyResource(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getOwner(),
                entity.getPrice(),
                entity.getLocation(),
                entity.getStatus(),
                entity.getType(),
                entity.getCurrency(),
                entity.getSize(),
                entity.getBedrooms(),
                entity.getBathrooms(),
                entity.getGarageSpace(),
                entity.getYearBuilt()
        );
    }
}