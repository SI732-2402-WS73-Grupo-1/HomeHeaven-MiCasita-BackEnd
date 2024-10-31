package pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetVoucherByIdQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services.VoucherCommandService;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services.VoucherQueryService;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources.CreateVoucherResource;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources.VoucherResource;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.transform.CreateVoucherCommandFromResourceAssembler;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.transform.VoucherResourceFromEntityAssembler;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/vouchers", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Voucher", description = "Voucher Management Endpoints")
public class VoucherController {
    private final VoucherCommandService voucherCommandService;
    private final VoucherQueryService voucherQueryService;

    public VoucherController(VoucherCommandService voucherCommandService, VoucherQueryService voucherQueryService) {
        this.voucherCommandService = voucherCommandService;
        this.voucherQueryService = voucherQueryService;
    }

    @PostMapping
    public ResponseEntity<VoucherResource> createVoucher(@RequestBody CreateVoucherResource createVoucherResource) {
        var createVoucherCommand = CreateVoucherCommandFromResourceAssembler.toCommandFromResource(createVoucherResource);
        var voucherId = voucherCommandService.handle(createVoucherCommand);

        if (voucherId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getVoucherByIdQuery = new GetVoucherByIdQuery(voucherId);
        var voucher = voucherQueryService.handle(getVoucherByIdQuery);

        if (voucher.isEmpty())
            return ResponseEntity.badRequest().build();
        var voucherResource = VoucherResourceFromEntityAssembler.toResourceFromEntity(voucher.get());
        return new ResponseEntity<>(voucherResource, HttpStatus.CREATED);
    }
}
