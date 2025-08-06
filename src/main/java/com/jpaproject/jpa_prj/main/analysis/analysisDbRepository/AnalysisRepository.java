package com.jpaproject.jpa_prj.main.analysis.analysisDbRepository;

import com.jpaproject.jpa_prj.main.analysis.analysisDbEntity.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
}
