package pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands.CreateVoucherCommand;
import pe.edu.upc.micasita.Micasitaplataform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "vouchers")
public class Voucher extends AuditableAbstractAggregateRoot {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVoucher;*/

    @OneToMany(mappedBy = "voucher")
    private List<Buy> buys;

    @OneToMany(mappedBy = "voucher")
    private List<Rent> rents;

    @Column(name = "buy_amount")
    private Double buyAmount;

    @Column(name = "id_buy")
    private Long idBuy;

    @Column(name = "id_rent")
    private Long idRent;

    @Column(name = "rent_amount")
    private Double rentAmount;


    public Voucher() {}

    public Voucher(Double buyAmount, Double rentAmount) {
        this.buyAmount = buyAmount;
        this.rentAmount = rentAmount;
    }
    public Voucher(CreateVoucherCommand command){
        this.idBuy = command.idBuy();
        this.idRent = command.idRent();
        this.buyAmount = command.buyAmount();
        this.rentAmount = command.rentAmount();
    }
}