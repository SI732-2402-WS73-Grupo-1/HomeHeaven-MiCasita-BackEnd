package pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.services;

import pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands.CreateVoucherCommand;

public interface VoucherCommandService {
    Long handle(CreateVoucherCommand command);
}
