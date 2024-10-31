package pe.edu.upc.micasita.Micasitaplataform.Property.domain.services;


import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.aggregate.Property;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface PropertyQueryService {
    Optional<Property> handle(GetPropertyByLocationQuery query);
    Optional<Property> handle(GetPropertyByStatusQuery query);
    Optional<Property> handle(GetPropertyByTypeQuery query);
    Optional<Property> handle(GetPropertyByBedroomsQuery query);
    Optional<Property> handle(GetPropertyByBathroomsQuery query);
    Optional<Property> handle(GetPropertyByGarageSpaceQuery query);
    Optional<Property> handle(GetPropertyByYearBuiltQuery query);
    Optional<Property> handle(GetPropertyByIdQuery query);
    List<Property> handle(GetAllPropertiesQuery query);
}