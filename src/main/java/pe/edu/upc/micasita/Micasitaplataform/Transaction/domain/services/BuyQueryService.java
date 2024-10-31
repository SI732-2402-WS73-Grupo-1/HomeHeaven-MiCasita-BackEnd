package pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services;

import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.aggregate.Buy;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetAllBuyQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetBuyByIdPropertyAndUserInfoNameAndUserInfoDniQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetBuyByIdQuery;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.GetBuyByUserInfoNameAndUserInfoDniQuery;

import java.util.List;
import java.util.Optional;

public interface BuyQueryService {
    Optional<Buy> handle(GetBuyByIdQuery query);
    Optional<Buy> handle(GetBuyByUserInfoNameAndUserInfoDniQuery query);
    List<Buy> handle(GetAllBuyQuery query);
    Optional<Buy> handle(GetBuyByIdPropertyAndUserInfoNameAndUserInfoDniQuery query);

}
