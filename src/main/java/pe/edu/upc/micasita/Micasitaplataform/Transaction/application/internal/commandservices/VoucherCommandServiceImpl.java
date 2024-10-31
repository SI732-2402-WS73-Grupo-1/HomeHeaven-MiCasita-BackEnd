package pe.edu.upc.micasita.Micasitaplataform.Transaction.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.aggregate.Voucher;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands.CreateVoucherCommand;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services.VoucherCommandService;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.infrastructure.persistence.jpa.VoucherRepository;

@Service
public class VoucherCommandServiceImpl implements VoucherCommandService {
    private final VoucherRepository voucherRepository;

    public VoucherCommandServiceImpl(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    @Override
    public Long handle(CreateVoucherCommand command) {
        if ((command.idBuy() != null && command.idRent() != null && command.idBuy() != 0 && command.idRent() != 0) ||
                (command.idBuy() == null || command.idBuy() == 0) && (command.idRent() == null || command.idRent() == 0)) {
            throw new IllegalArgumentException("Either Buy ID and amount or Rent ID and amount must be provided, but not both.");
        }

        if ((command.idBuy() != null && command.buyAmount() == null) || (command.idRent() != null && command.rentAmount() == null)) {
            throw new IllegalArgumentException("Amount must be provided with the corresponding ID.");
        }

        if (command.idBuy() != null) {
            if (voucherRepository.findVoucherByIdBuyAndBuyAmount(command.idBuy(), command.buyAmount()).isPresent()) {
                throw new IllegalArgumentException("Voucher with the same Buy ID and amount already exists.");
            }
        }

        if (command.idRent() != null) {
            if (voucherRepository.findVoucherByIdRentAndRentAmount(command.idRent(), command.rentAmount()).isPresent()) {
                throw new IllegalArgumentException("Voucher with the same Rent ID and amount already exists.");
            }
        }

        Voucher voucher = new Voucher(command.buyAmount(), command.rentAmount());

        try {
            voucherRepository.save(voucher);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving voucher: " + e.getMessage());
        }

        return voucher.getId();
    }
}