package unistudy.unistudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import unistudy.unistudy.domain.LoginRequest;
import unistudy.unistudy.domain.Post;
import unistudy.unistudy.domain.User;
import unistudy.unistudy.repository.PostRepository;
import unistudy.unistudy.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private PostRepository postRepository;
    @Autowired
    public UserService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // Login
    public User login(LoginRequest req){
        Optional<User> optionalUser = userRepository.findByEmail(req.getEmail());
        if(optionalUser.isEmpty()){
            return null;
        }
        User user = optionalUser.get();
        if(!user.getPw().equals(req.getPw())){
            return null;
        }
        return user;
    }
    // find by id
    public User getLoginUserById(Integer userId) {
        if(userId == null) return null;

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }
    // sign up
    public Integer join(User user){
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }
    public ResponseEntity<String> validateDuplicateUser(User user) {
        return userRepository.findByEmail(user.getEmail())
                .map(m -> ResponseEntity.badRequest().body("이미 가입한 이메일입니다."))
                .orElse(null);
    }

    // find all users
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    // find one user by id
    public Optional<User> findOneUser(Integer userId) {
        return userRepository.findById(userId);
    }
    // delete user
    @Transactional
    public void deleteUser(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Post 엔티티에서 해당 User를 참조하는 필드를 null로 설정
            List<Post> posts = postRepository.findByWriter_Id(user.getId());
            for (Post post : posts) {
                post.setWriter(null);
                postRepository.save(post);
            }

            // User 엔티티 삭제
            userRepository.delete(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }
    // update user information
    public void updateUser(Integer userId, User updatedUser) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();

            // 업데이트할 필드들을 설정
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            // 필요에 따라 다른 필드들도 업데이트

            // userRepository를 통해 업데이트된 유저 저장
            userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
