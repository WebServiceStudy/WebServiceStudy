package com.wss.webservicestudy.web.home.repository;

import com.wss.webservicestudy.web.home.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestdataRepository extends JpaRepository<TestEntity, Long> {



}
