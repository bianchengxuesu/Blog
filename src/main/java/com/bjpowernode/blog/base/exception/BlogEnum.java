package com.bjpowernode.blog.base.exception;

/**
 * 注意： 枚举类中定义枚举时，使用逗号隔开。
 */
public enum BlogEnum {

    USER_LOGIN_CODE("001-001","验证码输入错误"),
    USER_LOGIN_ACCOUNT("001-002","用户名或者密码错误"),
    USER_VERIFY_PASS("001-003","输入的旧密码错误"),
    USER_UPDATE("001-004","更新失败"),
    ARTICLE_ISOPEN("002-001","修改文章是否公开失败"),
    ARTICLE_PUNISH("002-002","发布文章失败"),
    ARTICLE_UPDATE("002-003","修改文章失败"),
    ARTICLE_DELETE("002-004","删除文章失败");

   private String message; //具体错误信息
    private String typeCode; //属于哪个模块儿下的操作失败code

    BlogEnum(String typeCode, String message) {
        this.message = message;
        this.typeCode = typeCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
