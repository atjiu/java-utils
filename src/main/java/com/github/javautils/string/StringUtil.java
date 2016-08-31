package com.github.javautils.string;

import com.github.javautils.encrypt.EncryptionUtil;
import com.github.javautils.regular.Regular;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    static final char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    static final char[] digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static final Random rand = new Random();

    /**
     * 首字母变小写
     */
    public static String firstCharToLowerCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] += ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    /**
     * 首字母变大写
     */
    public static String firstCharToUpperCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'a' && firstChar <= 'z') {
            char[] arr = str.toCharArray();
            arr[0] -= ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    /**
     * 字符串为 null 或者为  "" 时返回 true
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * 字符串不为 null 而且不为  "" 时返回 true
     */
    public static boolean notBlank(String str) {
        return str != null && !"".equals(str.trim());
    }

    public static boolean notBlank(String... strings) {
        if (strings == null)
            return false;
        for (String str : strings)
            if (str == null || "".equals(str.trim()))
                return false;
        return true;
    }

    public static boolean notNull(Object... paras) {
        if (paras == null)
            return false;
        for (Object obj : paras)
            if (obj == null)
                return false;
        return true;
    }

    public static String toCamelCase(String stringWithUnderline) {
        if (stringWithUnderline.indexOf('_') == -1)
            return stringWithUnderline;

        stringWithUnderline = stringWithUnderline.toLowerCase();
        char[] fromArray = stringWithUnderline.toCharArray();
        char[] toArray = new char[fromArray.length];
        int j = 0;
        for (int i = 0; i < fromArray.length; i++) {
            if (fromArray[i] == '_') {
                // 当前字符为下划线时，将指针后移一位，将紧随下划线后面一个字符转成大写并存放
                i++;
                if (i < fromArray.length)
                    toArray[j++] = Character.toUpperCase(fromArray[i]);
            } else {
                toArray[j++] = fromArray[i];
            }
        }
        return new String(toArray, 0, j);
    }

    public static String join(String[] stringArray) {
        StringBuilder sb = new StringBuilder();
        for (String s : stringArray)
            sb.append(s);
        return sb.toString();
    }

    public static String join(String[] stringArray, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stringArray.length; i++) {
            if (i > 0)
                sb.append(separator);
            sb.append(stringArray[i]);
        }
        return sb.toString();
    }

    /**
     * 验证email的合法性
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (isBlank(email)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile(Regular.EMAIL);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
    }

    /**
     * 后去一串去掉 - 的uuid字符串
     *
     * @return
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    /**
     * 随机指定长度的字符串
     *
     * @param length
     * @return
     */
    public static String randomString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int loop = 0; loop < length; ++loop) {
            sb.append(hexDigits[rand.nextInt(hexDigits.length)]);
        }
        return sb.toString();
    }

    /**
     * 随机指定长度的数字
     *
     * @param length
     * @return
     */
    public static String randomNumber(int length) {
        StringBuffer sb = new StringBuffer();
        for (int loop = 0; loop < length; ++loop) {
            sb.append(digits[rand.nextInt(digits.length)]);
        }
        return sb.toString();
    }

    /**
     * 将html过滤掉
     *
     * @param s
     * @return
     */
    public static String noHtml(String s) {
        if (isBlank(s)) return "";
        else return s.replaceAll(Regular.HTML, "");
    }

    /**
     * 转义html
     *
     * @param s
     * @return
     */
    public static String transHtml(String s) {
        if (isBlank(s)) return "";
        else return s.replace("<", "&lt;").replace(">", "&gt;");
    }

    /**
     * 查找一段文本里以 @ 开头的字符串
     *
     * @param str
     * @return
     */
    public static List<String> fetchUsers(String str) {
        List<String> ats = new ArrayList<String>();
        String pattern = Regular.AT;
        Pattern regex = Pattern.compile(pattern);
        Matcher regexMatcher = regex.matcher(str);
        while (regexMatcher.find()) {
            ats.add(regexMatcher.group(1));
        }
        return ats;
    }

    public static int str2int(String s) {
        if (s == null || s.equals("")) {
            return 0;
        } else {
            return Integer.parseInt(s);
        }
    }

    /**
     * json字符串转换成 Map
     * 依赖Gson包
     */
//    public static Map<String, Object> parseToMap(String jsonString) {
//        return new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {}.getType());
//    }

}