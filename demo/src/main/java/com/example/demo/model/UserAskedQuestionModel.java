package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class UserAskedQuestionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 10000)
    private byte[] imageBytes;
}
