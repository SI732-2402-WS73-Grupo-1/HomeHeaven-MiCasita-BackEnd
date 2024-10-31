package pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands;

public record CreateVoucherCommand(Long idBuy,Double buyAmount,Long idRent ,Double rentAmount) {
}
