package spr22533.bug;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureEmbeddedDatabase

public class BugExample {

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    public void contextLoads() {
        roleRepo.save(new Role("ADMIN"));
        roleRepo.save(new Role("USER"));

        User user = new User("admin", Collections.singleton(new Role("ADMIN")));
        userRepo.save(user);

        AtomicBoolean found = new AtomicBoolean(false);
        jdbc.query("select * from bug_user_roles where user_username = 'admin'", rs -> {
            found.set(true);
        });

        assertThat(found).isTrue();
    }

}
