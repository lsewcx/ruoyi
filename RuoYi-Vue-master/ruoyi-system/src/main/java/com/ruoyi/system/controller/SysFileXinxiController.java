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
import com.ruoyi.system.domain.SysFileXinxi;
import com.ruoyi.system.service.ISysFileXinxiService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文件详细信息Controller
 * 
 * @author lse
 * @date 2023-04-14
 */
@RestController
@RequestMapping("/system/xinxi")
public class SysFileXinxiController extends BaseController
{
    @Autowired
    private ISysFileXinxiService sysFileXinxiService;

    /**
     * 查询文件详细信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:xinxi:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysFileXinxi sysFileXinxi)
    {
        startPage();
        List<SysFileXinxi> list = sysFileXinxiService.selectSysFileXinxiList(sysFileXinxi);
        return getDataTable(list);
    }

    /**
     * 导出文件详细信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:xinxi:export')")
    @Log(title = "文件详细信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysFileXinxi sysFileXinxi)
    {
        List<SysFileXinxi> list = sysFileXinxiService.selectSysFileXinxiList(sysFileXinxi);
        ExcelUtil<SysFileXinxi> util = new ExcelUtil<SysFileXinxi>(SysFileXinxi.class);
        util.exportExcel(response, list, "文件详细信息数据");
    }

    /**
     * 获取文件详细信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:xinxi:query')")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long fileId)
    {
        return success(sysFileXinxiService.selectSysFileXinxiByFileId(fileId));
    }

    /**
     * 新增文件详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:xinxi:add')")
    @Log(title = "文件详细信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysFileXinxi sysFileXinxi)
    {
        return toAjax(sysFileXinxiService.insertSysFileXinxi(sysFileXinxi));
    }

    /**
     * 修改文件详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:xinxi:edit')")
    @Log(title = "文件详细信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysFileXinxi sysFileXinxi)
    {
        return toAjax(sysFileXinxiService.updateSysFileXinxi(sysFileXinxi));
    }

    /**
     * 删除文件详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:xinxi:remove')")
    @Log(title = "文件详细信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds)
    {
        return toAjax(sysFileXinxiService.deleteSysFileXinxiByFileIds(fileIds));
    }
}
