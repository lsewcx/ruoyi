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
    public Integer fileJiage;

    /** 实际代码量 */
    @Excel(name = "实际代码量")
    public Integer fileShijidaima;

    /** 最终价格 */
    @Excel(name = "最终价格")
    public Integer fileZuozhongjiage;

    public void setFileId(Long fileId) 
    {
        this.fileId = fileId;
    }

    public Long getFileId() 
    {
        return fileId;
    }
    public void setFileJiage(Integer fileJiage)
    {
        this.fileJiage = fileJiage;
    }

    public Integer getFileJiage()
    {
        return fileJiage;
    }
    public void setFileShijidaima(Integer fileShijidaima)
    {
        this.fileShijidaima = fileShijidaima;
    }

    public Integer getFileShijidaima()
    {
        return fileShijidaima;
    }
    public void setFileZuozhongjiage(Integer fileZuozhongjiage)
    {
        this.fileZuozhongjiage = fileZuozhongjiage;
    }

    public Integer getFileZuozhongjiage()
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
