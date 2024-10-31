package pe.edu.upc.micasita.Micasitaplataform.users.application.CommandServices;

import org.springframework.stereotype.Service;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.commads.CreateUserCommand;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.commads.DeleteUserCommand;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.entities.User;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.services.UserCommandServices;
import pe.edu.upc.micasita.Micasitaplataform.users.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserCommandServicesImpl implements UserCommandServices {

    private final UserRepository userRepository;

    public UserCommandServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long handle(CreateUserCommand command) {
        if(userRepository.existsByPhone(command.phone())){
            throw new RuntimeException("User with same phone already exists");
        }
        if (userRepository.existsByNameAndDni(command.name(), command.dni())){
            throw new RuntimeException("User with same name and dni already exists");
        }
        var user = new User(command);
        try {
            userRepository.save(user);
        } catch (Exception e){
            throw new RuntimeException("Error while saving user", e);
        }
        return user.getId();
    }


    @Override
    public void handle(DeleteUserCommand command) {
        if(!userRepository.existsById(command.id())) {
            throw new IllegalArgumentException("User does not exist");
        }
        userRepository.deleteById(command.id());
    }
}
