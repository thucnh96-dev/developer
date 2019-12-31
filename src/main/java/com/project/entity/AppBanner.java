package com.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TB_APPBANER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppBanner extends AbtractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "path")
    private String path;

    @Column(name = "prioritize")
    private Integer prioritize;

    @Column(name = "title")
    private String title;

}
