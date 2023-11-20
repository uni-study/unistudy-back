package unistudy.unistudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import unistudy.unistudy.domain.User;
import unistudy.unistudy.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<Integer> signUp(@RequestBody User user) {
        try {
            Integer userId = userService.join(user);
            return new ResponseEntity<>(userId, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 모든 유저 목록 반환
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
