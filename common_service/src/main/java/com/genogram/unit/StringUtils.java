package com.genogram.unit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils
{
    /**
     * 字符串为空
     * 
     * @param param
     * @return
     */
    public static boolean isEmpty(String param)
    {
        if (param == null || "".equals(param.trim()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

	/**
     * 字符串不为空
     * 
     * @param param
     * @return
     */
    public static boolean isNotEmpty(String param)
    {
        if (isEmpty(param))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 提取字符串中得数字
     * 
     * @param param
     * @return
     */
    public static String getNumber(String param)
    {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(param);
        return m.replaceAll("").trim();
    }

}