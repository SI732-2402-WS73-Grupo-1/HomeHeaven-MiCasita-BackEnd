package pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands.CreateBuyCommand;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands.UpdateBuyCommand;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands.UpdateRentCommand;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.valueobjects.UserInfo;
import pe.edu.upc.micasita.Micasitaplataform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "buys")
public class Buy extends AuditableAbstractAggregateRoot {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBuy;//*/

    @Embedded
    private UserInfo userInfo;

    @Column(name = "id_property", nullable = false)
    private Long idProperty;

    @Column(name = "buy_amount", nullable = false)
    private Double buyAmount;
    @ManyToOne
    @JoinColumn(name = "id_voucher")
    private Voucher voucher;


    public Buy() {}

    public Buy(String name, String dni, String phoneNumber, String email) {
        this.userInfo = new UserInfo(name, dni, phoneNumber, email);
    }

    public Buy(CreateBuyCommand command) {
        this.userInfo = new UserInfo(command.name(), command.dni(), command.phoneNumber(), command.email());
        this.idProperty = command.idProperty();
        this.buyAmount = command.buyAmount();
    }
    public void update(UpdateBuyCommand command) {
        this.userInfo = new UserInfo(command.name(), command.dni(), command.phoneNumber(), command.email());
    }
}
