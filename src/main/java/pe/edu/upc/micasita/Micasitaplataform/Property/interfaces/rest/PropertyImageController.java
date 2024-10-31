package pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.commands.*;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.queries.GetPropertyImageByIdQuery;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.model.queries.GetPropertyImageByPropertyIdQuery;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.services.PropertyImageCommandService;
import pe.edu.upc.micasita.Micasitaplataform.Property.domain.services.PropertyImageQueryService;
import pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.resources.CreatePropertyImageResource;
import pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.resources.PropertyImageResource;
import pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.resources.UpdatePropertyImageResource;
import pe.edu.upc.micasita.Micasitaplataform.Property.interfaces.rest.transform.*;


@RestController
@RequestMapping(value = "/api/v1/propertyImages", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "PropertyImages", description = "Property Image Management Endpoints")
public class PropertyImageController {
    private final PropertyImageCommandService propertyImageCommandService;
    private final PropertyImageQueryService propertyImageQueryService;

    public PropertyImageController(PropertyImageCommandService propertyImageCommandService,
                                   PropertyImageQueryService propertyImageQueryService) {
        this.propertyImageCommandService = propertyImageCommandService;
        this.propertyImageQueryService = propertyImageQueryService;

    }
    @GetMapping("/property/{propertyId}")
    public ResponseEntity<PropertyImageResource> getPropertyImageByPropertyId(@PathVariable Long propertyId) {
        var getPropertyImageByPropertyIdQuery = new GetPropertyImageByPropertyIdQuery(propertyId);
        var propertyImage = propertyImageQueryService.handle(getPropertyImageByPropertyIdQuery);

        if (propertyImage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var propertyImageResource = PropertyImageResourceFromEntityAssembler.toResourceFromEntity(propertyImage.get());
        return ResponseEntity.ok(propertyImageResource);
    }
    // Get PropertyImage
    @GetMapping("/{propertyImageId}")
    public ResponseEntity<PropertyImageResource> getPropertyImage(@PathVariable Long propertyImageId) {
        var getPropertyImageByIdQuery = new GetPropertyImageByIdQuery(propertyImageId);
        var propertyImage = propertyImageQueryService.handle(getPropertyImageByIdQuery);

        if (propertyImage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var propertyImageResource = PropertyImageResourceFromEntityAssembler.toResourceFromEntity(propertyImage.get());
        return ResponseEntity.ok(propertyImageResource);
    }

    //Add PropertyImage
    @PostMapping
    public ResponseEntity<PropertyImageResource> addPropertyImage(@RequestBody CreatePropertyImageResource resource) {
        AddPropertyImageCommand addPropertyImageCommand =
                CreatePropertyImageCommandFromResourceAssembler.toCommandFromResource(resource);
        Long propertyImageId = propertyImageCommandService.handle(addPropertyImageCommand);
        if (propertyImageId == 0) {
            return ResponseEntity.badRequest().build();
        }
        var getPropertyImageByIdQuery = new GetPropertyImageByIdQuery(propertyImageId);
        var propertyImage = propertyImageQueryService.handle(getPropertyImageByIdQuery);

        if (propertyImage.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var propertyImageResource = PropertyImageResourceFromEntityAssembler.
                toResourceFromEntity(propertyImage.get());
        return new ResponseEntity<>(propertyImageResource, HttpStatus.CREATED);
    }

    //Update PropertyImage
    @PutMapping("/{propertyImageId}")
    public ResponseEntity<PropertyImageResource> updatePropertyImage(@PathVariable Long propertyImageId, @RequestBody UpdatePropertyImageResource resource) {
        var updatePropertyImageCommand = UpdatePropertyImageCommandFromResourceAssembler.toCommandFromResource(propertyImageId, resource);
        var updatedPropertyImage = propertyImageCommandService.handle(updatePropertyImageCommand);
        if (updatedPropertyImage.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var propertyImageResource = PropertyImageResourceFromEntityAssembler.toResourceFromEntity(updatedPropertyImage.get());
        return ResponseEntity.ok(propertyImageResource);
    }

    //Delete PropertyImage
    @DeleteMapping("/{propertyImageId}")
    public ResponseEntity<?> deletePropertyImage(@PathVariable Long propertyImageId) {
        var deletePropertyImageCommand = new DeletePropertyImageCommand(propertyImageId);
        propertyImageCommandService.handle(deletePropertyImageCommand);
        return ResponseEntity.ok("PropertyImage with given id successfully deleted");
    }
}