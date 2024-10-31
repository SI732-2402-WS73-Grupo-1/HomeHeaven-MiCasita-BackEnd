package pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.aggregate.Buy;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands.CreateBuyCommand;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.*;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services.BuyCommandService;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services.BuyQueryService;

import java.util.List;
import java.util.Optional;

@Service
public class BuyContextFacade {
    private final BuyCommandService buyCommandService;
    private final BuyQueryService buyQueryService;

    public BuyContextFacade(BuyCommandService buyCommandService, BuyQueryService buyQueryService) {
        this.buyCommandService = buyCommandService;
        this.buyQueryService = buyQueryService;
    }

    /**
     * Creates a new Buy
     *
     * @param name the name
     * @param dni the dni
     * @param phoneNumber the phoneNumber
     * @param email the email
     * @param buyAmount the buy amount
     * @param idProperty the property id
     * @return the buy id
     */
    public Long createBuy(String name, String dni,String phoneNumber, String email, Double buyAmount, Long idProperty) {
        var createBuyCommand = new CreateBuyCommand(name, dni,phoneNumber, email, buyAmount, idProperty);
        var buyId = buyCommandService.handle(createBuyCommand);
        return buyId != null ? buyId : 0L;
    }

    /**
     * Get Buy by ID
     *
     * @param buyId the buy id
     * @return the buy
     */
    public Optional<Buy> getBuyById(Long buyId) {
        var getBuyByIdQuery = new GetBuyByIdQuery(buyId);
        return buyQueryService.handle(getBuyByIdQuery);
    }

    /**
     * Get Buy by User Info (Name, DNI)
     *
     * @param name the name
     * @param dni the dni
     * @return the buy
     */
    public Optional<Buy> getBuyByUserInfo(String name, String dni) {
        var query = new GetBuyByUserInfoNameAndUserInfoDniQuery(name, dni);
        return buyQueryService.handle(query);
    }

    /**
     * Get all Buys
     *
     * @return the list of buys
     */
    public List<Buy> getAllBuys() {
        var query = new GetAllBuyQuery();
        return buyQueryService.handle(query);
    }

    /**
     * Get Buy by Property ID and User Info (Name, DNI)
     *
     * @param idProperty the property id
     * @param name the name
     * @param dni the dni
     * @return the buy
     */
    public Optional<Buy> getBuyByPropertyAndUserInfo(Long idProperty, String name, String dni) {
        var query = new GetBuyByIdPropertyAndUserInfoNameAndUserInfoDniQuery(idProperty, name, dni);
        return buyQueryService.handle(query);
    }
}
