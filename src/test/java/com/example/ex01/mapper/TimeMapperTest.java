package com.example.ex01.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// MyBatis는 내부적으로 JDBC의 PreparednStatement를 이용해서 SQL에 처리한다.
// 따라서 SQL에 전달되는 파라미터는 JDBC에서와 같이 '?'로 치환되어 처리된다.
// 복잡한 SQL의 경우 '?'로 나오는 값이 제대로 되었는지 확인하기가 쉽지 않고 실행된 SQL의 내용을
// 정확히 확인하기 어렵기 때문에 log4-jdbc-log4j2 라이브러리를 사용하여 어떤 값인지 확인한다.

@SpringBootTest
@Slf4j
public class TimeMapperTest {

    @Autowired
    private TimeMapper timeMapper;

    @Test
    public void testGetTime(){
        String sysdate = timeMapper.getTime();
        log.info("-------------------------------------------");
        log.info("sysdate : " + sysdate);
        log.info("-------------------------------------------");

    }

}
