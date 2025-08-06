package com.jpaproject.jpa_prj;

import com.jpaproject.jpa_prj.analysis.analysisDbEntity.Analysis;
import com.jpaproject.jpa_prj.main.mainDbEntity.Main;
import com.jpaproject.jpa_prj.main.mainDbRepository.MainRepository;
import com.jpaproject.jpa_prj.analysis.analysisDbRepository.AnalysisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JpaPrjApplicationTests {
    @Autowired
    private MainRepository mainRepository;
    @Autowired
    private AnalysisRepository analysisRepository;

    @Test
    void main() {
        System.out.println("실행ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");

        // ghlove에서는 mybatis를 이용해서, select를 할 것이고
        // select 시 ResultType을 Analysis의 GCntrTemp 같은 엔티티로 할 것임
        // 현재 테스트에선 main을 analysis로 변환하는 과정이 필요
        List<Analysis> list = mainRepository.findAll().stream().map(
                main -> Analysis.builder()
                        .analysisCol1(main.getMainCol1())
                        .analysisCol2(main.getMainCol2())
                        .analysisCol3(main.getMainCol3())
                        .analysisCol4(main.getMainCol4())
                        .build()
                ).toList();
        long currentTime = System.currentTimeMillis();
        analysisRepository.saveAll(list);
        long endTime = System.currentTimeMillis();


        List<Analysis> findList = analysisRepository.findAll();
        System.out.println("1. 저장할 데이터 리스트 크기 = " + list.size());
        System.out.println("2. 저장에 소요된 시간  = " + (endTime - currentTime) + " ms");
        System.out.println("3. 저장된 데이터 리스크 크기 = " + findList.size());
    }

    @Test
    void makeData() {
        for(int i = 1; i <= 1000; i++) {
            init();
        }
    }

    void init() {
        for(int i = 1; i <= 5; i++) {
            Main main = Main.builder()
                    .mainCol1("tmpData"+ i +"Col1")
                    .mainCol2("tmpData"+ i +"Col1")
                    .mainCol3("tmpData"+ i +"Col1")
                    .mainCol4("tmpData"+ i +"Col1")
                    .build();
            mainRepository.save(main);
        }
    }

}
