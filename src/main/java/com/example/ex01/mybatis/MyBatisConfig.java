package com.example.ex01.mybatis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration // 설정 관련 클래스임을 표시
@MapperScan("com.example.ex01.mapper") // 작성한 패키지에서 @Mapper 스캔
@RequiredArgsConstructor
public class MyBatisConfig {

//    커넥션 풀 및 MyBatis에 필요한 요소를 메모리에 할당 및 관리, xml과 java연동에 필요한 경로 관리
    private final ApplicationContext applicationContext;
    @Bean // 클래스 내에 있는 필드를 spring에 등록
    @ConfigurationProperties(prefix = "spring.datasource.hikari") // 상위 경로 고정, properties에서 해당 경로 설정 불러오기(호출)
    public HikariConfig hikariConfig(){ return new HikariConfig(); } // application.properties에 작성된 #JDBC datasource 정보 설정

    @Bean
    public DataSource dataSource(){ return new HikariDataSource(hikariConfig()); } // datasource객체에 DBMS 정보 설정

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean(); // 세션 팩토리 설정 객체 생성
        sqlSessionFactoryBean.setDataSource(dataSource()); // 위에서 설정한 datasource객체를 세션 팩토리 설정에 전달
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:/mapper/*.xml")); // SQL 쿼리를 작성할 xml 경로 설정

        try {
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject(); // 세션 팩토리 생성
            sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true); // 팟홀 표기법을 카멜 표기법으로 자동 변경 설정
            return sqlSessionFactory; // 완성된 세션 팩토리 리턴
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
















