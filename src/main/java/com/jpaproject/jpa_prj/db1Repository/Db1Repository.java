package com.jpaproject.jpa_prj.db1Repository;

import com.jpaproject.jpa_prj.db1Entity.DB1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Db1Repository extends JpaRepository<DB1, Long> {
}
