package com.test.smallTest;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by Mumuya on 2020/1/3
 */
public class streamTest {
    public static void main(String[] args){
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(toList());
        System.out.println(filtered);
        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

        //遍历、取指定数量、排序
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);

        // 映射    获取对应的平方数
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(toList());
        System.out.println(squaresList);
        //统计
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());

        //过滤   获取空字符串的数量
        List<String>strings1 = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        long count = strings1.stream().filter(string -> string.isEmpty()).count();
        System.out.println(count);
    }
}
