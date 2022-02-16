package learning.spring.demo.service;


import learning.spring.demo.persistence.entity.User;
import learning.spring.demo.persistence.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public List<User> getAll() {
        return userRepo.findAll();
    }

    public User getByEmail(String email) {
        return userRepo.getByEmail(email);
    }

    public String cryptPassword(String password) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        BigInteger big = new BigInteger(1, messageDigest.digest(password.getBytes()));
        String crypted = big.toString();
        return crypted;
    }


    public String getPassword(String email) {
        return userRepo.getPassword(email);
    }


    public Boolean checkUser(String email, String password) {
        Boolean valid = false;
        if (userRepo.getPassword(email).equals(cryptPassword(password))) {
            valid = true;
        }
        return valid;
    }

    public void logIn(Boolean logIn,String email) {
        userRepo.logIn(logIn,email);
    }

    public void logOut(String email){
        userRepo.logOut(email);
    }

    public boolean checkEmail(String email){
        boolean valid = false;
        if(!userRepo.checkEmail(email).isEmpty()){
            valid = true;
        }
        return valid;
    }


}
