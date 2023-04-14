package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysFileXinxi;

/**
 * 文件详细信息Service接口
 * 
 * @author lse
 * @date 2023-04-14
 */
public interface ISysFileXinxiService 
{
    /**
     * 查询文件详细信息
     * 
     * @param fileId 文件详细信息主键
     * @return 文件详细信息
     */
    public SysFileXinxi selectSysFileXinxiByFileId(Long fileId);

    /**
     * 查询文件详细信息列表
     * 
     * @param sysFileXinxi 文件详细信息
     * @return 文件详细信息集合
     */
    public List<SysFileXinxi> selectSysFileXinxiList(SysFileXinxi sysFileXinxi);

    /**
     * 新增文件详细信息
     * 
     * @param sysFileXinxi 文件详细信息
     * @return 结果
     */
    public int insertSysFileXinxi(SysFileXinxi sysFileXinxi);

    /**
     * 修改文件详细信息
     * 
     * @param sysFileXinxi 文件详细信息
     * @return 结果
     */
    public int updateSysFileXinxi(SysFileXinxi sysFileXinxi);

    /**
     * 批量删除文件详细信息
     * 
     * @param fileIds 需要删除的文件详细信息主键集合
     * @return 结果
     */
    public int deleteSysFileXinxiByFileIds(Long[] fileIds);

    /**
     * 删除文件详细信息信息
     * 
     * @param fileId 文件详细信息主键
     * @return 结果
     */
    public int deleteSysFileXinxiByFileId(Long fileId);
}
