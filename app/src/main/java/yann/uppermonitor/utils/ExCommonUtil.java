package yann.uppermonitor.utils;

import android.text.TextUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by yayun.xia on 2018/1/27.
 */

public class ExCommonUtil {
    private ExCommonUtil() {
    }

    private static class ExCommonUtilHolder {
        private static final ExCommonUtil ecu = new ExCommonUtil();
    }

    public static final ExCommonUtil getInstance() {
        return ExCommonUtilHolder.ecu;
    }

    public static final boolean isEmpty(String input) {
        return TextUtils.isEmpty(input);
    }

    public static final boolean isEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }

    public static final boolean isEmpty(Map<?, ?> map) {
        return map == null || map.size() == 0;
    }

    public static final boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    public static final boolean isEmpty(float[] array) {
        return array == null || array.length == 0;
    }

    public static final boolean isEmpty(long[] array) {
        return array == null || array.length == 0;
    }

    public static final boolean isEmpty(double[] array) {
        return array == null || array.length == 0;
    }

    public static final boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static final boolean isEmpty(Object obj) {
        return obj == null;
    }

    public static final boolean isEmpty(long obj) {
        return obj == 0;
    }

    public static final String dealEmpty(String str) {
        return isEmpty(str) ? "" : str;
    }

    /**
     * UTF8编码
     *
     * @param str
     * @return String
     * @method stringUTF8Encode
     * @author lightning
     */
    public static final String stringUTF8Encode(String str) {
        return stringEncode(str, "UTF-8");
    }

    /**
     * 自定义编码
     *
     * @param str
     * @param charset
     * @return String
     * @method stringEncode
     * @author lightning
     */
    public static final String stringEncode(String str, String charset) {
        if (!ExCommonUtil.isEmpty(str)) {
            try {
                return URLEncoder.encode(str, charset);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /**
     * UTF8解码
     *
     * @param str
     * @return String
     * @method stringUTF8Decode
     * @author lightning
     */
    public static final String stringUTF8Decode(String str) {
        return stringDecode(str, "UTF-8");
    }

    /**
     * 自定义解码
     *
     * @param str
     * @param charset
     * @return String
     * @method stringDecode
     * @author lightning
     */
    public static final String stringDecode(String str, String charset) {
        if (!ExCommonUtil.isEmpty(str)) {
            try {
                return URLDecoder.decode(str, charset);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /**
     * unicode解码
     *
     * @param str
     * @return String
     * @method unicodeDecode
     * @author lightning
     */
    public static final String unicodeDecode(String str) {
        StringBuffer sb = new StringBuffer();
        String[] arr = str.split("\\\\u");
        int len = arr.length;
        sb.append(arr[0]);
        for (int i = 1; i < len; i++) {
            String tmp = arr[i];
            char c = (char) Integer.parseInt(tmp.substring(0, 4), 16);
            sb.append(c);
            sb.append(tmp.substring(4));
        }
        return sb.toString();
    }

    /**
     * unicode编码
     *
     * @param strText
     * @return String
     * @method unicodeEncode
     * @author lightning
     */
    public static final String unicodeEncode(String strText) {
        char c;
        String strRet = "";
        int intAsc;
        String strHex;
        for (int i = 0; i < strText.length(); i++) {
            c = strText.charAt(i);
            intAsc = c;
            if (intAsc > 128) {
                strHex = Integer.toHexString(intAsc);
                strRet += "\\u" + strHex;
            } else {
                strRet = strRet + c;
            }
        }
        return strRet;
    }
}
