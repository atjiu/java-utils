package com.github.javautils.regular;

/**
 * Created by tomoya on 16/8/31.
 */
public interface Regular {

    /**
     * 邮件正则
     */
    String EMAIL = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * html标签正则
     */
    String HTML = "<[.[^<]]*>";

    /**
     * 查找一段文本里以 @ 开头的正则
     */
    String AT = "@([a-zA-Z_0-9-/b]+)\\s";

}
