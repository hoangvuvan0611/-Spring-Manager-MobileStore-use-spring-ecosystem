package com.example.managephone.dto;

import com.example.managephone.constant.DateTimeConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class MobileDto {
    private Long id;
    private String name;
    private String description;
    @JsonFormat(pattern = DateTimeConstant.DATE_TIME_FORMAT, timezone = DateTimeConstant.TIME_ZONE) //Format định dạng giờ địa phương
    private Timestamp createdTime;
}
