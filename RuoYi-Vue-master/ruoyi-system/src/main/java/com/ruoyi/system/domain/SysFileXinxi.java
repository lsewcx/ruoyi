package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文件详细信息对象 sys_file_xinxi
 * 
 * @author lse
 * @date 2023-04-14
 */
public class SysFileXinxi extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文件id */
    private Long fileId;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** js文件数 */
    @Excel(name = "js文件数")
    public Long fileJs;

    /** java文件数量 */
    @Excel(name = "java文件数量")
    public Long fileJava;

    /** xml文件数量 */
    @Excel(name = "xml文件数量")
    public Long fileXml;

    /** vue文件数量 */
    @Excel(name = "vue文件数量")
    public Long fileVue;

    /** 无效文件数量 */
    @Excel(name = "无效文件数量")
    public Long fileWuxiao;

    /** 文件路径 */
    @Excel(name = "文件路径")
    public String filePath;

    public void setFileId(Long fileId) 
    {
        this.fileId = fileId;
    }

    public Long getFileId() 
    {
        return fileId;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFileJs(Long fileJs) 
    {
        this.fileJs = fileJs;
    }

    public Long getFileJs() 
    {
        return fileJs;
    }
    public void setFileJava(Long fileJava) 
    {
        this.fileJava = fileJava;
    }

    public Long getFileJava() 
    {
        return fileJava;
    }
    public void setFileXml(Long fileXml) 
    {
        this.fileXml = fileXml;
    }

    public Long getFileXml() 
    {
        return fileXml;
    }
    public void setFileVue(Long fileVue) 
    {
        this.fileVue = fileVue;
    }

    public Long getFileVue() 
    {
        return fileVue;
    }
    public void setFileWuxiao(Long fileWuxiao) 
    {
        this.fileWuxiao = fileWuxiao;
    }

    public Long getFileWuxiao() 
    {
        return fileWuxiao;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fileId", getFileId())
            .append("fileName", getFileName())
            .append("fileJs", getFileJs())
            .append("fileJava", getFileJava())
            .append("fileXml", getFileXml())
            .append("fileVue", getFileVue())
            .append("fileWuxiao", getFileWuxiao())
            .append("filePath", getFilePath())
            .toString();
    }
}
