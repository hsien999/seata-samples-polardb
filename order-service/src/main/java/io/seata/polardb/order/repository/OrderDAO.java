package io.seata.polardb.order.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.seata.polardb.order.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 订单 dao
 *
 * @author hsien999
 */
@Mapper
@Repository
public interface OrderDAO extends BaseMapper<Order> {
}
