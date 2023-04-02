package com.example.managephone.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

//Class được tạo ra mới mục đích nhận giá trị nhập đầu vào để validate,
//chứ không phải dùng dto để nhận vào, hoặc entity mà class này sẽ map sang entity hoặc dto

//chỉ validate tên vì đây là hàm insert tên không được để trống, ngày tháng thì lấy ngày tháng
//ngày theo thời gian thực

@Data
public class CreateMobileRequest {
    @NotBlank(message = "Tên không được để trống")
    @Size(min = 6, max = 200, message = "Tên phải có tối thiểu 6 kí tự, tối đa 200 kí tự")
    private String name;

    private String description;
}
