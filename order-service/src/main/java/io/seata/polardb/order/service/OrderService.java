package io.seata.polardb.order.service;

import io.seata.polardb.common.ServiceException;
import io.seata.polardb.common.ServiceResultCode;
import io.seata.polardb.common.ServiceResultVO;
import io.seata.polardb.order.feign.StockFeignClient;
import io.seata.polardb.order.model.Order;
import io.seata.polardb.order.repository.OrderDAO;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 订单 service
 *
 * @author hsien999
 */
@Service
public class OrderService {
    private final static int TEST_COMMODITY_UNIT = 5;

    @Resource
    private OrderDAO orderDao;
    @Resource
    private StockFeignClient stockFeignClient;

    /**
     * 下单: 创建订单, 减库存
     * 涉及订单&库存两个服务
     */
    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    public void placeOrder(String userId, String commodityCode, Integer count) {
        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(TEST_COMMODITY_UNIT));

        Order order = Order.builder().userId(userId).commodityCode(commodityCode)
                .count(count).money(orderMoney).build();
        orderDao.insert(order);

        ServiceResultVO result = stockFeignClient.deduct(commodityCode, count);
        if (result.getCode() == ServiceResultCode.FAILED) {
            throw new ServiceException(result.getMsg());
        }
    }

    /**
     * 取消订单: 删除订单, 加库存
     * 涉及订单&库存两个服务
     */
    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    public void cancelOrder(Integer id) {
        Order order = orderDao.selectById(id);
        if (order == null) {
            throw new ServiceException("订单不存在, id=" + id);
        }
        orderDao.deleteById(id);

        ServiceResultVO result = stockFeignClient.deduct(order.getCommodityCode(), -order.getCount());
        if (result.getCode() == ServiceResultCode.FAILED) {
            throw new ServiceException(result.getMsg());
        }
    }
}
