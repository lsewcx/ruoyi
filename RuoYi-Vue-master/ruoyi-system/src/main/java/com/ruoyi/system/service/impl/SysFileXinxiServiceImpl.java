package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysFileXinxiMapper;
import com.ruoyi.system.domain.SysFileXinxi;
import com.ruoyi.system.service.ISysFileXinxiService;
import com.ruoyi.system.controller.webjianleix;

/**
 * 文件详细信息Service业务层处理
 * 
 * @author lse
 * @date 2023-04-14
 */
@Service
public class SysFileXinxiServiceImpl implements ISysFileXinxiService 
{
    @Autowired
    private SysFileXinxiMapper sysFileXinxiMapper;

    /**
     * 查询文件详细信息
     * 
     * @param fileId 文件详细信息主键
     * @return 文件详细信息
     */
    @Override
    public SysFileXinxi selectSysFileXinxiByFileId(Long fileId)
    {
        return sysFileXinxiMapper.selectSysFileXinxiByFileId(fileId);
    }

    /**
     * 查询文件详细信息列表
     * 
     * @param sysFileXinxi 文件详细信息
     * @return 文件详细信息
     */
    @Override
    public List<SysFileXinxi> selectSysFileXinxiList(SysFileXinxi sysFileXinxi)
    {
        return sysFileXinxiMapper.selectSysFileXinxiList(sysFileXinxi);
    }

    /**
     * 新增文件详细信息
     * 
     * @param sysFileXinxi 文件详细信息
     * @return 结果
     */
    @Override
    public int insertSysFileXinxi(SysFileXinxi sysFileXinxi)
    {
        sysFileXinxi.fileJava= Long.valueOf(webjianleix.readZipFileName1(sysFileXinxi.filePath).get(2));
        sysFileXinxi.fileJs= Long.valueOf(webjianleix.readZipFileName1(sysFileXinxi.filePath).get(0));
        sysFileXinxi.fileVue= Long.valueOf(webjianleix.readZipFileName1(sysFileXinxi.filePath).get(1));
        sysFileXinxi.fileXml= Long.valueOf(webjianleix.readZipFileName1(sysFileXinxi.filePath).get(4));
        sysFileXinxi.fileWuxiao= Long.valueOf(webjianleix.readZipFileName1(sysFileXinxi.filePath).get(3));
        return sysFileXinxiMapper.insertSysFileXinxi(sysFileXinxi);
    }

    /**
     * 修改文件详细信息
     * 
     * @param sysFileXinxi 文件详细信息
     * @return 结果
     */
    @Override
    public int updateSysFileXinxi(SysFileXinxi sysFileXinxi)
    {
        return sysFileXinxiMapper.updateSysFileXinxi(sysFileXinxi);
    }

    /**
     * 批量删除文件详细信息
     * 
     * @param fileIds 需要删除的文件详细信息主键
     * @return 结果
     */
    @Override
    public int deleteSysFileXinxiByFileIds(Long[] fileIds)
    {
        return sysFileXinxiMapper.deleteSysFileXinxiByFileIds(fileIds);
    }

    /**
     * 删除文件详细信息信息
     * 
     * @param fileId 文件详细信息主键
     * @return 结果
     */
    @Override
    public int deleteSysFileXinxiByFileId(Long fileId)
    {
        return sysFileXinxiMapper.deleteSysFileXinxiByFileId(fileId);
    }
}
