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


    //login and create session
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

    // logout and delete session
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest httpServletRequest) {
        try {
            HttpSession session = httpServletRequest.getSession(false);
            if (session != null) {
                session.invalidate(); // 세션 무효화
            }
            return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error during logout", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // check session information(check current user)
    @GetMapping("/check-session")
    public ResponseEntity<UserDto> checkSession(HttpServletRequest httpServletRequest) {
        try {
            HttpSession session = httpServletRequest.getSession(false);

            // If there's no session, return null
            if (session == null || session.getAttribute("userId") == null) {
                return new ResponseEntity<>(null, HttpStatus.OK);
            }

            // get userId from current session
            Integer userId = (Integer) session.getAttribute("userId");

            // find user with userId
            Optional<User> userOptional = userService.findOneUser(userId);

            // If user exists, return user dto
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                UserDto userDto = convertToDto(user);
                return new ResponseEntity<>(userDto, HttpStatus.OK);
            } else {
                // If there's no user, return UNAUTHORIZED
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // sign up
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



    // return all user list
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        List<UserDto> userDtos = users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }


    // find user with id
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
        return userDto;
    }

    // delete user
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

    // update user info
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
