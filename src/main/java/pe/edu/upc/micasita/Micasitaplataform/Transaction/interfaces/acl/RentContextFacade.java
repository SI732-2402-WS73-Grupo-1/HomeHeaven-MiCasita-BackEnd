package pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.aggregate.Rent;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands.CreateRentCommand;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.queries.*;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services.RentCommandService;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services.RentQueryService;


import java.util.List;
import java.util.Optional;

@Service
public class RentContextFacade {
    private final RentCommandService rentCommandService;
    private final RentQueryService rentQueryService;

    public RentContextFacade(RentCommandService rentCommandService,
                             RentQueryService rentQueryService) {
        this.rentCommandService = rentCommandService;
        this.rentQueryService = rentQueryService;
    }

    /**
     * Creates a new Rent
     *
     * @param name the name
     * @param dni the dni
     * @param phoneNumber the phoneNumber
     * @param email the email
     * @param rentAmount the rentAmount
     * @param idProperty the property id
     * @return the rent id
     */
    public Long createRent(String name, String dni, String phoneNumber, String email, Double rentAmount, Long idProperty) {
        var createRentCommand = new CreateRentCommand(name, dni, phoneNumber, email, rentAmount, idProperty);
        var rentId = rentCommandService.handle(createRentCommand);
        return rentId != null ? rentId : 0L;
    }

    /**
     * Get Rent by ID
     *
     * @param rentId the rent id
     * @return the rent
     */
    public Optional<Rent> getRentById(Long rentId) {
        var getRentByIdQuery = new GetRentByIdQuery(rentId);
        return rentQueryService.handle(getRentByIdQuery);
    }

    /**
     * Get Rent by User Info (Name, DNI, Phone Number)
     *
     * @param name the name
     * @param dni the dni
     * @param phoneNumber the phone number
     * @return the rent
     */
    public Optional<Rent> getRentByUserInfo(String name, String dni, String phoneNumber) {
        var query = new GetRentByUserInfoNameAndUserInfoDniAndUserInfoPhoneNumberQuery(name, dni, phoneNumber);
        return rentQueryService.handle(query);
    }

    /**
     * Get all Rents
     *
     * @return the list of rents
     */
    public List<Rent> getAllRents() {
        var query = new GetAllRentQuery();
        return rentQueryService.handle(query);
    }

    /**
     * Get Rent by Property ID and User Info (Name, DNI)
     *
     * @param idProperty the property id
     * @param name the name
     * @param dni the dni
     * @return the rent
     */
    public Optional<Rent> getRentByPropertyAndUserInfo(Long idProperty, String name, String dni) {
        var query = new GetRentByIdPropertyAndUserInfoNameAndUserInfoDniQuery(idProperty, name, dni);
        return rentQueryService.handle(query);
    }
}
