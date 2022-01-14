package com.example.week9blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String content;

    @JoinColumn(name = "post_id")
    @ManyToOne
    private Post post;

    @Column
    private Instant createdOn;
    @Column
    private Instant updatedOn;

    @ManyToOne
    private UserInfo userInfo;


}
