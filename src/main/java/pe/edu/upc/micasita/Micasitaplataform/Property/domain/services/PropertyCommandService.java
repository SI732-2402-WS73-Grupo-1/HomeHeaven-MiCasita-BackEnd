package pe.edu.upc.micasita.Micasitaplataform.Property.domain.services;


import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.aggregate.Property;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.*;

import java.util.Optional;

public interface PropertyCommandService {
    Long handle(AddPropertyCommand command);

    Optional<Property> handle(UpdatePropertyCommand command);

    void handle(DeletePropertyCommand command);

    void handle(ChangePropertyStatusCommand command);

    void handle(ChangePropertyPriceCommand command);

    void handle(ChangePropertyTypeCommand command);

    void handle(ChangePropertyDescriptionCommand command);


    void handle(ChangePropertyLocationCommand command);
}












