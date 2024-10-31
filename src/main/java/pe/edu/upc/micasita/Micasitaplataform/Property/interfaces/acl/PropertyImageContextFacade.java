package pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.*;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.aggregate.PropertyImage;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.queries.*;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.services.PropertyImageCommandService;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.services.PropertyImageQueryService;

import java.util.Optional;

@Service
public class PropertyImageContextFacade {
    private final PropertyImageCommandService propertyImageCommandService;
    private final PropertyImageQueryService propertyImageQueryService;

    public PropertyImageContextFacade(PropertyImageCommandService propertyImageCommandService, PropertyImageQueryService propertyImageQueryService) {
        this.propertyImageCommandService = propertyImageCommandService;
        this.propertyImageQueryService = propertyImageQueryService;
    }

    public Optional<PropertyImage> getPropertyImageById(Long propertyImageId) {
        var getPropertyImageByIdQuery = new GetPropertyImageByIdQuery(propertyImageId);
        return propertyImageQueryService.handle(getPropertyImageByIdQuery);
    }

    public Long createPropertyImage(AddPropertyImageCommand command) {
        return propertyImageCommandService.handle(command);
    }

    public Optional<PropertyImage> updatePropertyImage(UpdatePropertyImageCommand command) {
        return propertyImageCommandService.handle(command);
    }

    public void deletePropertyImage(DeletePropertyImageCommand command) {
        propertyImageCommandService.handle(command);
    }
}