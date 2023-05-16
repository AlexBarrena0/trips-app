package edu.uoc.abarrena.trips.infrastructure.repository.config;

import edu.uoc.abarrena.trips.domain.repository.*;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.*;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.*;
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
    @Bean
    public TripRepository tripRepository(TripMapper tripMapper) {
        return new TripRepositoryImpl(tripMapper);
    }

    @Bean
    public BookingRepository bookingRepository(BookingMapper bookingMapper) {
        return new BookingRepositoryImpl(bookingMapper);
    }

    @Bean
    public RatingRepository ratingRepository(RatingMapper ratingMapper) {
        return new RatingRepositoryImpl(ratingMapper);
    }
}
