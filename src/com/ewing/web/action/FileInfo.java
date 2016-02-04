/**
 * 
 */
package com.ewing.web.action;

import com.ewing.core.factory.SysParamFactory;

/**
 * 文件信息
 */

public class FileInfo {

	private static final Long MBYTE_SIZE = 1024 * 1024l;
	private static final Long GBYTE_SIZE = 1024 * 1024 * 1024L;
	/**
	 * 文件存储路径
	 */
	private String filePath;
	/**
	 * web文件访问路径
	 */
	private String webfilePath;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 文件大小
	 */
	private Long fileSize;
	/**
	 * 文件大小格式化显示
	 */
	public String fileSizeFormat;

	public String getFileSizeFormat() {
		return fileSizeFormat;
	}

	public void setFileSizeFormat() {
		if (fileSize >= MBYTE_SIZE && fileSize < GBYTE_SIZE) {
			fileSizeFormat = String.format("%.2f",
					(fileSize / (MBYTE_SIZE * 1f))) + "MB";
		} else if (fileSize >= GBYTE_SIZE) {
			fileSizeFormat = String.format("%.2f",
					(fileSize / (GBYTE_SIZE * 1f))) + "GB";
		} else {
			fileSizeFormat = fileSize + "Byte";
		}
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
		setFileSizeFormat();
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String fPath) {
		this.filePath = fPath.replace(SysParamFactory.WEB_REAL_PATH, "")
				.replace("\\", "/");
		if (!this.filePath.startsWith("/"))
			this.filePath = "/" + this.filePath;
		webfilePath = SysParamFactory.WEB_CONTEXT_PATH  + this.filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getWebfilePath() {
		return webfilePath;
	}

	public void setWebfilePath(String webfilePath) {
		this.webfilePath = webfilePath;
	}

	@Override
	public String toString() {
		return "FileInfo [filePath=" + filePath + ", webfilePath="
				+ webfilePath + ", fileName=" + fileName + ", fileSize="
				+ fileSize + ", fileSizeFormat=" + fileSizeFormat + "]";
	}

	public static void main(String[] args) {
		FileInfo f = new FileInfo();
		f.setFileSize(89999111l);
		System.out.println(f.getFileSizeFormat());
	}
}