//package com.zdk.web.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
//import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
//import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
//import org.springframework.boot.autoconfigure.AutoConfigureBefore;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
//import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.Locale;
//import java.util.TimeZone;
//
//
///**
// * 日期转换自定义模块
// */
//@Configuration
//@ConditionalOnClass(ObjectMapper.class)
//@AutoConfigureBefore(JacksonAutoConfiguration.class)
//public class JacksonConfig {
//
//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer customizer() {
//        return jacksonObjectMapperBuilder -> {
//            // 设置时间
//            jacksonObjectMapperBuilder.locale(Locale.CHINA);
//            // 设置时区
//            jacksonObjectMapperBuilder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
//            // 设置simpleDateFormat
//            jacksonObjectMapperBuilder.simpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            // 设置modules
//            jacksonObjectMapperBuilder.modules(new JavaTimeModule());
//        };
//    }
//
//    // SimpleModule是jackson.databind提供的一个module模块
//    public static class JavaTimeModule extends SimpleModule {
//        // 无参构造，一旦new JavaTimeModule()便直接执行
//        public JavaTimeModule() {
//            // 添加各种各样的日期格式
//            this.addSerializer(LocalDateTime.class
//                    , new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
//            this.addSerializer(LocalDate.class
//                    , new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//            this.addSerializer(LocalTime.class
//                    , new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
//            this.addDeserializer(LocalDateTime.class
//                    , new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//            this.addDeserializer(LocalDate.class
//                    , new LocalDateDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
//            this.addDeserializer(LocalTime.class
//                    , new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
//        }
//    }
//}