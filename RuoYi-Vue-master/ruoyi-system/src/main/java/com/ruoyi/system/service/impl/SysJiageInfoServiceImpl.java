package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysJiageInfoMapper;
import com.ruoyi.system.domain.SysJiageInfo;
import com.ruoyi.system.service.ISysJiageInfoService;


/**
 * 最终价格计算Service业务层处理
 * 
 * @author lse
 * @date 2023-04-04
 */
@Service
public class SysJiageInfoServiceImpl implements ISysJiageInfoService 
{
    @Autowired
    private SysJiageInfoMapper sysJiageInfoMapper;

    /**
     * 查询最终价格计算
     * 
     * @param fileId 最终价格计算主键
     * @return 最终价格计算
     */
    @Override
    public SysJiageInfo selectSysJiageInfoByFileId(Long fileId)
    {
        return sysJiageInfoMapper.selectSysJiageInfoByFileId(fileId);
    }

    /**
     * 查询最终价格计算列表
     * 
     * @param sysJiageInfo 最终价格计算
     * @return 最终价格计算
     */
    @Override
    public List<SysJiageInfo> selectSysJiageInfoList(SysJiageInfo sysJiageInfo)
    {
        return sysJiageInfoMapper.selectSysJiageInfoList(sysJiageInfo);
    }

    /**
     * 新增最终价格计算
     * 
     * @param sysJiageInfo 最终价格计算
     * @return 结果
     */
    @Override
    public int insertSysJiageInfo(SysJiageInfo sysJiageInfo)
    {
        sysJiageInfo.fileZuozhongjiage=sysJiageInfo.fileJiage;
        return sysJiageInfoMapper.insertSysJiageInfo(sysJiageInfo);
    }

    /**
     * 修改最终价格计算
     * 
     * @param sysJiageInfo 最终价格计算
     * @return 结果
     */
    @Override
    public int updateSysJiageInfo(SysJiageInfo sysJiageInfo)
    {
        return sysJiageInfoMapper.updateSysJiageInfo(sysJiageInfo);
    }

    /**
     * 批量删除最终价格计算
     * 
     * @param fileIds 需要删除的最终价格计算主键
     * @return 结果
     */
    @Override
    public int deleteSysJiageInfoByFileIds(Long[] fileIds)
    {
        return sysJiageInfoMapper.deleteSysJiageInfoByFileIds(fileIds);
    }

    /**
     * 删除最终价格计算信息
     * 
     * @param fileId 最终价格计算主键
     * @return 结果
     */
    @Override
    public int deleteSysJiageInfoByFileId(Long fileId)
    {
        return sysJiageInfoMapper.deleteSysJiageInfoByFileId(fileId);
    }
}
