package pe.edu.upc.micasita.Micasitaplataform.Transaction.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.aggregate.Voucher;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    Optional<Voucher> findVoucherById(Long idVoucher);
    Optional<Voucher> findVoucherByIdBuyAndBuyAmount(Long idBuy, Double buyAmount);
    List<Voucher> findAll();
    Optional<Voucher> findVoucherByIdRentAndRentAmount(Long idRent, Double rentAmount);
}
