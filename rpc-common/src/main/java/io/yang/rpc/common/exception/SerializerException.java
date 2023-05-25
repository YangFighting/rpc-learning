package io.yang.rpc.common.exception;

/**
 * @author zhangyang
 * @description
 * @date 2023/05/18 23:26
 **/
public class SerializerException extends RuntimeException{
    private static final long serialVersionUID = -2373891573262432381L;

    /**
     * Instantiates a new Serializer exception.
     *
     * @param e the e
     */
    public SerializerException(final Throwable e) {
        super(e);
    }


    /**
     * Instantiates a new Serializer exception.
     *
     * @param message the message
     */
    public SerializerException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new Serializer exception.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public SerializerException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
