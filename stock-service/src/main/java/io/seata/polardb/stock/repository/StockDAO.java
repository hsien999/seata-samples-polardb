package io.seata.polardb.stock.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.seata.polardb.stock.model.StockDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 库存 dao
 *
 * @author hsien999
 */
@Mapper
@Repository
public interface StockDAO extends BaseMapper<StockDO> {
}
