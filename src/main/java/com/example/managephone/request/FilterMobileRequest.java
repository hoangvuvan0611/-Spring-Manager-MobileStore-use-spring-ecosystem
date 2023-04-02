package com.example.managephone.request;

import com.example.managephone.validator.DateValidateAnnotation;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

//Validate các giá trị nhập vào để tìm kiếm
@Data
public class FilterMobileRequest {
    private Integer typeFrom;
    private Integer typeTo;
    private Integer active;
    @DateValidateAnnotation(message = "Định dạng ngày phải là dd/MM/yyyy")
    private String dateFrom;
    @DateValidateAnnotation(message = "Định dạng ngày phải là dd/MM/yyyy")

    private String dateTo;
    private String name;
    @NotNull(message = "Start không được để trống")
    private Integer start;
    @NotNull(message = "Limit không được để trống")

    private Integer limit;
}
