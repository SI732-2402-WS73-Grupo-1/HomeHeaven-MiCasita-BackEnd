package pe.edu.upc.micasita.Micasitaplataform.users.interfaces.rest.resources;

public record UserResource(Long id,
                            String name,
                            String dni,
                            String email,
                            String password,
                            String phone,
                            String address) {
}
