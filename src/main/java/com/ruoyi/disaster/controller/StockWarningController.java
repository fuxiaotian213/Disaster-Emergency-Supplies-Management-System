package com.ruoyi.disaster.controller;

import com.ruoyi.disaster.domain.StockWarning;
import com.ruoyi.disaster.service.StockWarningService;
import com.ruoyi.disaster.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.disaster.util.Anonymous;
import java.util.List;

/**
 * 库存预警Controller
 */
@RestController
@RequestMapping("/stockWarning")
public class StockWarningController {

    @Autowired
    private StockWarningService stockWarningService;

    // 按状态查询预警
    @Anonymous
    @RequestMapping(value = "/getByStatus", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getByStatus(Integer warningStatus) {
        List<StockWarning> list = stockWarningService.getStockWarningByStatus(warningStatus);
        return AjaxResult.success(list);
    }

    // 新增预警
    @Anonymous
    @RequestMapping(value = "/addStockWarning", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult addStockWarning(@RequestBody StockWarning stockWarning) {
        stockWarningService.addStockWarning(stockWarning);
        return AjaxResult.success("库存预警新增成功");
    }

    // 标记为已处置
    @Anonymous
    @RequestMapping(value = "/markAsHandled", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult markAsHandled(Long id) {
        stockWarningService.markAsHandled(id);
        return AjaxResult.success("预警标记为已处置");
    }

    // 删除预警
    @Anonymous
    @RequestMapping(value = "/deleteStockWarning", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    public AjaxResult deleteStockWarning(Long id) {
        stockWarningService.deleteStockWarningById(id);
        return AjaxResult.success("预警删除成功");
    }
}