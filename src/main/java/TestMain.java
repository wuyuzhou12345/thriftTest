import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;

@SpringBootApplication(scanBasePackages = {"com.zhou"})
@MapperScan("com.zhou.biz.mapper") //使用MapperScan批量扫描所有的Mapper接口；
@Slf4j
public class TestMain {
    public static void main(String[] args) {
        new SpringApplicationBuilder(TestMain.class)
            .bannerMode(Banner.Mode.OFF)
            .web(WebApplicationType.SERVLET)
            .run(args);
        }
}
