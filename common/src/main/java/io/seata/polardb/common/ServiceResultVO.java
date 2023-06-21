package io.seata.polardb.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 服务 VO
 *
 * @author hsien999
 **/
@Data
public class ServiceResultVO implements Serializable {
    private Integer code;
    private String msg;

    public static ServiceResultVO success(String msg) {
        ServiceResultVO vo = new ServiceResultVO();
        vo.setCode(ServiceResultCode.SUCCESS);
        vo.setMsg(msg);
        return vo;
    }

    public static ServiceResultVO failed(String msg) {
        ServiceResultVO vo = new ServiceResultVO();
        vo.setCode(ServiceResultCode.FAILED);
        vo.setMsg(msg);
        return vo;
    }
}
