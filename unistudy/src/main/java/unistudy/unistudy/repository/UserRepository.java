package unistudy.unistudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unistudy.unistudy.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    User save(User user);

//    @Override
//    Optional<User> findById(Integer integer);
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
}
