package io.seata.polardb.common;

/**
 * 服务异常
 *
 * @author hsien
 **/
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
