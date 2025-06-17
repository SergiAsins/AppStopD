package com.Backend.userstests;

import com.Backend.users.User;
import com.Backend.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByUsername_thenReturnUser() {
        //given
        User user = new User("Paxi", "vote4paxi");
        entityManager.persist(user);
        entityManager.flush();

        //when
        Optional<User> optionalPaxi = userRepository.findByUsername(user.getUsername());

        //then
        assertThat(optionalPaxi.isPresent()).isTrue();
        assertThat(optionalPaxi.get().getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void whenFindByNonExistentUsername_thenReturnEmpty() {
        //given & when
        Optional<User> optionalUser = userRepository.findByUsername("noExistUser");

        //then
        assertThat(optionalUser.isEmpty()).isTrue();
    }
}

