package com.example.managephone.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseListItemResponse <T> extends BaseResponse{
    private DataList<T> data;
    @Data
    public static class DataList<T>{
        private Long total= 0L;
        private List<T> items;
    }

    public void setResult(List<T> items, long total){
        data = new DataList<>();
        data.setItems(items);
        data.setTotal(total);
    }
}
