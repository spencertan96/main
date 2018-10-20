package seedu.address.commons.util;

import java.util.Random;

/**
 * Helper function to create UniqueId
 */
public class UniqueIdUtil {

    /**
     *
     * @param seed the random seed
     * @return 100 character long randomly generated string (may include null character)
     */
    public static String createUniqueId(long seed) {
        byte[] byteArray = new byte[100];
        long millis = System.currentTimeMillis();
        Random generator = new Random(millis + seed);
        for (int i=0; i<byteArray.length; i++) {
            byteArray[i] = (byte)(generator.nextInt(94) + 33);
        }
        return new String(byteArray);
    }

    public static String createUniqueId() {
        return createUniqueId(0);
    }

}
