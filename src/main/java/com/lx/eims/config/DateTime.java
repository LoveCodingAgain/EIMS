package com.lx.eims.config;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.format.DateTimeFormatter;

/**
 * @author: lixing
 * date: 2019-03-24
 * time: 14:44
 * description:时间格式化
 */
@Configuration
public class DateTime {
    /**
     * 时间格式
     */
    private static final  String dateFormat="yyyy-MM-dd";
    /**
     * 日期格式
     */
    private static final String  dateTimeFormat="yyyy-MM-dd HH:mm:ss";

    /**
     * 兼容JAVA8的时间API
     * @return
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer(){
        return builder ->{
            builder.simpleDateFormat(dateFormat);
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
        };
    }
}
