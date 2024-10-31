package pe.edu.upc.micasita.Micasitaplataform.users.domain.services;

import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.entities.User;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.queries.GetUserByNameAndDniQuery;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.queries.GetUserByPhoneQuery;

import java.util.Optional;

public interface UserQueryServices {
    Optional<User> handle(GetUserByPhoneQuery query);
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> handle(GetUserByNameAndDniQuery query);
}
