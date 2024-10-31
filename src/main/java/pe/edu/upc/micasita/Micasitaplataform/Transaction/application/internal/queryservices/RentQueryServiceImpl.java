package pe.edu.upc.micasita.Micasitaplataform.Transaction.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.aggregate.Rent;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetAllRentQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetRentByIdPropertyAndUserInfoNameAndUserInfoDniQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetRentByIdQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetRentByUserInfoNameAndUserInfoDniAndUserInfoPhoneNumberQuery;

import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services.RentQueryService;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.infrastructure.persistence.jpa.RentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RentQueryServiceImpl implements RentQueryService {
    private final RentRepository rentRepository;

    public RentQueryServiceImpl(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    @Override
    public Optional<Rent> handle(GetRentByIdQuery query) {
        return this.rentRepository.findById(query.idRent());
    }

    @Override
    public Optional<Rent> handle(GetRentByUserInfoNameAndUserInfoDniAndUserInfoPhoneNumberQuery query) {
        return this.rentRepository.findRentByUserInfoNameAndUserInfoDniAndUserInfoPhoneNumber(query.name(), query.dni(), query.phoneNumber());
    }

    @Override
    public List<Rent> handle(GetAllRentQuery query) {
        return this.rentRepository.findAll();
    }

    @Override
    public Optional<Rent> handle(GetRentByIdPropertyAndUserInfoNameAndUserInfoDniQuery query) {
        return this.rentRepository.findRentByIdPropertyAndUserInfoNameAndUserInfoDni(query.idProperty(), query.name(), query.dni());
    }
}