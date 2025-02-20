package java常用类.Date类;

import java.time.*;
import java.util.Date;

/**
 * @author zijian Wang
 */
public class LocalDateTest {


    public static void main(String[] args) throws InterruptedException {
        Date date = new Date();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        /**
         * 按照时区获取日期时间
         */
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC+4"));
        ZoneId zoneId = ZonedDateTime.now().getZone();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);

        System.out.println(localDate);
        System.out.println(localTime);

        System.out.println("带有时区的localDateTime：" + localDateTime);
        System.out.println("zonedDateTime" + zonedDateTime);


        /**
         * 判断时间先后
         */
        boolean after = LocalTime.now().isAfter(localTime);
        System.out.println("是否在这时间之后：" + after);
        /**
         * 计算时间间隔
         */
        LocalTime start = LocalTime.parse(LocalTime.now().toString());
//        TimeUnit.SECONDS.sleep(1);
        LocalTime end = LocalTime.parse(LocalTime.now().toString());
        Duration duration = Duration.between(start, end);
        System.out.println("时间间隔:"+duration.toMillis());
    }
}
