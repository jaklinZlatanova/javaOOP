package bank.common;

public class StringUtils {

    private StringUtils(){

    }

    public static boolean nullOrEmpty(String str){
        return str == null || str.trim().isEmpty();
    }
}
