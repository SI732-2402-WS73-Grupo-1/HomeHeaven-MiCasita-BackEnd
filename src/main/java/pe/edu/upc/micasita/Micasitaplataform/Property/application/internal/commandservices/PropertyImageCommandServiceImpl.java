package pe.edu.upc.micasita.Micasitaplataform.Property.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.AddPropertyImageCommand;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.DeletePropertyImageCommand;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.UpdatePropertyImageCommand;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.aggregate.PropertyImage;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.services.PropertyImageCommandService;
import pe.edu.upc.micasita.Micasitaplataform.Property.infrastructure.persistence.jpa.repositories.PropertyImageRepository;

import java.util.Optional;

@Service
public class PropertyImageCommandServiceImpl implements PropertyImageCommandService {

    private final PropertyImageRepository propertyImageRepository;

    public PropertyImageCommandServiceImpl(PropertyImageRepository propertyImageRepository) {
        this.propertyImageRepository = propertyImageRepository;
    }

    @Override
    public Long handle(AddPropertyImageCommand command) {
        var propertyImage = new PropertyImage(command);
        try {
            propertyImageRepository.save(propertyImage);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving property: " + e.getMessage());
        }
        propertyImageRepository.save(propertyImage);
        return propertyImage.getId();
    }

    @Override
    public Optional<PropertyImage> handle(UpdatePropertyImageCommand command) {
        var result = propertyImageRepository.findById(command.id());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("PropertyImage does not exist");
        }
        var propertyImageToUpdate = result.get();
        try{
            var updatedPropertyImage = propertyImageRepository.
                    save(propertyImageToUpdate.updateImage(
                            command.url(),
                            command.propertyId()
                    ));
            return Optional.of(updatedPropertyImage);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating property: " + e.getMessage());
        }

    }

    @Override
    public void handle(DeletePropertyImageCommand command) {
        if (!propertyImageRepository.existsById(command.id())) {
            throw new IllegalArgumentException("PropertyImage does not exist");
        }
        try{
            propertyImageRepository.deleteById(command.id());
        }catch (Exception e){
            throw new IllegalArgumentException("Error while deleting property: " + e.getMessage());
        }
    }
}