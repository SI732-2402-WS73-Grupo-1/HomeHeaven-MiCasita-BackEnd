package pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.upc.micasita.Micasitaplataform.Reservation.domain.model.valueobjects.ReservationStatus;
import pe.edu.upc.micasita.Micasitaplataform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Reservation extends AuditableAbstractAggregateRoot<Reservation>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UserId", nullable = false)
    private Long userId;

    @Column(name = "PropertyId", nullable = false)
    private Long propertyId;

    @Column(name = "DateTim", nullable = false)
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 15, nullable = false)
    private ReservationStatus status;

    public Reservation(){
    }

    public Reservation(Long id, Long userId, Long propertyId, LocalDateTime dateTime, ReservationStatus status) {
        this.id = id;
        this.userId = userId;
        this.propertyId = propertyId;
        this.dateTime = dateTime;
        this.status = status;
    }

    public Reservation(CreateReservationCommand createReservationCommand) {
        this.userId = createReservationCommand.userId();
        this.propertyId = createReservationCommand.propertyId();
        this.dateTime = createReservationCommand.dateTime();
        this.status = createReservationCommand.status();
    }

    public ReservationStatus getStatus() {return status;}

    public Long getId() {
        return id;
    }
    public Long setId() {
        return id;
    }

    public void changeStatus(ReservationStatus status){this.status = status;

    }
    public void changeDateTime(LocalDateTime dateTime){

    }
}
