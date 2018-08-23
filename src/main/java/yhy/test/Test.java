package yhy.test;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class Test {
    private static Logger logger = Logger.getLogger(Test.class);



    public static void main(String[] args) {

        logger.info("我是INFO日志");
        logger.warn("我是warn日志");
        logger.fatal("我是fatal日志");
        logger.error("我是error日志");

        String[] str = new String[]{"foo","boo"};
        List<String> list = Arrays.asList(str);
        logger.info("list->:"+ list.get(0));
        String source = StringUtils.join(123256127, "-", 371, "-", "yang"); //25325-666-yang
        String str1 = StringUtils.join("0","gdfhdfhfdhj");
        System.out.println(source+"++++");
        System.out.println(str1+"++++");


    }
}
