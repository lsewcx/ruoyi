package com.ruoyi.system.service;

import java.io.IOException;
import java.util.List;
import com.ruoyi.system.domain.SysFileInfo1;

/**
 * 文件信息1Service接口
 * 
 * @author ruoyi
 * @date 2023-03-28
 */
public interface ISysFileInfo1Service 
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
    public List<SysFileInfo1> selectSysFileInfo1List(SysFileInfo1 sysFileInfo1) throws IOException;

    /**
     * 新增文件信息1
     * 
     * @param sysFileInfo1 文件信息1
     * @return 结果
     */
    public int insertSysFileInfo1(SysFileInfo1 sysFileInfo1) throws IOException;

    /**
     * 修改文件信息1
     * 
     * @param sysFileInfo1 文件信息1
     * @return 结果
     */
    public int updateSysFileInfo1(SysFileInfo1 sysFileInfo1);

    /**
     * 批量删除文件信息1
     * 
     * @param fileIds 需要删除的文件信息1主键集合
     * @return 结果
     */
    public int deleteSysFileInfo1ByFileIds(Long[] fileIds);

    /**
     * 删除文件信息1信息
     * 
     * @param fileId 文件信息1主键
     * @return 结果
     */
    public int deleteSysFileInfo1ByFileId(Long fileId);


}
