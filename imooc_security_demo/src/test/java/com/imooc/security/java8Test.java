package com.imooc.security;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.core.social.qq.api.QQUserInfo;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class java8Test {
    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    @Test
    public void test1(){
        Comparator<Integer> com=new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 01-02;
            }
        };
        TreeSet<Integer> ts=new TreeSet<>(com);

        //lambda表达式
        Comparator<Integer> comparator=(x,y)->Integer.compare(x,y);

    }


    //优化方式一：策略设计模式
    public List<Employee> filterEmployee(List<Employee> emps, MyPredicate<Employee> mp){
        List<Employee> list = new ArrayList<>();

        for (Employee employee : emps) {
            if(mp.test(employee)){
                list.add(employee);
            }
        }

        return list;
    }

    @Test
    public void oo(){
      /*  List<Employee> employees = filterEmployee(emps, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() > 10;
            }
        });
        System.out.println(employees.toString());*/

        List<Employee> employees = filterEmployee(emps, employee -> employee.getAge() > 30);
        employees.forEach(System.out::println);

    }

    @Test
    public void streamApi(){
        emps.stream().filter(employee ->employee.getSalary()>5000).forEach(System.out::println);
        emps.stream().map(Employee::getSalary).forEach(System.out::println);

    }

private String  aa="{\n" +
        "    \"ret\": 0,\n" +
        "    \"msg\": \"\",\n" +
        "    \"nickname\": \"_浪里白条丶菜小贱\",\n" +
        "    \"gender\": \"男\",\n" +
        "    \"province\": \"浙江\",\n" +
        "    \"city\": \"绍兴\",\n" +
        "    \"year\": \"1996\",\n" +
        "    \"constellation\": \"\",\n" +
        "    \"figureurl\": \"http:\\/\\/qzapp.qlogo.cn\\/qzapp\\/101494291\\/249D7047A7A022B7ACDB798DF5CC39B4\\/30\",\n" +
        "    \"figureurl_1\": \"http:\\/\\/qzapp.qlogo.cn\\/qzapp\\/101494291\\/249D7047A7A022B7ACDB798DF5CC39B4\\/50\",\n" +
        "    \"figureurl_2\": \"http:\\/\\/qzapp.qlogo.cn\\/qzapp\\/101494291\\/249D7047A7A022B7ACDB798DF5CC39B4\\/100\",\n" +
        "    \"figureurl_qq_1\": \"http:\\/\\/thirdqq.qlogo.cn\\/qqapp\\/101494291\\/249D7047A7A022B7ACDB798DF5CC39B4\\/40\",\n" +
        "    \"figureurl_qq_2\": \"http:\\/\\/thirdqq.qlogo.cn\\/qqapp\\/101494291\\/249D7047A7A022B7ACDB798DF5CC39B4\\/100\"\n" +
        "}";

    @Test
    public void aa(){
         ObjectMapper objectMapper=new ObjectMapper();
         objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        try {
            QQUserInfo userInfo = objectMapper.readValue(aa, QQUserInfo.class);
            System.out.println(userInfo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
