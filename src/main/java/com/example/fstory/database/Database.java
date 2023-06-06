package com.example.fstory.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.fstory.repositories.TacGiaRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase (TacGiaRepositories tacgiaRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                Tacgia tacgiaA = new Tacgia("Giang Đặng", "giang-dang");
//                Tacgia tacgiaB = new Tacgia("Đặng Nhật Giang", "dang-nhat-giang");
//                logger.info ("insert data: "+ tacgiaRepository.save(tacgiaA));
//                logger.info ("insert data: "+ tacgiaRepository.save(tacgiaB));
            }
        };
    }
}
