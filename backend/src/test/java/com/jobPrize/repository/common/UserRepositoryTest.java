package com.jobPrize.repository.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.jobPrize.config.QuerydslConfig;
import com.jobPrize.entity.common.User;
import com.jobPrize.entity.common.UserType;

import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QuerydslConfig.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("User 저장 및 조회 테스트")
    void saveAndFindUser() {
        // given
        User user = User.builder()
                .email("test@user.com")
                .password("pass1234")
                .name("홍길동")
                .phone("01012341234")
                .address("서울특별시")
                .type(UserType.일반회원)
                .build();

        userRepository.save(user);
        em.flush();
        em.clear();

        // when
        Optional<User> found = userRepository.findByEmailAndDeletedDateIsNull("test@user.com");

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("홍길동");
    }
}