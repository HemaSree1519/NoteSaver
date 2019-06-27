package com.noteSaver.notesaver.repository;

import com.noteSaver.notesaver.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryUnitTest {
    @Autowired
    TestEntityManager entityManager;
    @Autowired
    UserRepository userRepository;
    private User user = new User();

    {
        user.setEmail("UnitTester@gmail.com");
        user.setUserName("test_user");
        user.setPassword("password");
    }

    @Test
    public void it_should_save_user() {
        user = entityManager.persistAndFlush(user);
        assertThat(userRepository.findById(user.getEmail()).isPresent()).isTrue();
    }

    @Test
    public void it_should_find_user_byId() {
        user = entityManager.persistAndFlush(user);
        assertThat(userRepository.findById(user.getEmail()).get()).isEqualTo(user);
    }

    @Test
    public void it_should_find_all_users() {
        User user2 = new User();
        user2.setEmail("UnitTester2@gmail.com");
        user2.setUserName("test_user2");
        user2.setPassword("password2");
        user = entityManager.persistAndFlush(user);
        user2 = entityManager.persistAndFlush(user2);
        System.out.println(userRepository.findAll());
        assertThat(userRepository.findAll().get(0)).isEqualTo(user);
        assertThat(userRepository.findAll().get(1)).isEqualTo(user2);
    }
}
