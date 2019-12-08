package com.example.authorbook.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private int count;
    @Column
    private double price;
    @ManyToOne
    private Author author;
    @Column(name = "pic_Url")
    private String picUrl;
}
