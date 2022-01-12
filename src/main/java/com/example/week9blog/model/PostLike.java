package com.example.week9blog.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "post_id")
    @ManyToOne
    private Post post;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserInfo userInfo;


}
