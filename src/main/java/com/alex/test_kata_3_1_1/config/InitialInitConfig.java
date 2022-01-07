package com.alex.test_kata_3_1_1.config;

import com.alex.test_kata_3_1_1.model.Role;
import com.alex.test_kata_3_1_1.model.User;
import com.alex.test_kata_3_1_1.service.RoleService;
import com.alex.test_kata_3_1_1.service.UserService;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
@Log4j2
public class InitialInitConfig {

    private final EntityManagerFactory entityManagerFactory;
    private final PasswordEncoder passwordEncoder;

    public InitialInitConfig(EntityManagerFactory entityManagerFactory, PasswordEncoder passwordEncoder) {
        this.entityManagerFactory = entityManagerFactory;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initValues(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_DEVELOPER");
        Role role3 = new Role("ROLE_USER");
        Role role4 = new Role("ROLE_HR");
        Role role5 = new Role("ROLE_MANAGER");

        log.info("Add init role: "+role1);

        entityManager.persist(role1);
        entityManager.persist(role2);
        entityManager.persist(role3);
        entityManager.persist(role4);
        entityManager.persist(role5);

        User admin = new User("admin", "admin", "admin@mail.ru");
        admin.setAge(1);
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.addRole(role1);
        entityManager.persist(admin);

        User user1 = new User("Alex", "Alex", "email1@mail.ru");
        user1.setAge(1);
        user1.setPassword(passwordEncoder.encode("email1@mail.ru"));
        user1.addRole(role1);
        entityManager.persist(user1);

        User user2 = new User("Dasha", "Dasha", "email2@mail.ru");
        user2.setAge(2);
        user2.setPassword(passwordEncoder.encode("email2@mail.ru"));
        user2.addRole(role1);
        entityManager.persist(user2);

        User user = new User("user", "user", "user@mail.ru");
        user.setAge(3);
        user.setPassword(passwordEncoder.encode("user"));
        user.addRole(role3);
        entityManager.persist(user);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
