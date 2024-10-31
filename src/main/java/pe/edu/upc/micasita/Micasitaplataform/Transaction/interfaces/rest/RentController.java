package pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetAllRentQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetRentByIdQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetRentByUserInfoNameAndUserInfoDniAndUserInfoPhoneNumberQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services.RentCommandService;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services.RentQueryService;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources.CreateRentResource;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources.RentResource;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources.UpdateRentResource;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.transform.CreateRentCommandFromResourceAssembler;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.transform.RentResourceFromEntityAssembler;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.transform.UpdateRentCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/rents", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Rent", description = "Rent Management Endpoints")
public class RentController {
    private final RentCommandService rentCommandService;
    private final RentQueryService rentQueryService;

    public RentController(RentCommandService rentCommandService, RentQueryService rentQueryService) {
        this.rentCommandService = rentCommandService;
        this.rentQueryService = rentQueryService;
    }

    @PostMapping
    public ResponseEntity<RentResource> createRent(@RequestBody CreateRentResource createRentResource) {
        var createRentCommand = CreateRentCommandFromResourceAssembler.toCommandFromResource(createRentResource);
        var rentId = rentCommandService.handle(createRentCommand);

        if (rentId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getRentByIdQuery = new GetRentByIdQuery(rentId);
        var rent = rentQueryService.handle(getRentByIdQuery);

        if (rent.isEmpty())
            return ResponseEntity.badRequest().build();
        var rentResource = RentResourceFromEntityAssembler.toResourceFromEntity(rent.get());
        return new ResponseEntity<>(rentResource, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentResource> getRentById(@PathVariable Long id) {
        var getRentByIdQuery = new GetRentByIdQuery(id);
        var rent = rentQueryService.handle(getRentByIdQuery);

        if (rent.isEmpty())
            return ResponseEntity.notFound().build();
        var rentResource = RentResourceFromEntityAssembler.toResourceFromEntity(rent.get());
        return ResponseEntity.ok(rentResource);
    }

    @GetMapping
    public ResponseEntity<List<RentResource>> getAllRents() {
        var getAllRentQuery = new GetAllRentQuery();
        var rents = rentQueryService.handle(getAllRentQuery).stream()
                .map(RentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(rents);
    }

    @GetMapping("/byUser")
    public ResponseEntity<List<RentResource>> getRentByUser(@RequestParam String name, @RequestParam String dni, @RequestParam String phoneNumber) {
        var getRentByUserQuery = new GetRentByUserInfoNameAndUserInfoDniAndUserInfoPhoneNumberQuery(name, dni, phoneNumber);
        var rents = rentQueryService.handle(getRentByUserQuery).stream()
                .map(RentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(rents);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RentResource> updateRent(@PathVariable Long id, @RequestBody UpdateRentResource updateRentResource) {
        var updateRentCommand = UpdateRentCommandFromResourceAssembler.toCommandFromResource(id, updateRentResource);
        var rentUpdated = rentCommandService.handle(updateRentCommand);

        if (!rentUpdated) {
            return ResponseEntity.notFound().build();
        }
        var getRentByIdQuery = new GetRentByIdQuery(id);
        var rent = rentQueryService.handle(getRentByIdQuery);

        if (rent.isEmpty())
            return ResponseEntity.badRequest().build();
        var rentResource = RentResourceFromEntityAssembler.toResourceFromEntity(rent.get());
        return new ResponseEntity<>(rentResource, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long id) {
        boolean isDeleted = rentCommandService.delete(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
