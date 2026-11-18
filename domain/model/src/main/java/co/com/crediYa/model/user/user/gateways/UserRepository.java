package co.com.crediYa.model.user.user.gateways;

import co.com.crediYa.model.user.user.User;

public interface UserRepository {
    User save(User user);
    boolean existsByCorreoElectronico(String correoElectronico);
}