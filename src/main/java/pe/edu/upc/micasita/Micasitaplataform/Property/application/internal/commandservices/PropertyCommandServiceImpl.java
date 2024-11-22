package pe.edu.upc.micasita.Micasitaplataform.Property.application.internal.commandservices;


import org.springframework.stereotype.Service;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.aggregate.Property;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.*;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.valueobjects.PropertyStatus;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.valueobjects.PropertyType;

import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.DeletePropertyCommand;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.UpdatePropertyCommand;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.AddPropertyCommand;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.services.PropertyCommandService;
import pe.edu.upc.micasita.Micasitaplataform.Property.infrastructure.persistence.jpa.repositories.PropertyRepository;

import java.util.Optional;

@Service

public class PropertyCommandServiceImpl implements PropertyCommandService {

    private final PropertyRepository propertyRepository;
    public PropertyCommandServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Long handle(AddPropertyCommand command) {
        var property = new Property(command);
        if (propertyRepository.existsByLocation(property.getLocation()) &&
                property.isStatusSale() &&
                property.isTypeHouse()) {
            throw new IllegalArgumentException("Property with same location already exists for sales");
        }

        try {
            Property savedProperty = propertyRepository.save(property);
            return savedProperty.getId();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving property: " + e.getMessage());
        }
    }

    @Override
    public Optional<Property> handle(UpdatePropertyCommand command) {
        /*if (propertyRepository.existsByLocationAndIdIsNot(command.location(), command.id())) {
            throw new IllegalArgumentException("Property with same location already exists");
        }*/
        var result = propertyRepository.findById(command.id());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Property does not exist");
        }
        var propertyToUpdate = result.get();
        try {
            var updatedProperty = propertyRepository.save(propertyToUpdate.updateInformation(
                    command.title(),
                    command.description(),
                    command.owner(),
                    command.price(),
                    command.location(),
                    command.status(),
                    command.type(),
                    command.currency(),
                    command.size(),
                    command.bedrooms(),
                    command.bathrooms(),
                    command.garageSpace(),
                    command.yearBuilt()
            ));
            return Optional.of(updatedProperty);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating property: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeletePropertyCommand command) {
        if (!propertyRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Property does not exist");
        }
        try{
            propertyRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting property: " + e.getMessage());
        }
    }

    @Override
    public void handle(ChangePropertyStatusCommand command) {
        propertyRepository.findById(command.id()).map(property -> {
            property.changeStatus(command.status());
            propertyRepository.save(property);
            return property;
        });
    }

    @Override
    public void handle(ChangePropertyPriceCommand command) {
        propertyRepository.findById(command.id()).map(property -> {
            property.changePrice(command.newPrice());
            propertyRepository.save(property);
            return property;
        });
    }

    @Override
    public void handle(ChangePropertyTypeCommand command) {
        propertyRepository.findById(command.id()).map(property -> {
            property.changeType(command.newType());
            propertyRepository.save(property);
            return property;
        });
    }

    @Override
    public void handle(ChangePropertyDescriptionCommand command) {
        propertyRepository.findById(command.id()).map(property -> {
            property.changeDescription(command.newDescription());
            propertyRepository.save(property);
            return property;
        });
    }

    @Override
    public void handle(ChangePropertyLocationCommand command) {
        if (propertyRepository.existsByLocationAndIdIsNot(command.newLocation(), command.id())) {
            throw new IllegalArgumentException("Ya existe otra propiedad con esta ubicación");
        }
        propertyRepository.findById(command.id()).map(property -> {
            property.changeLocation(command.newLocation());
            propertyRepository.save(property);
            return property;
        });
    }

    public boolean existsByLocation(String location) {
        return propertyRepository.existsByLocation(location);
    }
}