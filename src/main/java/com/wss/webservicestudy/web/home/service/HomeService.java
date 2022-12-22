package com.wss.webservicestudy.web.home.service;

import com.wss.webservicestudy.web.home.controller.HomeController;
import com.wss.webservicestudy.web.home.entity.TestEntity;
import com.wss.webservicestudy.web.home.repository.TestdataRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {

    private final TestdataRepository testdataRepository;

    public HomeService(TestdataRepository testdataRepository) {
        this.testdataRepository = testdataRepository;
    }

    public List<String> toTestdataRepository() {
        List<TestEntity> exList = testdataRepository.findAll();
        List<String> exString = new ArrayList<>();
        for(TestEntity exEntity : exList) {
            exString.add(exEntity.getTeamMember());
        }
        return exString;
    }
}
