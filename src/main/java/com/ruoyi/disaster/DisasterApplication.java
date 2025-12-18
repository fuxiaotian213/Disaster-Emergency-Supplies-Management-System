package com.ruoyi.disaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan("com.ruoyi.disaster.mapper")
public class DisasterApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(DisasterApplication.class, args);
        System.out.println("(๑•̀ㅂ•́)و✧  灾时物资应急管理系统启动成功  ✧٩(ˊωˋ*)و✧  \n" +
                "       .----------------.  .----------------.  .----------------. \n" +
                "      | .--------------. || .--------------. || .--------------. |\n" +
                "      | |  _________   | || |  ____  ____  | || |    ______    | |\n" +
                "      | | |  _   _  |  | || | |_  _||_  _| | || |  .' ___  |   | |\n" +
                "      | | |_/ | | _|  | || |   \\   / /   | || | / .'   _|   | |\n" +
                "      | |     | |      | || |    \\ / /    | || | | |    ____  | |\n" +
                "      | |    _| |_     | || |    _|  |_    | || | \\ `.___]  _| | |\n" +
                "      | |   |_____|    | || |   |______|   | || |  `._____.'   | |\n" +
                "      | |              | || |              | || |              | |\n" +
                "      | '--------------' || '--------------' || '--------------' |\n" +
                "       '----------------'  '----------------'  '----------------' \n" +
                "          应急保障 · 物资先行 · 守护生命 · 共渡难关            \n" +
                "      ┌─────────────────────────────────────────────────────┐\n" +
                "      │                     响应及时 · 调配精准                │\n" +
                "      └─────────────────────────────────────────────────────┘");
    }
    
    /**
     * 配置跨域支持
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}