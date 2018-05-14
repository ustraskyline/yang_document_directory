package org.fkit.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

/**
 * @Description: <br>
 *               网站：<a href="http://www.fkit.org">疯狂Java</a>
 * @author 肖文吉 36750064@qq.com
 * @version V1.0
 */

public class DateFormatter implements Formatter<Date> {

	// 日期类型模板：如yyyy-MM-dd
	private String datePattern;
	// 日期格式化对象
	private SimpleDateFormat dateFormat;

	// 构造器，通过依赖注入的日期类型创建日期格式化对象
	public DateFormatter(String datePattern) {
		this.datePattern = datePattern;
		this.dateFormat = new SimpleDateFormat(datePattern);
	}

	// 将Date->String时，调用print()方法
	@Override
	public String print(Date date, Locale locale) {

		System.out.println("--->调用print(): date=" + date);
		return dateFormat.format(date);
	}

	// 将String->Date时，调用parse()方法
	@Override
	public Date parse(String source, Locale locale) throws ParseException {
		try {
			System.out.println("===>调用parse(): source=" + source);
			return dateFormat.parse(source);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
}
