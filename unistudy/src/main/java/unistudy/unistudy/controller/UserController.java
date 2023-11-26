package unistudy.unistudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import unistudy.unistudy.DTO.UserDto;
import unistudy.unistudy.domain.User;
import unistudy.unistudy.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class UserController {
    private final UserService userService;

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
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        List<UserDto> userDtos = users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }


    // 특정 아이디 유저 조회
    @GetMapping("/user/{userid}")
    public ResponseEntity<UserDto> getOneUser(@PathVariable Integer userid) {
        Optional<User> userOptional = userService.findOneUser(userid);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserDto userDto = convertToDto(user);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Convert User to UserDto
    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        // You can add more fields as needed
        return userDto;
    }


}
