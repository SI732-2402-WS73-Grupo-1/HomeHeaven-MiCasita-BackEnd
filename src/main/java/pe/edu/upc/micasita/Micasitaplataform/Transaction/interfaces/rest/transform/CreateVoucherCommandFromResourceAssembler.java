package pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.transform;

import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands.CreateBuyCommand;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands.CreateVoucherCommand;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources.CreateBuyResource;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources.CreateVoucherResource;

public class CreateVoucherCommandFromResourceAssembler {
    public static CreateVoucherCommand toCommandFromResource(CreateVoucherResource resource) {
        return new CreateVoucherCommand(
                resource.idBuy(),
                resource.buyAmount(),
                resource.idRent(),
                resource.rentAmount()
        );
    }
}
