package yhy.mbg;

/**
 * @Author: yhy
 * @Date: 2019/8/21 10:49
 * @Version 1.0
 */
public class MyException extends Exception {
    private String erroCode;
    private String proviceCOde;

    public String getErroCode() {
        return erroCode;
    }

    public MyException() {
    }

    public MyException(String erroCode,String meassge) {
        super(meassge);
        this.erroCode = erroCode;
    }

    public MyException(String erroCode,String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(Throwable cause) {
        super(cause);
    }

    public MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getProviceCOde() {
        return proviceCOde;
    }

}
