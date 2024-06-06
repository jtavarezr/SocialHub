package com.example.SocialHub.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.SocialHub.entity.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class UserRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private UserRepository repo;

  @Test
  public void testCreateUser() {
    User user = new User();
    user.setEmail("test@test.com");
    user.setPassword("test@2020");
    user.setFirstName("FirstName");
    user.setLastName("LastName");

    User savedUser = repo.save(user);

    User existUser = entityManager.find(User.class, savedUser.getId());


    assertEquals(user.getEmail(), existUser.getEmail());
    assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

  }
}