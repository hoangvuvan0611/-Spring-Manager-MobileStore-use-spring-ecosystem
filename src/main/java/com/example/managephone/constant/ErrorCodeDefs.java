package com.example.managephone.constant;

public class ErrorCodeDefs {
    public static final int OTHER = 1;
    public static final int VADIDATION_ERROR = 2;
    public static final int SERVER_ERROR = 500;
    public static final int BAD_REQUES = 400;
    public static final int TOKEN_INVALID = 404;

    public static final String getErrMsg(int code){
        switch (code){
            case OTHER:
                return "Các Lỗi khác!";
            case VADIDATION_ERROR:
                return "Tham số không hợp lệ!";
            case SERVER_ERROR:
                return "Lỗi hệ thống";
            case BAD_REQUES:
                return "Bad request";
            default:
                return "Không xác định";
        }
    }
}
