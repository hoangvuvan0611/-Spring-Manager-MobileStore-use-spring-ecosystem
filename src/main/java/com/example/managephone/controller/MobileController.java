package com.example.managephone.controller;

import com.example.managephone.dto.MobileDto;
import com.example.managephone.entity.Mobile;
import com.example.managephone.request.CreateMobileRequest;
import com.example.managephone.response.BaseListItemResponse;
import com.example.managephone.response.BaseResponse;
import com.example.managephone.service.MobileService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/mobile")
public class MobileController {
    private MobileService mobileService;

    @Autowired
    MobileController(MobileService mobileService){
        this.mobileService = mobileService;
    }

    @GetMapping("/all")
    public ResponseEntity<BaseListItemResponse<MobileDto>> getAllMobile(){
        BaseListItemResponse<MobileDto> baseListItemResponse = new BaseListItemResponse<>();
        if(mobileService.getListMobile().isEmpty()){
            baseListItemResponse.setSuccess(false);
            baseListItemResponse.setResult(null, 0);
            return ResponseEntity.ok(baseListItemResponse);
        }

        baseListItemResponse.setSuccess(true);
        baseListItemResponse.setResult(mobileService.getListMobile(), mobileService.getListMobile().size());
        return ResponseEntity.ok(baseListItemResponse);
    }

    @PostMapping("")
    public ResponseEntity<MobileDto> createMobile(@Valid @RequestBody CreateMobileRequest request){
        return ResponseEntity.ok(mobileService.create(request));
    }

}
