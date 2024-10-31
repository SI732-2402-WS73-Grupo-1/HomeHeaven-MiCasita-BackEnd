package pe.edu.upc.micasita.Micasitaplataform.users.domain.services;

import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.commads.CreateUserCommand;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.commads.DeleteUserCommand;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.entities.User;

import java.util.Optional;

public interface UserCommandServices {
    Long handle(CreateUserCommand command);
    void handle(DeleteUserCommand command);
}
