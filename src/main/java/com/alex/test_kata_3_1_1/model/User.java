package com.alex.test_kata_3_1_1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {

   @Id
   @Column(name = "id", nullable = false)
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name", nullable = false)
   private String firstName;

   @Column(name = "last_name", nullable = false)
   private String lastName;

   @Email
   @Column(name = "email", unique = true, nullable = false)
   private String email;

   @Column(name = "age", nullable = false)
   private int age;

   @Column(name="password", nullable = false)
   private String password;

   @ManyToMany(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
   @JoinTable(name = "users_roles",
   joinColumns = @JoinColumn(name = "users_id"),
   inverseJoinColumns = @JoinColumn(name = "roles_id"))
   private Set<Role> roleList = new HashSet<>();

   public User() {}

   public User(String firstName, String lastName, String email) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
   }

   public User(String firstName, String lastName, String email, Role role) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
   }

   public void addRole(Role role){
      roleList.add(role);
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return roleList;
   }

   @Override
   public String getUsername() {
      return email;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return Objects.equals(id, user.id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", email='" + email + '\'' +
              ", age=" + age +
              ", password='" + password + '\'' +
              ", roleList=" + roleList +
              '}';
   }
}
