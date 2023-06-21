package io.seata.polardb.stock.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.seata.polardb.common.ServiceException;
import io.seata.polardb.stock.model.StockDO;
import io.seata.polardb.stock.repository.StockDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 库存 service
 *
 * @author hsien999
 */
@Service
public class StockService {

    @Resource
    private StockDAO stockDAO;

    /**
     * 减库存
     */
    @Transactional(rollbackFor = Exception.class)
    public void deduct(String commodityCode, int count) {
        QueryWrapper<StockDO> wrapper = new QueryWrapper<>();
        StockDO stockDO = StockDO.builder().commodityCode(commodityCode).build();
        wrapper.setEntity(stockDO);
        stockDO = stockDAO.selectOne(wrapper);
        if (stockDO == null) {
            throw new ServiceException("库存不存在, commodityCode=" + commodityCode);
        }
        if (count > 0 && stockDO.getCount() < count) {
            throw new ServiceException("库存不足, commodityCode=" + commodityCode + ", stock=" + stockDO.getCount());
        }
        stockDO.setCount(stockDO.getCount() - count);

        int affectRows = stockDAO.updateById(stockDO);
        if (affectRows == 0) {
            throw new ServiceException("库存更新失败, commodityCode=" + commodityCode);
        }
    }
}
