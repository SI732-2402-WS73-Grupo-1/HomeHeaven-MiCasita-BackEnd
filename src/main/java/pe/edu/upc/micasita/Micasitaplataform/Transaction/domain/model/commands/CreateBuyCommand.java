package pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands;

public record CreateBuyCommand(String name,String dni, String phoneNumber, String email, Double buyAmount, Long idProperty) {
}
