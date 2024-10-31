package pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources;

public record CreateRentResource(String name, String dni, String phoneNumber, String email,Double rentAmount,Long idProperty) {
}
