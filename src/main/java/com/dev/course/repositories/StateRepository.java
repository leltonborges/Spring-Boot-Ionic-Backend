package com.dev.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.course.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{

}
