package unistudy.unistudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unistudy.unistudy.domain.User;
import unistudy.unistudy.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /* 회원가입 */
    public Integer join(User user){
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }
    private void validateDuplicateUser(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(m -> {
                    try {
                        throw new IllegalAccessException("이미 가입한 이메일입니다.");
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> findOneUser(Integer userId) {
        return userRepository.findById(userId);
    }

}
