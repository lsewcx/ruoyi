package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysWordInfo;

/**
 * word上传Service接口
 * 
 * @author lse
 * @date 2023-04-03
 */
public interface ISysWordInfoService 
{
    /**
     * 查询word上传
     * 
     * @param fileId word上传主键
     * @return word上传
     */
    public SysWordInfo selectSysWordInfoByFileId(Long fileId);

    /**
     * 查询word上传列表
     * 
     * @param sysWordInfo word上传
     * @return word上传集合
     */
    public List<SysWordInfo> selectSysWordInfoList(SysWordInfo sysWordInfo);

    /**
     * 新增word上传
     * 
     * @param sysWordInfo word上传
     * @return 结果
     */
    public int insertSysWordInfo(SysWordInfo sysWordInfo);

    /**
     * 修改word上传
     * 
     * @param sysWordInfo word上传
     * @return 结果
     */
    public int updateSysWordInfo(SysWordInfo sysWordInfo);

    /**
     * 批量删除word上传
     * 
     * @param fileIds 需要删除的word上传主键集合
     * @return 结果
     */
    public int deleteSysWordInfoByFileIds(Long[] fileIds);

    /**
     * 删除word上传信息
     * 
     * @param fileId word上传主键
     * @return 结果
     */
    public int deleteSysWordInfoByFileId(Long fileId);
}
