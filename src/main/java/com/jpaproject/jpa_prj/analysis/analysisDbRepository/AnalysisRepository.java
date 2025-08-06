package com.jpaproject.jpa_prj.analysis.analysisDbRepository;

import com.jpaproject.jpa_prj.analysis.analysisDbEntity.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
}
