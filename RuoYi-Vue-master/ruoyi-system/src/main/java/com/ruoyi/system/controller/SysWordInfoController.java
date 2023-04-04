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
import com.ruoyi.system.domain.SysWordInfo;
import com.ruoyi.system.service.ISysWordInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * word上传Controller
 * 
 * @author lse
 * @date 2023-04-04
 */
@RestController
@RequestMapping("/system/word")
public class SysWordInfoController extends BaseController
{
    @Autowired
    private ISysWordInfoService sysWordInfoService;

    /**
     * 查询word上传列表
     */
    @PreAuthorize("@ss.hasPermi('system:word:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysWordInfo sysWordInfo)
    {
        startPage();
        List<SysWordInfo> list = sysWordInfoService.selectSysWordInfoList(sysWordInfo);
        return getDataTable(list);
    }

    /**
     * 导出word上传列表
     */
    @PreAuthorize("@ss.hasPermi('system:word:export')")
    @Log(title = "word上传", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysWordInfo sysWordInfo)
    {
        List<SysWordInfo> list = sysWordInfoService.selectSysWordInfoList(sysWordInfo);
        ExcelUtil<SysWordInfo> util = new ExcelUtil<SysWordInfo>(SysWordInfo.class);
        util.exportExcel(response, list, "word上传数据");
    }

    /**
     * 获取word上传详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:word:query')")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long fileId)
    {
        return success(sysWordInfoService.selectSysWordInfoByFileId(fileId));
    }

    /**
     * 新增word上传
     */
    @PreAuthorize("@ss.hasPermi('system:word:add')")
    @Log(title = "word上传", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysWordInfo sysWordInfo)
    {
        return toAjax(sysWordInfoService.insertSysWordInfo(sysWordInfo));
    }

    /**
     * 修改word上传
     */
    @PreAuthorize("@ss.hasPermi('system:word:edit')")
    @Log(title = "word上传", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysWordInfo sysWordInfo)
    {
        return toAjax(sysWordInfoService.updateSysWordInfo(sysWordInfo));
    }

    /**
     * 删除word上传
     */
    @PreAuthorize("@ss.hasPermi('system:word:remove')")
    @Log(title = "word上传", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds)
    {
        return toAjax(sysWordInfoService.deleteSysWordInfoByFileIds(fileIds));
    }
}
