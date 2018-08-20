package yhy.testForeach;

import org.springframework.util.ObjectUtils;
import yhy.pojo.User;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.io.File.separator;

/**
 * @Author: yhy
 * @Date: 2018/8/20 16:35
 * @Version 1.0
 */
public class ForeacheTests {
    public static void main(String[] args) {
        User user = new User();
        user.setId(23);
        user.setUsername("yang");
        user.setPassword("124");
        user.setEmail("yang@qq.ocm");
        user.setAge(20);
        user.setPhone("987456146");

        User users = new User();
        users.setId(25);
        users.setUsername("hao");
        users.setPhone("rghrehyr");
        users.setEmail("hao@qq.ocm");
        users.setPassword("124");
        users.setAge(56);

        List<User> lists = new ArrayList<>();
        lists.add(user);
        lists.add(users);
        /*使用forEach方式*/
        lists.forEach(e -> {
            System.out.println(e.getEmail());
        });
        System.out.println(lists.stream().map(demo -> demo.getId()).collect(Collectors.toList()));
        /*使用stream().map方法*/
        List<Integer> collect = lists.stream().map(demo -> demo.getId()).collect(Collectors.toList());
        System.out.println(collect.get(0));
        List list = new ArrayList();
        list.add(3);
        list.add(5);
        list.add(6);
        Iterator iterator2 = list.iterator();
        Map map = new HashMap();
        map.put("y", "yang");
        map.put("h", "hao");
        map.put("g", "ying");
        Iterator iterator = map.entrySet().iterator();
        for (Iterator iterator1 = map.entrySet().iterator(); iterator1.hasNext(); ) {
            Map.Entry entry = (Map.Entry) iterator1.next();
            System.out.println(entry.getKey()+"---------"+entry.getValue());
        }
        System.out.println(iterator);
        String join = join(iterator2,",");
        System.out.println("----jions----"+join);

    }

    public static String join(Iterator<?> iterator, String separator) {
        if (iterator == null) {
            return null;
        } else if (!iterator.hasNext()) {
            return "";
        } else {
            Object first = iterator.next();
            if (!iterator.hasNext()) {
                String result = "";//ObjectUtils.toString(first);
                return result;
            } else {
                StringBuilder buf = new StringBuilder(256);
                if (first != null) {
                    buf.append(first);
                }

                while (iterator.hasNext()) {
                    if (separator != null) {
                        buf.append(separator);
                    }

                    Object obj = iterator.next();
                    if (obj != null) {
                        buf.append(obj);
                    }
                }

                return buf.toString();
            }
        }
    }

}
