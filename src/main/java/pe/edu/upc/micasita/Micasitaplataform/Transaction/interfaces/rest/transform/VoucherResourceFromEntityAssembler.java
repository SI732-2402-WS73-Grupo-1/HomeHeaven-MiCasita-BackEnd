package pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.transform;

import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.aggregate.Buy;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.aggregate.Voucher;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources.BuyResource;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources.VoucherResource;

public class VoucherResourceFromEntityAssembler {
    public static VoucherResource toResourceFromEntity(Voucher entity) {
        return new VoucherResource(entity.getId(), entity.getIdBuy(), entity.getBuyAmount(),
                entity.getIdRent(), entity.getRentAmount());
    }
}
