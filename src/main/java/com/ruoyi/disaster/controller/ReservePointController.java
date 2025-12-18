package com.ruoyi.disaster.controller;

import com.ruoyi.disaster.domain.ReservePoint;
import com.ruoyi.disaster.service.ReservePointService;
import com.ruoyi.disaster.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.disaster.util.Anonymous;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 储备点Controller
 */
@RestController
@RequestMapping("/reservePoint")
public class ReservePointController {

    @Autowired
    private ReservePointService reservePointService;

    // 查询所有储备点
    @Anonymous
    @RequestMapping(value = "/getAllReservePoint", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getAllReservePoint() {
        List<ReservePoint> list = reservePointService.getAllReservePoint();
        return AjaxResult.success(list);
    }

    // 按省市查询储备点
    @Anonymous
    @RequestMapping(value = "/getByProvinceCity", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getByProvinceCity(String province, String city) {
        List<ReservePoint> list = reservePointService.getReservePointByProvinceCity(province, city);
        return AjaxResult.success(list);
    }

    // 新增储备点
    @Anonymous
    @RequestMapping(value = "/addReservePoint", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult addReservePoint(@RequestBody ReservePoint reservePoint) {
        reservePointService.addReservePoint(reservePoint);
        return AjaxResult.success("储备点新增成功");
    }

    // 修改储备点
    @Anonymous
    @RequestMapping(value = "/modifyReservePoint", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult modifyReservePoint(@RequestBody ReservePoint reservePoint) {
        reservePointService.modifyReservePoint(reservePoint);
        return AjaxResult.success("储备点修改成功");
    }
    @Anonymous
    @RequestMapping(value = "/getReservePointByIds", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getReservePointByIds(@RequestParam("ids") String ids) {
        // 解析前端传递的ids字符串（格式："1,2,3"）
        if (ids == null || ids.trim().isEmpty()) {
            return AjaxResult.error("储备点ID列表不能为空");
        }
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());
        // 调用Service查询
        List<ReservePoint> list = reservePointService.getReservePointByIds(idList);
        return AjaxResult.success(list);
    }

    // 删除储备点
    @Anonymous
    @RequestMapping(value = "/deleteReservePoint", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    public AjaxResult deleteReservePoint(Long id) {
        reservePointService.deleteReservePointById(id);
        return AjaxResult.success("储备点删除成功");
    }
}