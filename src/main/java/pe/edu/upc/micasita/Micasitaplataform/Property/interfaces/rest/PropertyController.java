package pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.AddPropertyCommand;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.DeletePropertyCommand;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.queries.GetAllPropertiesQuery;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.queries.GetPropertyByIdQuery;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.services.PropertyCommandService;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.services.PropertyQueryService;
import pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.resources.CreatePropertyResource;
import pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.resources.PropertyResource;
import pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.resources.UpdatePropertyResource;
import pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.transform.CreatePropertyCommandFromResourceAssembler;
import pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.transform.PropertyResourceFromEntityAssembler;
import pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.transform.UpdatePropertyCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/properties", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Properties", description = "Property Management Endpoints")
public class PropertyController {
    private final PropertyCommandService propertyCommandService;
    private final PropertyQueryService propertyQueryService;

    public PropertyController(PropertyCommandService propertyCommandService, PropertyQueryService propertyQueryService) {
        this.propertyCommandService = propertyCommandService;
        this.propertyQueryService = propertyQueryService;
    }
    //Get Property by ID
    @GetMapping("/{propertyId}")
    public ResponseEntity<PropertyResource> getPropertyById(@PathVariable Long propertyId) {
        var getPropertyByIdQuery = new GetPropertyByIdQuery(propertyId);
        var property = propertyQueryService.handle(getPropertyByIdQuery);
        if (property.isEmpty())
            return ResponseEntity.badRequest().build();
        var propertyResource = PropertyResourceFromEntityAssembler.toResourceFromEntity(property.get());
        return ResponseEntity.ok(propertyResource);
    }
    //Get Property all Properties
    @GetMapping
    public ResponseEntity<List<PropertyResource>> getAllProperties() {
        var getAllPropertiesQuery = new GetAllPropertiesQuery();
        var properties = propertyQueryService.handle(getAllPropertiesQuery);
        var propertyResources = properties.stream()
                .map(PropertyResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(propertyResources);
    }
    //Add Property
    @PostMapping
    public ResponseEntity<PropertyResource> addProperty(@RequestBody CreatePropertyResource resource) {
        AddPropertyCommand addPropertyCommand = CreatePropertyCommandFromResourceAssembler.toCommandFromResource(resource);
        Long propertyId = propertyCommandService.handle(addPropertyCommand);

        if (propertyId == 0) {
            return ResponseEntity.badRequest().build();
        }
        var getPropertyByIdQuery = new GetPropertyByIdQuery(propertyId);
        var property = propertyQueryService.handle(getPropertyByIdQuery);

        if (property.isEmpty())
            return ResponseEntity.badRequest().build();
        var propertyResource = PropertyResourceFromEntityAssembler.toResourceFromEntity(property.get());
        return new ResponseEntity<>(propertyResource, HttpStatus.CREATED);
    }
    //Update Property
    @PutMapping("/{propertyId}")
    public ResponseEntity<PropertyResource> updateProperty(@PathVariable Long propertyId, @RequestBody UpdatePropertyResource resource) {
        var updatePropertyCommand = UpdatePropertyCommandFromResourceAssembler.toCommandFromResource(propertyId,resource);
        var updatedProperty = propertyCommandService.handle(updatePropertyCommand);
        if (updatedProperty.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var propertyResource = PropertyResourceFromEntityAssembler.toResourceFromEntity(updatedProperty.get());
        return ResponseEntity.ok(propertyResource);
    }
    //Delete Property
    @DeleteMapping("/{propertyId}")
    public ResponseEntity<?> deleteProperty(@PathVariable Long propertyId) {
        var deletePropertyCommand = new DeletePropertyCommand(propertyId);
        propertyCommandService.handle(deletePropertyCommand);
        return ResponseEntity.ok("Property with given id successfully deleted");
    }
}