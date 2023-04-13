package com.ruoyi.system.domain;

import com.ruoyi.system.controller.Zip;
import com.ruoyi.system.controller.gonneng;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.io.IOException;

/**
 * 文件信息1对象 sys_file_info1
 * 
 * @author ruoyi
 * @date 2023-03-28
 */
public class SysFileInfo1 extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文件id */
    private Long fileId;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件路径 */
    @Excel(name = "文件路径")
    public String filePath;

    /** 开源项目版本 */
    @Excel(name = "开源项目版本")
    private String fileVersion;

    /** 托管地址 */
    @Excel(name = "托管地址")
    private String fileGithub;

    /** 代码行数 */
    @Excel(name = "代码行数")
    public String fileRows;

    /** 代码相似度 */
    @Excel(name = "代码相似度")
    public String fileXinagsidu;

    public String filenewpath;



    public void setFilenewpath(String filenewpath){this.filenewpath=filenewpath;}
    public String getFilenewpath(){
        return filenewpath;
    }

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
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setFileVersion(String fileVersion) 
    {
        this.fileVersion = fileVersion;
    }

    public String getFileVersion() 
    {
        return fileVersion;
    }
    public void setFileGithub(String fileGithub) 
    {
        this.fileGithub = fileGithub;
    }

    public String getFileGithub()
    {
        return fileGithub;
    }
    public void setFileRows(String fileRows)  {
        this.fileRows = fileRows;
    }

    public String getFileRows() throws IOException {
        return fileRows;
    }
    public void setFileXinagsidu(String fileXinagsidu)  {
        this.fileXinagsidu=fileXinagsidu;
    }

    public String getFileXinagsidu() throws IOException {
        return fileXinagsidu;
    }

    @Override
    public String toString() {
        try {
            return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("fileId", getFileId())
                .append("fileName", getFileName())
                .append("filePath", getFilePath())
                .append("fileVersion", getFileVersion())
                .append("fileGithub", getFileGithub())
                .append("fileRows", getFileRows())
                .append("fileXinagsidu", getFileXinagsidu())
                    .append("filenewpath",getFilenewpath())
                .toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
