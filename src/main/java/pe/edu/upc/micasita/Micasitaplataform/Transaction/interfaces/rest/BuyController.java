package pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetAllBuyQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetBuyByIdQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetBuyByUserInfoNameAndUserInfoDniQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services.BuyCommandService;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services.BuyQueryService;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources.CreateBuyResource;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources.BuyResource;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources.UpdateBuyResource;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.transform.CreateBuyCommandFromResourceAssembler;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.transform.BuyResourceFromEntityAssembler;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.transform.UpdateBuyCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/buys", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Buy", description = "Buy Management Endpoints")
public class BuyController {
    private final BuyCommandService buyCommandService;
    private final BuyQueryService buyQueryService;

    public BuyController(BuyCommandService buyCommandService, BuyQueryService buyQueryService) {
        this.buyCommandService = buyCommandService;
        this.buyQueryService = buyQueryService;
    }

    @PostMapping
    public ResponseEntity<BuyResource> createBuy(@RequestBody CreateBuyResource createBuyResource) {
        var createBuyCommand = CreateBuyCommandFromResourceAssembler.toCommandFromResource(createBuyResource);
        var buyId = buyCommandService.handle(createBuyCommand);

        if (buyId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getBuyByIdQuery = new GetBuyByIdQuery(buyId);
        var buy = buyQueryService.handle(getBuyByIdQuery);

        if (buy.isEmpty())
            return ResponseEntity.badRequest().build();
        var buyResource = BuyResourceFromEntityAssembler.toResourceFromEntity(buy.get());
        return new ResponseEntity<>(buyResource, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuyResource> getBuyById(@PathVariable Long id) {
        var getBuyByIdQuery = new GetBuyByIdQuery(id);
        var buy = buyQueryService.handle(getBuyByIdQuery);

        if (buy.isEmpty())
            return ResponseEntity.notFound().build();
        var buyResource = BuyResourceFromEntityAssembler.toResourceFromEntity(buy.get());
        return ResponseEntity.ok(buyResource);
    }

    @GetMapping
    public ResponseEntity<List<BuyResource>> getAllBuys() {
        var getAllBuyQuery = new GetAllBuyQuery();
        var buys = buyQueryService.handle(getAllBuyQuery).stream()
                .map(BuyResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(buys);
    }

    @GetMapping("/byUser")
    public ResponseEntity<List<BuyResource>> getBuyByUser(@RequestParam String name, @RequestParam String dni) {
        var getBuyByUserQuery = new GetBuyByUserInfoNameAndUserInfoDniQuery(name, dni);
        var buys = buyQueryService.handle(getBuyByUserQuery).stream()
                .map(BuyResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(buys);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BuyResource> updateBuy(@PathVariable Long id, @RequestBody UpdateBuyResource updateBuyResource) {
        var updateBuyCommand = UpdateBuyCommandFromResourceAssembler.toCommandFromResource(id, updateBuyResource);
        var buyUpdated = buyCommandService.handle(updateBuyCommand);

        if (!buyUpdated) {
            return ResponseEntity.notFound().build();
        }
        var getBuyByIdQuery = new GetBuyByIdQuery(id);
        var buy = buyQueryService.handle(getBuyByIdQuery);

        if (buy.isEmpty())
            return ResponseEntity.badRequest().build();
        var buyResource = BuyResourceFromEntityAssembler.toResourceFromEntity(buy.get());
        return new ResponseEntity<>(buyResource, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuy(@PathVariable Long id) {
        boolean isDeleted = buyCommandService.delete(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
