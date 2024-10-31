package pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands;

public record CreateRentCommand(String name, String dni, String phoneNumber, String email, Double rentAmount,Long idProperty) {
}
