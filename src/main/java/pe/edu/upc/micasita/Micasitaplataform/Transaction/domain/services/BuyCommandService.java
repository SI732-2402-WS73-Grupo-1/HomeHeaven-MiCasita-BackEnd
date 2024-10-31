package pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services;

import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands.CreateBuyCommand;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands.UpdateBuyCommand;
import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands.UpdateRentCommand;

public interface BuyCommandService {
    Long handle(CreateBuyCommand command);
    boolean handle(UpdateBuyCommand command);
    boolean delete(Long idRent);
}
