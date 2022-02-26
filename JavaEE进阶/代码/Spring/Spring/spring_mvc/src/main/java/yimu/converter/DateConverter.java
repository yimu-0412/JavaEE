package yimu.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author
 * @Program
 * @create 2022-02-26-22:55
 */
public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        // 1.将日期的字符串转成日期对象，返回
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
