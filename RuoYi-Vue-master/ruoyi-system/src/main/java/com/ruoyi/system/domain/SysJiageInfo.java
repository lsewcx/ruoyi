package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 最终价格计算对象 sys_jiage_info
 * 
 * @author lse
 * @date 2023-04-04
 */
public class SysJiageInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文件id */
    private Long fileId;

    /** 价格 */
    @Excel(name = "价格")
    public Long fileJiage;

    /** 实际代码量 */
    @Excel(name = "实际代码量")
    public String fileShijidaima;

    /** 最终价格 */
    @Excel(name = "最终价格")
    public Long fileZuozhongjiage;

    public void setFileId(Long fileId) 
    {
        this.fileId = fileId;
    }

    public Long getFileId() 
    {
        return fileId;
    }
    public void setFileJiage(Long fileJiage) 
    {
        this.fileJiage = fileJiage;
    }

    public Long getFileJiage() 
    {
        return fileJiage;
    }
    public void setFileShijidaima(String fileShijidaima) 
    {
        this.fileShijidaima = fileShijidaima;
    }

    public String getFileShijidaima() 
    {
        return fileShijidaima;
    }
    public void setFileZuozhongjiage(Long fileZuozhongjiage) 
    {
        this.fileZuozhongjiage = fileZuozhongjiage;
    }

    public Long getFileZuozhongjiage() 
    {
        return fileZuozhongjiage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fileId", getFileId())
            .append("fileJiage", getFileJiage())
            .append("fileShijidaima", getFileShijidaima())
            .append("fileZuozhongjiage", getFileZuozhongjiage())
            .toString();
    }
}
