package io.seata.polardb.stock.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * 库存表
 *
 * @author hsien999
 */
@Data
@Builder
@TableName("stock_tbl")
public class StockDO {
    private Long id;
    private String commodityCode;
    private Long count;
}
