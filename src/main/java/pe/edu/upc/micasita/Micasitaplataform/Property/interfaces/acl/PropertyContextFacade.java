package pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.aggregate.Property;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.*;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.queries.*;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.services.PropertyCommandService;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.services.PropertyQueryService;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyContextFacade {
    private final PropertyCommandService propertyCommandService;
    private final PropertyQueryService propertyQueryService;

    public PropertyContextFacade(PropertyCommandService propertyCommandService, PropertyQueryService propertyQueryService) {
        this.propertyCommandService = propertyCommandService;
        this.propertyQueryService = propertyQueryService;
    }

    public Optional<Property> getPropertyById(Long propertyId) {
        var getPropertyByIdQuery = new GetPropertyByIdQuery(propertyId);
        return propertyQueryService.handle(getPropertyByIdQuery);
    }

    public List<Property> getAllProperties() {
        var getAllPropertiesQuery = new GetAllPropertiesQuery();
        return propertyQueryService.handle(getAllPropertiesQuery);
    }

    public Long createProperty(AddPropertyCommand command) {
        return propertyCommandService.handle(command);
    }

    public Optional<Property> updateProperty(UpdatePropertyCommand command) {
        return propertyCommandService.handle(command);
    }

    public void deleteProperty(DeletePropertyCommand command) {
        propertyCommandService.handle(command);
    }
}