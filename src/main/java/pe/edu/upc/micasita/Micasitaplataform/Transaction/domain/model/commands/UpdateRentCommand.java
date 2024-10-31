package pe.edu.upc.micasita.Micasitaplataform.Transaction.domain.model.commands;

public record UpdateRentCommand(Long idRent, String name, String dni, String phoneNumber, String email) {
}
