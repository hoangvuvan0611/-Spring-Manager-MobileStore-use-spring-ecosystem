package com.example.managephone.service;

import com.example.managephone.dto.MobileDto;
import com.example.managephone.entity.Mobile;
import com.example.managephone.reponsitory.CustomRepository;
import com.example.managephone.reponsitory.MobileReponsitory;
import com.example.managephone.request.CreateMobileRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MobileService {
    private MobileReponsitory mobileReponsitory;
    private CustomRepository customRepository;

    @Autowired
    public MobileService(MobileReponsitory mobileReponsitory, CustomRepository customRepository) {
        this.mobileReponsitory = mobileReponsitory;
        this.customRepository = customRepository;
    }

    public List<MobileDto> getListMobile() {
        ModelMapper modelMapper = new ModelMapper();
        return mobileReponsitory.findAll().stream().map(
                mobile -> modelMapper.map(mobile, MobileDto.class)
        ).collect(Collectors.toList());
    }

    public List<Mobile> getListMobile(Sort.Direction direction, String... fieldSort) {
        return mobileReponsitory.findAll(Sort.by(direction, fieldSort));
    }

    @Transactional
    public MobileDto create(CreateMobileRequest request) {
        Mobile mobile = Mobile.builder()
                .name(request.getName())
                .description(request.getDescription())
                .createdTime(new Timestamp(System.currentTimeMillis()))
                .build();

        mobile = mobileReponsitory.saveAndFlush(mobile);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mobile, MobileDto.class);
    }

    @Transactional
    public MobileDto update(CreateMobileRequest request) {
        Mobile mobile = Mobile.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        mobileReponsitory.save(mobile);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mobile, MobileDto.class);
    }

    public Mobile findById(Long id) {
        return mobileReponsitory.findById(id).orElseThrow(() -> new RuntimeException("Mobile not found"));
    }

    public Page<Mobile> filterMobileList(Integer typeFrom, Integer typeTo,
                                         Integer active,
                                         Date dateFrom, Date dateTo,
                                         String name,
                                         Integer start, Integer limit) {
        Specification<Mobile> specification = CustomRepository.buildFilterSpecification(typeFrom, typeTo, active, dateFrom, dateTo, name);
        return mobileReponsitory.findAll(specification, PageRequest.of(start, limit));
    }

}
