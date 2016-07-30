package jmsdevelopment.smolovcal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jesper on 03/07/2016.
 */

public class Util {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private static Matcher matcher;

    private static Util util;

    public static Util get() {
        if (util == null) {
            util = new Util();
        }
        return util;
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String password) {
        return password.length() >= 6;
    }


}
