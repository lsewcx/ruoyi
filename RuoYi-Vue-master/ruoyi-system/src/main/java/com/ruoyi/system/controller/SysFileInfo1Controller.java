package com.ruoyi.system.controller;

import java.io.IOException;
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
import com.ruoyi.system.domain.SysFileInfo1;
import com.ruoyi.system.service.ISysFileInfo1Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文件信息1Controller
 * 
 * @author ruoyi
 * @date 2023-03-28
 */
@RestController
@RequestMapping("/system/info1")
public class SysFileInfo1Controller extends BaseController
{
    @Autowired
    private ISysFileInfo1Service sysFileInfo1Service;

    /**
     * 查询文件信息1列表
     */
    @PreAuthorize("@ss.hasPermi('system:info1:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysFileInfo1 sysFileInfo1) throws IOException {
        startPage();
        List<SysFileInfo1> list = sysFileInfo1Service.selectSysFileInfo1List(sysFileInfo1);
        return getDataTable(list);
    }

    /**
     * 导出文件信息1列表
     */
    @PreAuthorize("@ss.hasPermi('system:info1:export')")
    @Log(title = "文件信息1", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysFileInfo1 sysFileInfo1) throws IOException {
        List<SysFileInfo1> list = sysFileInfo1Service.selectSysFileInfo1List(sysFileInfo1);
        ExcelUtil<SysFileInfo1> util = new ExcelUtil<SysFileInfo1>(SysFileInfo1.class);
        util.exportExcel(response, list, "文件信息1数据");
    }

    /**
     * 获取文件信息1详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info1:query')")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long fileId)
    {
        return success(sysFileInfo1Service.selectSysFileInfo1ByFileId(fileId));
    }

    /**
     * 新增文件信息1
     */
    @PreAuthorize("@ss.hasPermi('system:info1:add')")
    @Log(title = "文件信息1", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysFileInfo1 sysFileInfo1) throws IOException {
        return toAjax(sysFileInfo1Service.insertSysFileInfo1(sysFileInfo1));
    }

    /**
     * 修改文件信息1
     */
    @PreAuthorize("@ss.hasPermi('system:info1:edit')")
    @Log(title = "文件信息1", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysFileInfo1 sysFileInfo1)
    {
        return toAjax(sysFileInfo1Service.updateSysFileInfo1(sysFileInfo1));
    }

    /**
     * 删除文件信息1
     */
    @PreAuthorize("@ss.hasPermi('system:info1:remove')")
    @Log(title = "文件信息1", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds)
    {
        return toAjax(sysFileInfo1Service.deleteSysFileInfo1ByFileIds(fileIds));
    }
}
