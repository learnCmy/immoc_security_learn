package com.imooc.security;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestStreamApI {

    @Test
    public void test1(){
        //1.给定一个数字列表，返回一个由每个数的平方构成的列表
        Integer[] num=new Integer[]{1,2,3,4};
        Arrays.stream(num).map((x)->x*x).forEach(System.out::println);
     }


    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    @Test
    public void test2(){
        //reduce归约 ，将流中元素反复结合起来
        //怎样 用map和reduce方法数一数流中有多少个Employee
        Optional<Integer> reduce = emps.stream().map((e) -> 1).reduce(Integer::sum);
        System.out.println(reduce.get());
    }
    


}
