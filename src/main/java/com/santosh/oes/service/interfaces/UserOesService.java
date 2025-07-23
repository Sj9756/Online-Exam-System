package com.santosh.oes.service.interfaces;

import com.santosh.oes.model.UserOes;
import com.santosh.oes.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserOesService {
    @Autowired
    UserRepo userRepo;
    public void save(UserOes userOes){
        PasswordEncoder encoder=new BCryptPasswordEncoder(12);
        userOes.setPassword(encoder.encode(userOes.getPassword()));
        userRepo.save(userOes);
    }
  public boolean isPresent(String username){
      return userRepo.findByUsername(username).isPresent();
  }
  public UserOes findByUsername(String username){
        Optional<UserOes>oes=userRepo.findByUsername(username);
        if (oes.isEmpty()){
            throw new NoSuchElementException("No value present");
        }
        return oes.get();

  }
}
