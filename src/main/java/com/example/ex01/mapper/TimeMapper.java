package com.example.ex01.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

// SQl이 복잡하거나 길어지는 경우에는 어노테이션 보다는 XML을 이용하는 방식을 더 선호하게 된다.

@Mapper // 마이바티스 연결 등록(xml id 매핑)
public interface TimeMapper {
//    @Select("SELECT SYSDATE FROM DUAL")
    public String getTime();
}
