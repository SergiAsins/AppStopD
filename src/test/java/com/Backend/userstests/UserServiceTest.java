package com.Backend.userstests;

import com.Backend.exceptions.general.AppNotFoundException;
import com.Backend.roles.Role;
import com.Backend.users.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_ValidDTO_ReturnUserResponseDTO() {
        //Arrange
        Role userRole = new Role();
        userRole.setName("ROLE_USER");

        UserRequestDTO userRequestDTO = new UserRequestDTO(userRole, "JoseTests", "trustJesus");
        User savedUser = new User ("JoseTests", "trustJesus");
        //savedUser.setRoles(Set.of(userRole));
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        //Act
        UserResponseDTO userResponseDTO = userService.createUser((userRequestDTO));

        //Assert
        assertEquals("JoseTests", userResponseDTO.name());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void findById_NonExistentUser_ThrowsException(){
        //Arrange
        Long nonExistentId = 99L;
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(AppNotFoundException.class, () -> userService.findById(nonExistentId));
    }

    @Test
    void deleteUserByID_AuthenticatedUser_DeletesUser() {
        //Arrange
        User user = new User("authUser", "Pass123");
        when(userRepository.findByUsername("authUser")).thenReturn(Optional.of(user));

        //mock SecurityContext
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("authUser");
        SecurityContextHolder.getContext().setAuthentication(auth);

        //Act
        userService.deleteUserById();

        //Assert
        verify(userRepository, times(1)).deleteById(user.getId());
    }

    @Test
    void updateUserById_ValidData_ReturnsUpdatedUser() {
        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        UserRequestDTO request = new UserRequestDTO(adminRole, "updatedUser", "newPass123");
        User existingUser = new User("oldUser", "oldPass");
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        UserResponseDTO userResponseDTO = userService.updateUserById(1L, request);
        assertEquals("updatedUser", userResponseDTO.name());
    }

    @Test
    void findByUsernameIgnoreCaseContaining_ExistingUser_ReturnsUsers() {
        // Arrange
        User user1 = new User("Pacman", "pass123");
        User user2 = new User("Mike", "pass456");
        when(userRepository.findByUsernameIgnoreCaseContaining("pacman"))
                .thenReturn(List.of(user1, user2));

        // Act
        List<UserResponseDTO> result = userService.findByUsernameIgnoreCaseContaining("pacman");

        // Assert
        assertEquals(2, result.size());
        assertEquals("Pacman", result.get(0).name());
    }

    @Test
    void updateUserById_NonExistentUser_ThrowsException() {
        // Arrange
        UserRequestDTO request = new UserRequestDTO(new Role("ROLE_USER"), "newUser", "pass123");
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(AppNotFoundException.class, () -> userService.updateUserById(99L, request));
    }

    @Test
    void getAllUsers_ReturnsAllUsers() {
        // Arrange
        User user1 = new User("Paco", "pass1");
        User user2 = new User("Alcazar", "pass2");
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        // Act
        List<UserResponseDTO> result = userService.getAllUsers();

        // Assert
        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }
}
