package edu.xihua.project.pms;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author intent <a>zzy.main@gmail.com</a>
 * @date 2021/5/30 10:49 上午
 * @since 1.0
 */
public class RandomStringUtilsTest {
    public static void main(String[] args) {
        System.out.println(RandomStringUtils.random(64));
//        System.out.println(RandomStringUtils.randomAscii(64));
        System.out.println(RandomStringUtils.random(64, 'a', 'z', true, true));
    }
}
