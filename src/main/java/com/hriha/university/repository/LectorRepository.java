package com.hriha.university.repository;

import com.hriha.university.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {

}
