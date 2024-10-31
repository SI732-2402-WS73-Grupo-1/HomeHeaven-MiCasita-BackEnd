package pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.aggregate.Voucher;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.*;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services.VoucherQueryService;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherContextFacade {

    private final VoucherQueryService voucherQueryService;

    public VoucherContextFacade(VoucherQueryService voucherQueryService) {
        this.voucherQueryService = voucherQueryService;
    }

    /**
     * Get Voucher by ID
     *
     * @param voucherId the voucher id
     * @return the voucher
     */
    public Optional<Voucher> getVoucherById(Long voucherId) {
        var getVoucherByIdQuery = new GetVoucherByIdQuery(voucherId);
        return voucherQueryService.handle(getVoucherByIdQuery);
    }

    /**
     * Get all Vouchers
     *
     * @return the list of vouchers
     */
    public List<Voucher> getAllVouchers() {
        var query = new GetAllVoucherQuery();
        return voucherQueryService.handle(query);
    }

    /**
     * Get Voucher by Buy ID and Buy Amount
     *
     * @param buyId the buy id
     * @param buyAmount the buy amount
     * @return the voucher
     */
    public Optional<Voucher> getVoucherByIdBuyAndBuyAmount(Long buyId, Double buyAmount) {
        var query = new GetVoucherByIdBuyAndBuyAmountQuery(buyId, buyAmount);
        return voucherQueryService.handle(query);
    }

    /**
     * Get Voucher by Rent ID and Rent Amount
     *
     * @param rentId the rent id
     * @param rentAmount the rent amount
     * @return the voucher
     */
    public Optional<Voucher> getVoucherByIdRentAndRentAmount(Long rentId, Double rentAmount) {
        var query = new GetVoucherByIdRentAndRentAmountQuery(rentId, rentAmount);
        return voucherQueryService.handle(query);
    }
}
