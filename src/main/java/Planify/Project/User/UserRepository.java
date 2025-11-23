package Planify.Project.User;

import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Transactional
    Optional<User> findByUsername(String username);

    @Modifying()
    @Query("update User u set u.firstname=:firstname, u.lastname=:lastname, u.phone=:phone where u.id = :id")
    void updateUser(@Param(value = "id") Integer id,   @Param(value = "firstname") String firstname, @Param(value = "lastname") String lastname , @Param(value = "phone") String phone);

}
