package com.wss.webservicestudy.web.user.entity;

import javax.persistence.*;

@Entity
public class UserRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double rating;

    @ManyToOne
    private User to;

    @ManyToOne
    private User from;
}
