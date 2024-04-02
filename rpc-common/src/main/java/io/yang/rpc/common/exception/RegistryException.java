package io.yang.rpc.common.exception;

/**
 * @author zhangyang03
 * @Description
 * @create 2024-04-02 11:32
 */
public class RegistryException extends RuntimeException{
    private static final long serialVersionUID = 3231243777851221374L;

    public RegistryException() {
    }

    public RegistryException(String message) {
        super(message);
    }

    public RegistryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistryException(Throwable cause) {
        super(cause);
    }

    public RegistryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
