package pe.edu.upc.micasita.Micasitaplataform.users.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.micasita.Micasitaplataform.users.application.CommandServices.UserCommandServicesImpl;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.commads.CreateUserCommand;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.commads.DeleteUserCommand;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.model.queries.GetUserByPhoneQuery;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.services.UserCommandServices;
import pe.edu.upc.micasita.Micasitaplataform.users.domain.services.UserQueryServices;
import pe.edu.upc.micasita.Micasitaplataform.users.interfaces.rest.resources.CreateUserResource;
import pe.edu.upc.micasita.Micasitaplataform.users.interfaces.rest.resources.UserResource;
import pe.edu.upc.micasita.Micasitaplataform.users.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import pe.edu.upc.micasita.Micasitaplataform.users.interfaces.rest.transform.UserResourceFromEntityAssembler;

@RestController
@RequestMapping(value = "api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "The Users API")
@CrossOrigin(origins = "https://micasita-frontend.web.app")
public class UserController {

    private final UserCommandServices userCommandService;
    private final UserQueryServices userQueryService;
    private final UserCommandServicesImpl userCommandServicesImpl;

    public UserController(UserCommandServices userCommandService, UserQueryServices userQueryService, UserCommandServicesImpl userCommandServicesImpl) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
        this.userCommandServicesImpl = userCommandServicesImpl;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long id) {
        var getUserByIdQuery = new GetUserByIdQuery(id);
        var user = userQueryService.handle(getUserByIdQuery);
        if(user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<UserResource> getUserByPhone(@PathVariable String phone) {
        var getUserByPhoneQuery = new GetUserByPhoneQuery(phone);
        var user = userQueryService.handle(getUserByPhoneQuery);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }

    @PostMapping
    public ResponseEntity<UserResource> addUser(@RequestBody CreateUserResource resource){
        CreateUserCommand createUserCommand =
                CreateUserCommandFromResourceAssembler.toCommandFromResource(resource);
        Long id = userCommandService.handle(createUserCommand);

        if (id == 0 ){
            return ResponseEntity.badRequest().build();
        }
        var user = userQueryService.handle(new GetUserByIdQuery(id));
        if(user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        var deleteUserCommand = new DeleteUserCommand(id);
        userCommandService.handle(deleteUserCommand);
        return ResponseEntity.ok("User with given id successfully deleted");
    }
}