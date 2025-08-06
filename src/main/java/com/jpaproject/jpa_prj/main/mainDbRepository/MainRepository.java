package com.jpaproject.jpa_prj.main.mainDbRepository;

import com.jpaproject.jpa_prj.main.mainDbEntity.Main;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainRepository extends JpaRepository<Main, Long> {
}
