package pe.edu.upc.micasita.Micasitaplataform.Transaction.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.aggregate.Buy;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetAllBuyQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetBuyByIdPropertyAndUserInfoNameAndUserInfoDniQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetBuyByIdQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetBuyByUserInfoNameAndUserInfoDniQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services.BuyQueryService;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.infrastructure.persistence.jpa.BuyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BuyQueryServiceImpl implements BuyQueryService {
    private final BuyRepository buyRepository;

    public BuyQueryServiceImpl(BuyRepository buyRepository) {
        this.buyRepository = buyRepository;
    }

    @Override
    public Optional<Buy> handle(GetBuyByIdQuery query) {
        return this.buyRepository.findById(query.idBuy());
    }

    @Override
    public Optional<Buy> handle(GetBuyByUserInfoNameAndUserInfoDniQuery query) {
        return this.buyRepository.findBuyByUserInfoNameAndUserInfoDni(query.name(), query.dni());
    }

    @Override
    public List<Buy> handle(GetAllBuyQuery query) {
        return this.buyRepository.findAll();
    }

    @Override
    public Optional<Buy> handle(GetBuyByIdPropertyAndUserInfoNameAndUserInfoDniQuery query) {
        return this.buyRepository.findBuyByIdPropertyAndUserInfoNameAndUserInfoDni(query.idProperty(), query.name(), query.dni());
    }
}