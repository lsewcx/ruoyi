package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysJiageInfo;
import com.ruoyi.system.service.ISysJiageInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 最终价格计算Controller
 * 
 * @author lse
 * @date 2023-04-04
 */
@RestController
@RequestMapping("/system/jiage")
public class SysJiageInfoController extends BaseController
{
    @Autowired
    private ISysJiageInfoService sysJiageInfoService;

    /**
     * 查询最终价格计算列表
     */
    @PreAuthorize("@ss.hasPermi('system:jiage:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysJiageInfo sysJiageInfo)
    {
        startPage();
        List<SysJiageInfo> list = sysJiageInfoService.selectSysJiageInfoList(sysJiageInfo);
        return getDataTable(list);
    }

    /**
     * 导出最终价格计算列表
     */
    @PreAuthorize("@ss.hasPermi('system:jiage:export')")
    @Log(title = "最终价格计算", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysJiageInfo sysJiageInfo)
    {
        List<SysJiageInfo> list = sysJiageInfoService.selectSysJiageInfoList(sysJiageInfo);
        ExcelUtil<SysJiageInfo> util = new ExcelUtil<SysJiageInfo>(SysJiageInfo.class);
        util.exportExcel(response, list, "最终价格计算数据");
    }

    /**
     * 获取最终价格计算详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:jiage:query')")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long fileId)
    {
        return success(sysJiageInfoService.selectSysJiageInfoByFileId(fileId));
    }

    /**
     * 新增最终价格计算
     */
    @PreAuthorize("@ss.hasPermi('system:jiage:add')")
    @Log(title = "最终价格计算", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysJiageInfo sysJiageInfo)
    {
        return toAjax(sysJiageInfoService.insertSysJiageInfo(sysJiageInfo));
    }

    /**
     * 修改最终价格计算
     */
    @PreAuthorize("@ss.hasPermi('system:jiage:edit')")
    @Log(title = "最终价格计算", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysJiageInfo sysJiageInfo)
    {
        return toAjax(sysJiageInfoService.updateSysJiageInfo(sysJiageInfo));
    }

    /**
     * 删除最终价格计算
     */
    @PreAuthorize("@ss.hasPermi('system:jiage:remove')")
    @Log(title = "最终价格计算", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds)
    {
        return toAjax(sysJiageInfoService.deleteSysJiageInfoByFileIds(fileIds));
    }
}
