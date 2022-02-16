package learning.spring.demo.persistence.repository;

import learning.spring.demo.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    User getByEmail(String email);

    @Query("select password from User where email= ?1")
    String getPassword(String email);

    @Modifying
    @Query(value = "update from User set loggedIn= ?1 where email= ?2")
    void logIn(Boolean logIn, String email);

    @Query("select email from User where email= ?1")
    Optional<String> checkEmail(String email);

    @Modifying
    @Query("update from User set loggedIn= false where email= ?1")
    void logOut(String email);
}
