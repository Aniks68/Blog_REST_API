package com.example.week9blog.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name ="commentLikes")
@Data
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "comment_id")
    @ManyToOne
    private Comment comment;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserInfo userInfo;

}