package com.test.smallTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by Mumuya on 2020/1/16
 */
public class ZonedDateTimeTest {
    public static void main(String args[]){
        ZonedDateTimeTest timeTest = new ZonedDateTimeTest();
        timeTest.testZonedDateTime();
    }

    public void testZonedDateTime(){
        // 获取当前时间日期
        ZonedDateTime date1 = ZonedDateTime.parse("2020-01-16T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);
    }
}
