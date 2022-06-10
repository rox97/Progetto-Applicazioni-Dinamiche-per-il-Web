package it.univr.webapp.repositories.users;

import it.univr.webapp.models.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,String> {
    UserEntity findByUserCodeAndAndUserPassword(String user_code, String user_password);
}
