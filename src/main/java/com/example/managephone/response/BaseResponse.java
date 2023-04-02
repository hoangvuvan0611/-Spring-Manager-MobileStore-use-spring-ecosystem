package com.example.managephone.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse {
    private boolean success = false;    //Trạng thái lỗi
    private Error error;    //Hàm chứa lỗi bên trong

    public void setFalsed(int code, String message){    //Truyền lỗi vào bên trong khi xảy ra false
        error = new Error(code, message, null);
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Error{
        private int code;   //hiển thị mã lỗ ví dụ như 404, 500, 400
        private String message;     //hiển thị loại lỗi, có thể là lỗi hệ thống, lỗi do người dùng nhập vào
        private List<ErrorDetail> errorDetailList;  //Trong list sẽ chứa rõ chi tiết lỗi được phát ra từ đâu
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ErrorDetail{
        private String message;     //Chi tiết nỗi hiển thị của mỗi trường, lỗi được trả về nhờ validate
        private String id;      //trả về id của bản ghi có lỗi
    }
}
