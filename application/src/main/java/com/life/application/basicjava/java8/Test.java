package com.life.application.basicjava.java8;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * @author tengyun
 * @date 2021/1/19 17:01
 **/
public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }


    public void test() {
        List<Person> data = Data.getData();

        /*
         * map 的作用是将一个对象经过处理，映射为另一个对象
         */
        List<Integer> nameLengthList = data.stream().map(item -> item.getName().length()).collect(Collectors.toList());
        List<Long> idList = data.stream().map(Person::getId).collect(Collectors.toList());
        List<Long> uidList = data.stream().map(person -> {
            long uidLong = Long.parseLong(person.getUid());
            return uidLong + 123L;
        }).collect(Collectors.toList());

        System.out.println(nameLengthList);
        System.out.println(idList);
        System.out.println(uidList);

        /*
         * flatMap 是把一个集合，这个集合里面的元素是多个集合，flatMap可以把这个集合抚平成一个集合
         * 而map只会将集合中的每个元素映射成另一个元素
         */
        List<List<Person>> personLists = Data.getPersonLists();

        List<List<Long>> personIdListList = personLists.stream().map(persons -> persons.stream().map(Person::getId).collect(Collectors.toList())).collect(Collectors.toList());

        List<Long> personIdList = personLists.stream().flatMap(personList -> personList.stream().map(Person::getId)).collect(Collectors.toList());

        System.out.println(personIdListList);
        System.out.println(personIdList);

        /*
         * filter 的作用是根据某个条件将合格的对象筛选出来
         */
        List<Person> peronIdFilter = data.stream().filter(person -> person.getId() > 2L).collect(Collectors.toList());
        List<Person> personSexFilter = data.stream().filter(person -> "女".equals(person.getSex())).collect(Collectors.toList());

        System.out.println(peronIdFilter);
        System.out.println(personSexFilter);

//        data.forEach(person -> person.setId(123L));

        // 获取最大的一个，注意这里面的比较器的写法
        Optional<Person> max = data.stream().max((o1, o2) -> {
            if (o1.getId() > o2.getId()) {
                return 1;
            } else if (o1.getId().equals(o2.getId())) {
                return 0;
            } else {
                return -1;
            }
        });
        max.ifPresent(person -> {
            person.setName("我是最大的1");
            System.out.println(person);
        });

        System.out.println("max之后 ++++++++++++++++++++++++++");
        System.out.println(data);

        // 错误的比较器写法示例
        data.stream().max((o1, o2) -> {
            if (o1.getId() < o2.getId()) {
                return 1;
            } else {
                return 0;
            }
        }).ifPresent(System.out::println);


        Optional<Person> min = data.stream().min((o1, o2) -> {
            if (o1.getId() > o2.getId()) {
                return 1;
            } else if (o1.getId().equals(o2.getId())) {
                return 0;
            } else {
                return -1;
            }
        });
        min.ifPresent(person -> {
            person.setUid("我是最小的");
            System.out.println(person);
        });
        System.out.println(data);

        long count = data.stream().count();

        System.out.println(count);

        /*
         * 排序
         */
        List<Person> sortPersonList = data.stream().sorted((o1, o2) -> {
            // 从小到大
            return (int) (o1.getId() - o2.getId());
            // 从大到小
            // return (int) (o2.getId() - o1.getId());
        }).collect(Collectors.toList());
        System.out.println(sortPersonList);

        Optional<Person> first = data.stream().findFirst();
        first.ifPresent(person -> System.out.println(person.getName()));

        // 对每个元素做下操作，用于debugging
        List<Person> collect = data.stream().peek(person -> {
            person.setUid("hi 就是我");
            System.out.println(person);
        }).collect(Collectors.toList());
        System.out.println(collect);

        /*
         * collect 将这个流变成一个list、map等常用的数据结构
         * Collectors.toList()
         * Collectors.toSet()
         * Collectors.toMap()
         * Collectors.joining()
         * 自定义的
         */
        //
        List<Person> updateUidList = data.stream().peek(person -> person.setUid(person.getUid() + "333")).collect(Collectors.toList());
        System.out.println(updateUidList);
        //
        Map<Long, String> idAndNameMap = data.stream().peek(person -> person.setUid(person.getUid() + "333")).collect(Collectors.toMap(Person::getId, Person::getName));
        System.out.println(idAndNameMap);
        //
        Set<Person> set = data.stream().peek(person -> person.setUid(person.getUid() + "333")).collect(Collectors.toSet());
        System.out.println(set);
        //
        Map<Long, List<Person>> groupBy = data.stream().peek(person -> person.setUid(person.getUid() + "333")).collect(Collectors.groupingBy(person -> person.getId() + 1));
        System.out.println(groupBy);
        //
        String collect1 = data.stream().map(person -> person.getUid() + "333").collect(Collectors.joining("+++", "@@@", "!!!"));
        System.out.println(collect1);
        //
        Double collect2 = data.stream().map(Person::getId).collect(Collectors.averagingLong(uid -> uid));
        System.out.println(collect2);
        //
        Long collect3 = data.stream().collect(Collectors.summingLong(person -> person.getId()));
        Long collect4 = data.stream().mapToLong(Person::getId).sum();
        System.out.println(collect3);
        System.out.println(collect4);
        //
        ConcurrentMap<Long, String> collect5 = data.stream().collect(Collectors.toConcurrentMap(Person::getId, Person::getUid));
        System.out.println(collect5);

        /*
         * Optional 替换null的使用
         */
        Optional<Person> person2 = Optional.of(data.get(0));

        Person personTest = get();

        person2.ifPresent(perso -> {
            perso.setSex("我不为空，我改性别了");
            System.out.println(perso);
        });


        List<Long> idList1 = data.parallelStream().map(person -> {
            System.out.println(person.getName());
            return person.getId();
        }).collect(Collectors.toList());
        System.out.println(idList1);
    }

    private Person get() {
        return null;
    }

}
