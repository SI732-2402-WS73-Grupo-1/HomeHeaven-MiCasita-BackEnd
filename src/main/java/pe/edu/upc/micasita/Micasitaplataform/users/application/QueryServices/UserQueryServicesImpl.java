package pe.edu.upc.micasita.Micasitaplataform.users.application.QueryServices;

import org.springframework.stereotype.Service;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.entities.User;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.queries.GetUserByNameAndDniQuery;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.queries.GetUserByPhoneQuery;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.services.UserQueryServices;
import pe.edu.upc.micasita.Micasitaplataform.users.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserQueryServicesImpl implements UserQueryServices {

    private final UserRepository userRepository;

    public UserQueryServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Optional<User> handle(GetUserByPhoneQuery query) {
        return this.userRepository.findByPhone(query.phone());
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return this.userRepository.findById(query.userId());
    }

    @Override
    public Optional<User> handle(GetUserByNameAndDniQuery query) {
        return this.userRepository.findByNameAndDni(query.name(), query.dni());
    }
}
