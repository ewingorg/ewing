package com.admin.dto;


/**
 * 资源分类对象
 * 
 * @author tanson lam
 * @creation 2015年5月13日
 */
public class ResCategoryTreeDto {

	private Integer id;
	private Integer pId;
	private String name;
	private Boolean open;
	private String file;
	
	public ResCategoryTreeDto(Integer id, Integer pId, String name,
			Boolean open, String file) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = open;
		this.file = file;
	}
	public String getFile() {
		return file;
	}
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Boolean getOpen() {
		return open;
	}
	public Integer getpId() {
		return pId;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
}
