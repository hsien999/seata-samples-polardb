package io.seata.polardb.order.controller;

import io.seata.polardb.common.ServiceException;
import io.seata.polardb.common.ServiceResultVO;
import io.seata.polardb.order.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单 controller
 *
 * @author hsien999
 */
@RestController
@RequestMapping("order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @RequestMapping("/placeOrder")
    public ServiceResultVO placeOrder(String userId, String commodityCode, Integer count) {
        try {
            orderService.placeOrder(userId, commodityCode, count);
            return ServiceResultVO.success("订单创建成功");
        } catch (ServiceException e) {
            e.printStackTrace();
            return ServiceResultVO.failed("订单创建失败: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResultVO.success("订单创建失败");
        }
    }

    @RequestMapping("/cancelOrder")
    public ServiceResultVO cancelOrder(Integer id) {
        try {
            orderService.cancelOrder(id);
            return ServiceResultVO.success("订单取消成功");
        } catch (ServiceException e) {
            e.printStackTrace();
            return ServiceResultVO.failed("订单取消失败: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceResultVO.success("订单取消失败");
        }
    }
}
