package com.wss.webservicestudy.web.home.service;

import com.wss.webservicestudy.web.home.entity.TestEntity;
import com.wss.webservicestudy.web.user.repository.TestdataRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {

    private final TestdataRepository testdataRepository;

    public HomeService(TestdataRepository testdataRepository) {
        this.testdataRepository = testdataRepository;
    }

    public List<String> toTestdataRepository() {
        List<TestEntity>
                exList = testdataRepository.findAll();
        List<String> exString = new ArrayList<>();
        for(TestEntity exEntity : exList) {
            exString.add(exEntity.getTeamMember());
        }
        return exString;
    }
}
