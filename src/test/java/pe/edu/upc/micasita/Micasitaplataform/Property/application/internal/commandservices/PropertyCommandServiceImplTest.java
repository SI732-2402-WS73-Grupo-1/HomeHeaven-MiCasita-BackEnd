package pe.edu.upc.micasita.Micasitaplataform.Property.application.internal.commandservices;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.aggregate.Property;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.AddPropertyCommand;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.UpdatePropertyCommand;

import pe.edu.upc.micasita.Micasitaplataform.Property.infrastructure.persistence.jpa.repositories.PropertyRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



class PropertyCommandServiceImplTest {

    @Test
    void handleAddPropertyCommandTest() {
        // Configurar el mock del repositorio
        PropertyRepository propertyRepository = Mockito.mock(PropertyRepository.class);
        PropertyCommandServiceImpl propertyCommandService = new PropertyCommandServiceImpl(propertyRepository);

        // Crear el comando
        AddPropertyCommand command = new AddPropertyCommand(
                "title", "description", "owner", "111111", "location", "Sale", "House", "currency", "120.0", 3, 2, 1, 2020);

        // Configurar el mock para el método existsByLocation
        when(propertyRepository.existsByLocation(command.location())).thenReturn(false);

        // Configurar el mock para el método save
        Property property = new Property(command);
        property.setId(1L);
        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        // Ejecutar el método handle para el comando
        Long propertyId = propertyCommandService.handle(command);

        // Verificar que se ha llamado a save una vez
        verify(propertyRepository, times(1)).save(any(Property.class));

        // Verificar que el ID de la propiedad no es null
        assertNotNull(propertyId);
    }
    @Test
    void UpdatePropertyTest() {
        PropertyRepository propertyRepository = Mockito.mock(PropertyRepository.class);
        PropertyCommandServiceImpl propertyCommandService = new PropertyCommandServiceImpl(propertyRepository);

        UpdatePropertyCommand command = new UpdatePropertyCommand(
                1L, "title", "description", "owner", "111111", "location", "Sale", "House", "currency", "120.0", 3, 2, 1, 2020);

        Property property = new Property();
        when(propertyRepository.findById(command.id())).thenReturn(Optional.of(property));
        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        propertyCommandService.handle(command);

        verify(propertyRepository, times(1)).save(any(Property.class));
    }


}