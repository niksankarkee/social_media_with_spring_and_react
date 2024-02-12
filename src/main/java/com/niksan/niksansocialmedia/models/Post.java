package com.niksan.niksansocialmedia.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String caption;

    private String image;

    private String video;

    @ManyToOne
    private User user;

    @OneToMany
    private List<User> liked = new ArrayList<>();

    private LocalDateTime createdAt;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();


}
