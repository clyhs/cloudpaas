package com.cloudpaas.admin.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "t_menu")
public class AdminMenu implements Serializable  {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    private Integer id;

    private String title;

    private String url;

    private String icon;

    private Integer sort;

    private String remark;

    @Column(name = "pid")
    private Integer pId;

    private Integer level;
    
    /**
     * 1：目录，2：菜单，3：按钮
     */
    private Integer type;
    
    

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "create_time")
    private Date createTime;
	
	/**
     * 1：显示，2：隐藏
     */
	@Column(name = "is_show")
    private Integer isShow;
    
    

    public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return order
     */
    

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	/**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return pid
     */
    public Integer getPId() {
        return pId;
    }

    /**
     * @param pid
     */
    public void setPid(Integer pId) {
        this.pId = pId;
    }

    /**
     * @return level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return create_time
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}