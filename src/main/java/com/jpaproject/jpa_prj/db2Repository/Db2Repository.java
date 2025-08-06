package com.jpaproject.jpa_prj.db2Repository;

import com.jpaproject.jpa_prj.db2Entity.DB2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Db2Repository extends JpaRepository<DB2, Long> {
}
