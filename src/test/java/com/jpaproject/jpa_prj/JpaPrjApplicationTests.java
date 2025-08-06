package com.jpaproject.jpa_prj;

import com.jpaproject.jpa_prj.db1Entity.DB1;
import com.jpaproject.jpa_prj.db1Repository.Db1Repository;
import com.jpaproject.jpa_prj.db2Entity.DB2;
import com.jpaproject.jpa_prj.db2Repository.Db2Repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@SpringBootTest
class JpaPrjApplicationTests {
    @Autowired
    private Db1Repository db1Repository;
    @Autowired
    private Db2Repository db2Repository;

    @Test
    void main() {
        System.out.println("실행ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
        System.out.println(db1Repository.findAll());

        // ghlove에서는 mybatis를 이용해서, select를 할 것이고
        // select 시 ResultType을 Analysis의 GCntrTemp 같은 엔티티로 할 것임
        // 현재 테스트에선 DB1을 DB2로 변환하는 과정이 필요
        List<DB2> list = db1Repository.findAll().stream().map(
                db1 -> DB2.builder()
                        .db2Col1(db1.getDb1Col1())
                        .db2Col2(db1.getDb1Col2())
                        .db2Col3(db1.getDb1Col3())
                        .db2Col4(db1.getDb1Col4())
                        .build()
                ).toList();
        long currentTime = System.currentTimeMillis();
        db2Repository.saveAll(list);
        long endTime = System.currentTimeMillis();


        List<DB2> findList = db2Repository.findAll();
        System.out.println("1. 저장할 데이터 리스트 크기 = " + list.size());
        System.out.println("2. 저장에 소요된 시간  = " + (endTime - currentTime) + " ms");
        System.out.println("3. 저장된 데이터 리스크 크기 = " + findList.size());
    }

    @Test
    void makeData() {
        for(int i = 1; i <= 100000; i++) {
            init();
        }
    }

    void init() {
        for(int i = 1; i <= 5; i++) {
            DB1 db1 = DB1.builder()
                    .db1Col1("tmpData"+ i +"Col1")
                    .db1Col2("tmpData"+ i +"Col1")
                    .db1Col3("tmpData"+ i +"Col1")
                    .db1Col4("tmpData"+ i +"Col1")
                    .build();
            db1Repository.save(db1);
        }
    }

}
