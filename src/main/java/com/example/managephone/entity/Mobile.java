package com.example.managephone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Table(name = "mobile")
@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(name = "created_time") //Vì tên là danh từ ghép lên phải sử dụng gạch dưới để ngăn cách giữa 2 từ ghép, nếu không tự tách thì hệ thống tự làm như vậy
    private Timestamp createdTime;


    //Truy vấn một số thuộc tính không thuộc bài
    private int type = 1;
    private int active = 0;
}
