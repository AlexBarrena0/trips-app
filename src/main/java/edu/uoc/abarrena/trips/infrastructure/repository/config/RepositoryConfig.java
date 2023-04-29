package edu.uoc.abarrena.trips.infrastructure.repository.config;

import edu.uoc.abarrena.trips.domain.repository.CruiseRepository;
import edu.uoc.abarrena.trips.domain.repository.DestinationRepository;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.CruiseRepositoryImpl;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.DestinationRepositoryImpl;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.CruiseMapper;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.DestinationMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper")
public class RepositoryConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }
    @Bean
    public DestinationRepository destinationRepository(DestinationMapper destinationMapper) {
        return new DestinationRepositoryImpl(destinationMapper);
    }

    @Bean
    public CruiseRepository cruiseRepository(CruiseMapper cruiseMapper) {
        return new CruiseRepositoryImpl(cruiseMapper);
    }
}
