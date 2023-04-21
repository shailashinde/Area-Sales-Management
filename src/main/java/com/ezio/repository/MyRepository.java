package com.ezio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezio.model.MyData;

@Repository("MyRepository")
public interface MyRepository extends JpaRepository<MyData, Long> {
}
