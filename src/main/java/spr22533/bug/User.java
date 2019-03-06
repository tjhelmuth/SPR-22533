package spr22533.bug;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="bug_user")
public class User {
    @Id
    private String username;

    @Buggy //<---- REMOVE THIS AND TEST PASSES
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User(){}

    public User(String username, Set<Role> roles) {
        this.username = username;
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
