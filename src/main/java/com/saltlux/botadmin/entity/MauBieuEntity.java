package com.saltlux.botadmin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "mau_bieu")
public class MauBieuEntity {
    private static final long serialVersionUID = -558553967080513799L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_mau_bieu")
    private String tenMauBieu;

    @Column(name = "code")
    private String code;

    @Column(name = "path")
    private String path;
}
