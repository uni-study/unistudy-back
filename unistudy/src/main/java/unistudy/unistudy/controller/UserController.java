package unistudy.unistudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unistudy.unistudy.DTO.UserDto;
import unistudy.unistudy.domain.LoginRequest;
import unistudy.unistudy.domain.User;
import unistudy.unistudy.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    // 로그인
// 로그인
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest) {
        try {
            User user = userService.login(loginRequest);

            if (user != null) {
                // 로그인 성공
                HttpSession session = httpServletRequest.getSession(true);
                session.setAttribute("userId", user.getId());
                session.setMaxInactiveInterval(1800);

                UserDto userDto = convertToDto(user);
                return new ResponseEntity<>(userDto, HttpStatus.OK);
            } else {
                // 로그인 실패
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /* 회원가입 */
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody User user) {
        try {
            Integer userId = userService.join(user);
            Optional<User> createdUser = userService.findOneUser(userId);

            if (createdUser.isPresent()) {
                UserDto userDto = convertToDto(createdUser.get());
                return new ResponseEntity<>(userDto, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
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
        userDto.setPw(user.getPw());
        // You can add more fields as needed
        return userDto;
    }
    @DeleteMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 유저 정보 업데이트
    @PutMapping("/user/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Integer userId, @RequestBody User updatedUser) {
        try {
            userService.updateUser(userId, updatedUser);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
