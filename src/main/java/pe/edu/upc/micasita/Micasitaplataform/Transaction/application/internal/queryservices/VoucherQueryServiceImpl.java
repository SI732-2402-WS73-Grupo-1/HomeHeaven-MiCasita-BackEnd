package pe.edu.upc.micasita.Micasitaplataform.Transaction.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.aggregate.Voucher;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetAllVoucherQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetVoucherByIdBuyAndBuyAmountQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetVoucherByIdQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetVoucherByIdRentAndRentAmountQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services.VoucherQueryService;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.infrastructure.persistence.jpa.VoucherRepository;


import java.util.List;
import java.util.Optional;

@Service
public class VoucherQueryServiceImpl implements VoucherQueryService {
    private final VoucherRepository voucherRepository;

    public VoucherQueryServiceImpl(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    @Override
    public Optional<Voucher> handle(GetVoucherByIdQuery query) {
        return this.voucherRepository.findById(query.idVoucher());
    }

    @Override
    public Optional<Voucher> handle(GetVoucherByIdBuyAndBuyAmountQuery query) {
        return this.voucherRepository.findVoucherByIdBuyAndBuyAmount(query.idBuy(), query.buyAmount());
    }

    @Override
    public List<Voucher> handle(GetAllVoucherQuery query) {
        return this.voucherRepository.findAll();
    }

    @Override
    public Optional<Voucher> handle(GetVoucherByIdRentAndRentAmountQuery query) {
        return this.voucherRepository.findVoucherByIdRentAndRentAmount(query.idRent(), query.rentAmount());
    }
}
