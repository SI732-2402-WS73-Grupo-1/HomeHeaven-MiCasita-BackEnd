package pe.edu.upc.micasita.Micasitaplataform.Property.domain.services;

import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.*;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.aggregate.PropertyImage;

import java.util.Optional;

public interface PropertyImageCommandService {
    Long handle(AddPropertyImageCommand command);
    Optional<PropertyImage> handle(UpdatePropertyImageCommand command);
    void handle(DeletePropertyImageCommand command);
}
