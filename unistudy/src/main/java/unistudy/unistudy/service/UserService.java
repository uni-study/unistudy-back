package unistudy.unistudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
