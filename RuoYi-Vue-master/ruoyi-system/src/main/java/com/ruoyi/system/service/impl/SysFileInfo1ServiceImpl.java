package com.ruoyi.system.service.impl;

import java.io.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysFileInfo1Mapper;
import com.ruoyi.system.domain.SysFileInfo1;
import com.ruoyi.system.service.ISysFileInfo1Service;
import com.ruoyi.system.controller.Zip;
import com.ruoyi.system.controller.gonneng;

/**
 * 文件信息1Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-28
 */
@Service
public class SysFileInfo1ServiceImpl implements ISysFileInfo1Service 
{
    String a,b;
    @Autowired
    private SysFileInfo1Mapper sysFileInfo1Mapper;

    /**
     * 查询文件信息1
     * 
     * @param fileId 文件信息1主键
     * @return 文件信息1
     */
    @Override
    public SysFileInfo1 selectSysFileInfo1ByFileId(Long fileId)
    {
        return sysFileInfo1Mapper.selectSysFileInfo1ByFileId(fileId);
    }

    /**
     * 查询文件信息1列表
     * 
     * @param sysFileInfo1 文件信息1
     * @return 文件信息1
     */
    @Override
    public List<SysFileInfo1> selectSysFileInfo1List(SysFileInfo1 sysFileInfo1) throws IOException {
        return sysFileInfo1Mapper.selectSysFileInfo1List(sysFileInfo1);
    }

    /**
     * 新增文件信息1
     * 
     * @param sysFileInfo1 文件信息1
     * @return 结果
     */
    @Override
    public int insertSysFileInfo1(SysFileInfo1 sysFileInfo1) throws IOException {
        a=Zip.getstr(sysFileInfo1.filePath);
        b=Zip.getstr1(sysFileInfo1.filePath);
        sysFileInfo1.fileRows= String.valueOf(Zip.getrow(sysFileInfo1.filePath));
        sysFileInfo1.fileXinagsidu= gonneng.getSimilarity(a,b);
        return sysFileInfo1Mapper.insertSysFileInfo1(sysFileInfo1);
    }

    /**
     * 修改文件信息1
     * 
     * @param sysFileInfo1 文件信息1
     * @return 结果
     */
    @Override
    public int updateSysFileInfo1(SysFileInfo1 sysFileInfo1)
    {
        return sysFileInfo1Mapper.updateSysFileInfo1(sysFileInfo1);
    }

    /**
     * 批量删除文件信息1
     * 
     * @param fileIds 需要删除的文件信息1主键
     * @return 结果
     */
    @Override
    public int deleteSysFileInfo1ByFileIds(Long[] fileIds)
    {
        return sysFileInfo1Mapper.deleteSysFileInfo1ByFileIds(fileIds);
    }

    /**
     * 删除文件信息1信息
     * 
     * @param fileId 文件信息1主键
     * @return 结果
     */
    @Override
    public int deleteSysFileInfo1ByFileId(Long fileId)
    {
        return sysFileInfo1Mapper.deleteSysFileInfo1ByFileId(fileId);
    }

}
