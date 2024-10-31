package pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services;


import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.aggregate.Voucher;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetAllVoucherQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetVoucherByIdBuyAndBuyAmountQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetVoucherByIdQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetVoucherByIdRentAndRentAmountQuery;

import java.util.List;
import java.util.Optional;

public interface VoucherQueryService {
    Optional<Voucher> handle(GetVoucherByIdQuery query);
    Optional<Voucher> handle(GetVoucherByIdBuyAndBuyAmountQuery query);
    List<Voucher> handle(GetAllVoucherQuery query);
    Optional<Voucher> handle(GetVoucherByIdRentAndRentAmountQuery query);
}
