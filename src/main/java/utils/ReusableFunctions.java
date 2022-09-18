package utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class ReusableFunctions {

    public static String getTimeStamp(){
        long unixTime = System.currentTimeMillis() / 1000L;
        return String.valueOf(unixTime);
    }

    public static int randomNumberGivenLength(int length){
        int m = (int) Math.pow(10, length - 1);
        return m + new Random().nextInt(9 * m);
    }

    public static String randomStringGivenLength(int length){
       String generatedString = RandomStringUtils.randomAlphabetic(length);
       return generatedString;
    }

}
