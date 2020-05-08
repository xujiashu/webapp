package com.webapp;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Configuration注释Spring会自动加载配置
 * @author Administrator
 *
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer{

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(new Formatter<LocalDate>() {

			/**
			 * 将字符串转换成日期,格式为2019-01-02的字符串
			 */
			@Override
			public LocalDate parse(String text, Locale locale) throws ParseException {
				return LocalDate.parse(text);
			}

			
			/**
			 * 
			 * 将日期转换成字符串
			 */
			@Override
			public String print(LocalDate object, Locale locale) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd",locale);
				return dtf.format(object);
			}
			
		});
		WebMvcConfigurer.super.addFormatters(registry);
	}
	
	
	
	

}
