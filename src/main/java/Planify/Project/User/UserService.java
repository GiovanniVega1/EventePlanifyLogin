package Planify.Project.User;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.Optional;


@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse updateUser(UserRequest userRequest) {

        User user = User.builder()
                .id(userRequest.id)
                .firstname(userRequest.getFirstname())
                .lastname(userRequest.lastname)
                .phone(userRequest.getPhone())
                .role(Role.USER)
                .build();

        userRepository.updateUser(user.id, user.firstname, user.lastname, user.phone);

        return new UserResponse("El usuario se registró satisfactoriamente");
    }

    public UserDTO getUser(Integer id) {
        User user= userRepository.findById(id).orElse(null);

        if (user!=null)
        {
            UserDTO userDTO = UserDTO.builder()
                    .id(user.id)
                    .username(user.username)
                    .firstname(user.firstname)
                    .lastname(user.lastname)
                    .phone(user.phone)
                    .role(user.getRole())
                    .build();
            return userDTO;
        }
        return null;
    }

    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con Username: " + username));

        return UserDTO.builder()
                .id(user.id)
                .username(user.username)
                .firstname(user.firstname)
                .lastname(user.lastname)
                .phone(user.phone)
                .role(user.getRole())
                .build();
    }

    @Transactional
    public UserResponse upgradeToHost(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + id);
        }

        User user = userOptional.get();

        if (user.getRole() == Role.USER) {
            user.setRole(Role.HOST);
            userRepository.save(user);
            return new UserResponse("El usuario ha sido ascendido a HOST satisfactoriamente.");
        } else if (user.getRole() == Role.HOST) {
            return new UserResponse("El usuario ya es un HOST.");
        } else {
            return new UserResponse("El rol actual del usuario no permite el ascenso a HOST.");
        }
    }
}
