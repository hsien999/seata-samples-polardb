package io.seata.polardb.stock.controller;


import io.seata.polardb.common.ServiceException;
import io.seata.polardb.common.ServiceResultVO;
import io.seata.polardb.stock.service.StockService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 库存 controller
 *
 * @author hsien999
 */
@RestController
@RequestMapping("stock")
public class StockController {
    @Resource
    private StockService stockService;

    /**
     * 减库存
     */
    @RequestMapping(path = "/deduct")
    @ResponseBody()
    public ServiceResultVO deduct(String commodityCode, Integer count) {
        try {
            stockService.deduct(commodityCode, count);
            return ServiceResultVO.success("库存扣除成功");
        } catch (ServiceException e) {
            e.printStackTrace();
            return ServiceResultVO.failed("库存扣除失败: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResultVO.failed("库存扣除失败");
        }
    }
}
