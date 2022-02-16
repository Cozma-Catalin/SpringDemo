package learning.spring.demo.controller;


import learning.spring.demo.persistence.entity.User;
import learning.spring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping(path = "/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping(path = "/getByEmail")
    public ResponseEntity<Object> getByEmail(@RequestParam String email) {

        if(!userService.checkEmail(email)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(email +" doesn't exist...!");
        }
        User user = userService.getByEmail(email);

        return ResponseEntity.ok(user);
    }

    @PutMapping(path = "/logIn")
    public ResponseEntity<String> login(@RequestParam String email, String password) {
        if(!userService.checkEmail(email)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(email +" doesn't exist...!");
        }
        if (!userService.checkUser(email, password)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong password...!");
        }
        userService.logIn(true,email);
        return ResponseEntity.ok("LoggedIn successful !");
    }

    @PutMapping(path = "/logOut")
    public ResponseEntity<String> logOut(@RequestParam String email,String password){
        if(!userService.checkEmail(email)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(email + " doesn't exist...!");
        }

        if(!userService.checkUser(email,password)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong password...!");
        }

        userService.logOut(email);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Logged out successful !");
    }

}
