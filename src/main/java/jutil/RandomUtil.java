package jutil;


import java.util.Random;

/**
 * Created by lixuhui on 2018/2/9.
 */
public class RandomUtil {

    private static final char[] CHAR_ARRAY = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
    };

    private static final int RANDOM_RANGE = CHAR_ARRAY.length;

    private static final Random RANDOM = new Random();

    public static String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHAR_ARRAY[RANDOM.nextInt(RANDOM_RANGE)]);
        }
        return sb.toString();
    }


}
