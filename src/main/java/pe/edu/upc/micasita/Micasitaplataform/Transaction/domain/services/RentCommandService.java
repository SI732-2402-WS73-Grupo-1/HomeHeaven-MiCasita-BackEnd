package pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services;


import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.aggregate.Rent;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands.CreateRentCommand;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands.UpdateRentCommand;

import java.util.Optional;

public interface RentCommandService {
    Long handle(CreateRentCommand command);
    boolean handle(UpdateRentCommand command);
    boolean delete(Long idRent);
}
