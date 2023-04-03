package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysWordInfoMapper;
import com.ruoyi.system.domain.SysWordInfo;
import com.ruoyi.system.service.ISysWordInfoService;
import com.ruoyi.system.controller.wordduqu;

/**
 * word上传Service业务层处理
 * 
 * @author lse
 * @date 2023-04-03
 */
@Service
public class SysWordInfoServiceImpl implements ISysWordInfoService 
{
    int num=0;
    @Autowired
    private SysWordInfoMapper sysWordInfoMapper;

    /**
     * 查询word上传
     * 
     * @param fileId word上传主键
     * @return word上传
     */
    @Override
    public SysWordInfo selectSysWordInfoByFileId(Long fileId)
    {
        return sysWordInfoMapper.selectSysWordInfoByFileId(fileId);
    }

    /**
     * 查询word上传列表
     * 
     * @param sysWordInfo word上传
     * @return word上传
     */
    @Override
    public List<SysWordInfo> selectSysWordInfoList(SysWordInfo sysWordInfo)
    {
        return sysWordInfoMapper.selectSysWordInfoList(sysWordInfo);
    }

    /**
     * 新增word上传
     * 
     * @param sysWordInfo word上传
     * @return 结果
     */
    @Override
    public int insertSysWordInfo(SysWordInfo sysWordInfo)
    {
        num=wordduqu.Word(sysWordInfo.fileWord);
        return sysWordInfoMapper.insertSysWordInfo(sysWordInfo);
    }

    /**
     * 修改word上传
     * 
     * @param sysWordInfo word上传
     * @return 结果
     */
    @Override
    public int updateSysWordInfo(SysWordInfo sysWordInfo)
    {
        return sysWordInfoMapper.updateSysWordInfo(sysWordInfo);
    }

    /**
     * 批量删除word上传
     * 
     * @param fileIds 需要删除的word上传主键
     * @return 结果
     */
    @Override
    public int deleteSysWordInfoByFileIds(Long[] fileIds)
    {
        return sysWordInfoMapper.deleteSysWordInfoByFileIds(fileIds);
    }

    /**
     * 删除word上传信息
     * 
     * @param fileId word上传主键
     * @return 结果
     */
    @Override
    public int deleteSysWordInfoByFileId(Long fileId)
    {
        return sysWordInfoMapper.deleteSysWordInfoByFileId(fileId);
    }
}
