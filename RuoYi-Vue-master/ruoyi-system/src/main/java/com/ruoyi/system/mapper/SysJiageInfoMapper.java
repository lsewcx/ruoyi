package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysJiageInfo;

/**
 * 最终价格计算Mapper接口
 * 
 * @author lse
 * @date 2023-04-04
 */
public interface SysJiageInfoMapper 
{
    /**
     * 查询最终价格计算
     * 
     * @param fileId 最终价格计算主键
     * @return 最终价格计算
     */
    public SysJiageInfo selectSysJiageInfoByFileId(Long fileId);

    /**
     * 查询最终价格计算列表
     * 
     * @param sysJiageInfo 最终价格计算
     * @return 最终价格计算集合
     */
    public List<SysJiageInfo> selectSysJiageInfoList(SysJiageInfo sysJiageInfo);

    /**
     * 新增最终价格计算
     * 
     * @param sysJiageInfo 最终价格计算
     * @return 结果
     */
    public int insertSysJiageInfo(SysJiageInfo sysJiageInfo);

    /**
     * 修改最终价格计算
     * 
     * @param sysJiageInfo 最终价格计算
     * @return 结果
     */
    public int updateSysJiageInfo(SysJiageInfo sysJiageInfo);

    /**
     * 删除最终价格计算
     * 
     * @param fileId 最终价格计算主键
     * @return 结果
     */
    public int deleteSysJiageInfoByFileId(Long fileId);

    /**
     * 批量删除最终价格计算
     * 
     * @param fileIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysJiageInfoByFileIds(Long[] fileIds);
}
