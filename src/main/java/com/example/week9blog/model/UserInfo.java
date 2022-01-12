package com.example.week9blog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")

public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
//    @JoinColumn
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonManagedReference
//    @OneToMany
//    private List<UserInfo> connection;
//    @JoinColumn
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonManagedReference
//    @OneToMany
//    private List<Post> favoritePosts;




//    public UserInfo(Long id, String userName, String password, String email) {
//        this.id = id;
//        this.userName = userName;
//        this.password = password;
//        this.email = email;
//    }
//
//    public void setConnection(List<UserInfo> connection) {
//        this.connection = connection;
//    }
//
//    public List<UserInfo> getConnection() {
//        return connection;
//    }
//
//    public void setFavoritePosts(List<Post> favoritePosts) {
//        this.favoritePosts = favoritePosts;
//    }
//
//    public List<Post> getFavoritePosts() {
//        return favoritePosts;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
