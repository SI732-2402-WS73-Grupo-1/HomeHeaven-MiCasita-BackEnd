package pe.edu.upc.micasita.Micasitaplataform.users.interfaces.rest.transform;

import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.entities.User;
import pe.edu.upc.micasita.Micasitaplataform.users.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(entity.getId(),
                                entity.getName(),
                                entity.getDni(),
                                entity.getEmail(),
                                entity.getPassword(),
                                entity.getPhone(),
                                entity.getAddress()
        );
    }
}
