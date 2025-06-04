package com.object_oriented_case.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.object_oriented_case.portfolio.model.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {

}
