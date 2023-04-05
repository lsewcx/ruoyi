package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysFileInfo1;

/**
 * 文件信息1Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-28
 */
public interface SysFileInfo1Mapper 
{
    /**
     * 查询文件信息1
     * 
     * @param fileId 文件信息1主键
     * @return 文件信息1
     */
    public SysFileInfo1 selectSysFileInfo1ByFileId(Long fileId);

    /**
     * 查询文件信息1列表
     * 
     * @param sysFileInfo1 文件信息1
     * @return 文件信息1集合
     */
    public List<SysFileInfo1> selectSysFileInfo1List(SysFileInfo1 sysFileInfo1);

    /**
     * 新增文件信息1
     * 
     * @param sysFileInfo1 文件信息1
     * @return 结果
     */
    public int insertSysFileInfo1(SysFileInfo1 sysFileInfo1);

    /**
     * 修改文件信息1
     * 
     * @param sysFileInfo1 文件信息1
     * @return 结果
     */
    public int updateSysFileInfo1(SysFileInfo1 sysFileInfo1);

    /**
     * 删除文件信息1
     * 
     * @param fileId 文件信息1主键
     * @return 结果
     */
    public int deleteSysFileInfo1ByFileId(Long fileId);

    /**
     * 批量删除文件信息1
     * 
     * @param fileIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysFileInfo1ByFileIds(Long[] fileIds);

}
