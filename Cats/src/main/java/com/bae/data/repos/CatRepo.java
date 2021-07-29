package com.bae.data.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.data.Cat;

@Repository
public interface CatRepo extends JpaRepository<Cat, Integer> {

	List<Cat> findByNameIgnoreCase(String name);

}
