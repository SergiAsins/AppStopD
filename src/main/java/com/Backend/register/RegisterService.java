package com.Backend.register;

import java.util.*;
import java.util.Base64.Decoder;

import com.Backend.exceptions.general.AppAlreadyExistsException;
import com.Backend.roles.RolesService;
import com.Backend.users.User;
import com.Backend.users.UserRequestDTO;
import com.Backend.users.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final RolesService rolesService;

    public RegisterService(UserRepository userRepository, RolesService rolesService) {
        this.userRepository = userRepository;
        this.rolesService = rolesService;
    }

public Map<String, String> save(UserRequestDTO userRequestDTO){

        Optional<User> existingUser = userRepository.findByUsername(userRequestDTO.username());
        if(existingUser.isPresent()){
            throw new AppAlreadyExistsException("The user with this name already exists.");
        }

        System.out.println("------------------" + userRequestDTO.password());
        // Decode the base64 password
        Decoder decoder = Base64.getDecoder();
        byte[] decodeBytes = decoder.decode(userRequestDTO.password());
        String passwordDecoded = new String(decodeBytes);

        System.out.println("<------------------" + passwordDecoded);

        // Encrypt the password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordEncoded = encoder.encode(passwordDecoded);
        //gpt: String passwordEncoded = encoder.encode(userRequestDTO.password());

        //retrieve the role from the DB
        //Role role = roleService.getById(userRequestDTO.role().getId());

        //create the user Entity
        User newUser = new User(userRequestDTO.username(), passwordEncoded);

        // manage RoleNotFoundException
        try{
            newUser.setRoles(rolesService.assignDefaultRole(newUser.getId()));
        } catch(RoleNotFoundException e){
            throw new RuntimeException("Default role could not be assigned. Please contact support", e);
        };

        //Save User Entity to the repository
        userRepository.save(newUser);

        //Prepare the response
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return response;
    }
}
