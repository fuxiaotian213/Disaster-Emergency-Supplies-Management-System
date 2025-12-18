package com.ruoyi.disaster.controller;

import com.ruoyi.disaster.domain.Material;
import com.ruoyi.disaster.service.MaterialService;
import com.ruoyi.disaster.util.Anonymous;
import com.ruoyi.disaster.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 救援物资Controller（提供RESTful接口）
 */
@RestController
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    // 1. 查询所有物资列表 - 支持GET和POST
    @Anonymous
    @RequestMapping(value = "/getMaterialList", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getMaterialList() {
        List<Material> list = materialService.getMaterialList();
        return AjaxResult.success(list);
    }

    // 2. 通过ID查询物资详情 - 支持GET和POST
    @Anonymous
    @RequestMapping(value = "/getMaterialById", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getMaterialById(Long id) {
        Material material = materialService.getMaterialById(id);
        return AjaxResult.success(material);
    }

    // 3. 批量查询物资（支持多ID同时查询） - 支持GET和POST
    @Anonymous
    @RequestMapping(value = "/getMaterialByIds", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getMaterialByIds(@RequestParam("ids") Set<Long> ids) {
        List<Material> materialList = materialService.getMaterialByIds(ids);
        return AjaxResult.success("批量查询成功", materialList);
    }

    // 4. 按条件查询物资 - 支持GET和POST
    @Anonymous
    @RequestMapping(value = "/getMaterialByCondition", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult getMaterialByCondition(Integer materialType, Integer priorityLevel, Long reservePointId) {
        List<Material> materialList = materialService.getMaterialByCondition(materialType, priorityLevel, reservePointId);
        return AjaxResult.success(materialList);
    }

    // 5. 添加物资 - 支持GET和POST
    @Anonymous
    @RequestMapping(value = "/addMaterial", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult addMaterial(@RequestBody Material material) {
        materialService.addMaterial(material);
        return AjaxResult.success("添加成功");
    }

    // 6. 修改物资信息 - 支持GET和POST
    @Anonymous
    @RequestMapping(value = "/modifyMaterial", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult modifyMaterial(@RequestBody Material material) {
        materialService.modifyMaterial(material);
        return AjaxResult.success("修改成功");
    }

    // 7. 更新物资库存 - 支持GET和POST
    @Anonymous
    @RequestMapping(value = "/updateStock", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult updateStock(Long id, Integer newStockNum) {
        materialService.updateMaterialStock(id, newStockNum);
        return AjaxResult.success("库存更新成功");
    }

    // 8. 删除物资（单条） - 支持GET、POST和DELETE
    @Anonymous
    @RequestMapping(value = "/deleteMaterial", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    public AjaxResult deleteMaterial(Long id) {
        materialService.deleteMaterialById(id);
        return AjaxResult.success("删除成功");
    }

    // 9. 批量删除物资 - 支持GET、POST和DELETE
    @Anonymous
    @RequestMapping(value = "/deleteMaterials", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    public AjaxResult deleteMaterials(@RequestBody Set<Long> ids) {
        int deleteCount = materialService.deleteMaterialByIds(ids);
        return AjaxResult.success("批量删除成功，共删除" + deleteCount + "条物资数据", deleteCount);
    }
}