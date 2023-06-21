package io.seata.polardb.order.feign;

import io.seata.polardb.common.ServiceResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 库存服务 client
 *
 * @author hsien999
 */
@FeignClient(name = "stock-service")
public interface StockFeignClient {
    @GetMapping("/stock/deduct")
    ServiceResultVO deduct(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") Integer count);
}
