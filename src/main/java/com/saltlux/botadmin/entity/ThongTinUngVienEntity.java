package com.saltlux.botadmin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "thong_tin_ung_vien")

public class ThongTinUngVienEntity implements Serializable {
    private static final long serialVersionUID = -558553967080513799L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "vi_tri_ung_tuyen")
    private String viTriUngTuyen;

    @Column(name = "cv_path")
    private String pathCV;

    public ThongTinUngVienEntity(String hoTen, String viTriUngTuyen, String pathCV) {
        this.hoTen = hoTen;
        this.viTriUngTuyen = viTriUngTuyen;
        this.pathCV = pathCV;
    }
}
