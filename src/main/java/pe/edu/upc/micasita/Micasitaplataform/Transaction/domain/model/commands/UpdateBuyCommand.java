package pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands;

public record UpdateBuyCommand(Long idBuy, String name, String dni, String phoneNumber, String email) {
}
