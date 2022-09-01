package duan.atripic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("duan.atripic.mapper")
@SpringBootApplication
public class AtriPicApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtriPicApplication.class, args);
    }

}
