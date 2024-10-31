package pe.edu.upc.micasita.Micasitaplataform.Transaction.interfaces.rest.resources;

public record CreateBuyResource(String name, String dni, String phoneNumber, String email,Double buyAmount,Long idProperty) {
}
