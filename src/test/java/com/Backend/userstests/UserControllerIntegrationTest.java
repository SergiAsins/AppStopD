package com.Backend.userstests;

import com.Backend.roles.Role;
import com.Backend.roles.RoleRepository;
import com.Backend.users.UserRepository;
import com.Backend.users.UserRequestDTO;
import com.Backend.users.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        roleRepository.deleteAll();

        Role userRole = new Role("ROLE_USER");
        roleRepository.save(userRole);
    }


    @Test
    public void whenPostUser_thenCreateUser() throws Exception {
        String userJson = "{\"username\":\"testdavid\",\"password\":\"testpass\",\"role\":{\"name\":\"ROLE_USER\"}}";

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("testdavid"));
    }

    @Test
    public void whenGetUser_thenReturnUser() throws Exception {
        Role role = roleRepository.findByName("ROLE_USER").get();
        UserRequestDTO userRequestDTO = new UserRequestDTO(role, "getuser", "password");
        userService.createUser(userRequestDTO);

        // URL corregida - usa parámetro query
        mockMvc.perform(get("/api/v1/users?username=getuser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("getuser"));
    }
}