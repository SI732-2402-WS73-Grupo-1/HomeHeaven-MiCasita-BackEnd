package pe.edu.upc.micasita.Micasitaplataform.users.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.commads.CreateUserCommand;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.commads.DeleteUserCommand;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.entities.User;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.services.UserCommandServices;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.services.UserQueryServices;

import java.util.List;
import java.util.Optional;


@Service
public class UsersContextFacade {
    private final UserCommandServices userCommandService;
    private final UserQueryServices userQueryService;


    public UsersContextFacade(UserCommandServices userCommandService, UserQueryServices userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    public Long createUser(CreateUserCommand command) {
        return userCommandService.handle(command);
    }

    public void deleteUser(DeleteUserCommand command) {
        userCommandService.handle(command);
    }

    public Optional<User> getUserById(Long id) {
        return userQueryService.handle(new GetUserByIdQuery(id));
    }
}