package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * word上传对象 sys_word_info
 * 
 * @author lse
 * @date 2023-04-03
 */
public class SysWordInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文件id */
    private Long fileId;

    /** 文字 */
    @Excel(name = "文字")
    public String fileWord;

    public void setFileId(Long fileId) 
    {
        this.fileId = fileId;
    }

    public Long getFileId() 
    {
        return fileId;
    }
    public void setFileWord(String fileWord) 
    {
        this.fileWord = fileWord;
    }

    public String getFileWord() 
    {
        return fileWord;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fileId", getFileId())
            .append("fileWord", getFileWord())
            .toString();
    }
}
