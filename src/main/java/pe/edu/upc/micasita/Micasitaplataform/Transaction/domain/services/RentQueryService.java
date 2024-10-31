package pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services;

import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.aggregate.Rent;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface RentQueryService {
    Optional<Rent> handle(GetRentByIdQuery query);
    Optional<Rent> handle(GetRentByUserInfoNameAndUserInfoDniAndUserInfoPhoneNumberQuery query);
    List<Rent> handle(GetAllRentQuery query);
    Optional<Rent> handle(GetRentByIdPropertyAndUserInfoNameAndUserInfoDniQuery query);
}
