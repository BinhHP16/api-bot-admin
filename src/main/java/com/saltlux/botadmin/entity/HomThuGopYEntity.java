package com.saltlux.botadmin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties(
        value = {"thoiGian"},
        allowGetters = true
)
@Table(name = "hom_thu_gop_y")
public class HomThuGopYEntity implements Serializable {
    private static final long serialVersionUID = -558553967080513799L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "noi_dung",columnDefinition = "TEXT")
    private String noiDung;

    @Column(name = "tieu_de")
    private String tieuDe;

    @Column(name = "nguoi_gui")
    private String nguoiGui;

    @Column(name = "status_an_danh")
    private Integer anDanh;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "thoi_gian",columnDefinition = "DATE_TIME")
    private Date thoiGian;

    public HomThuGopYEntity(String noiDung, String tieuDe, String nguoiGui, Integer anDanh, Date thoiGian) {
        this.noiDung = noiDung;
        this.tieuDe = tieuDe;
        this.nguoiGui = nguoiGui;
        this.anDanh = anDanh;
        this.thoiGian = thoiGian;
    }
}
