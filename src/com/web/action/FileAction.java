package com.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.admin.action.MainAction;
import com.admin.constant.GroupType;
import com.admin.model.WebBlock;
import com.core.app.action.base.BaseAction;
import com.core.app.action.base.ResponseData;
import com.core.app.action.base.ResponseUtils;
import com.core.factory.SysParamFactory;

public class FileAction extends BaseAction {

	private static Logger logger = Logger.getLogger(FileAction.class);

	private File uploadfile;
	// 使用列表保存多个上传文件的文件名
	private String uploadfileFileName;
	// 使用列表保存多个上传文件的MIME类型
	private String uploadfileContentType;

	private InputStream inputStream;

	private String fileName;

	private String downTargetPath;

	public String getDownTargetPath() {
		return downTargetPath;
	}

	public void setDownTargetPath(String downTargetPath) {
		this.downTargetPath = downTargetPath;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void show() {
		Map<String, Object> dataModel = new HashMap<String, Object>();
		render("/admin/fileupload.html", dataModel);
	}

	/**
	 * 文件上传
	 */
	public void upload() {
		if(uploadfileFileName ==null){
			ResponseData responseData = null;
			responseData = ResponseUtils.fail("上传失败！"); 
			this.outResult(responseData);
		}
			
		String attachPath = SysParamFactory.WEB_REAL_PATH + "fileupload"
				+ File.separator;
		String savePath = attachPath;
		File rootPath = new File(savePath);
		if (!rootPath.exists()) {
			rootPath.mkdirs();
		}

		InputStream in = null;
		OutputStream out = null;
		String fileName = uploadfileFileName.toString();
		int typeInd = fileName.lastIndexOf(".");
		if (typeInd > 0) {
			fileName = fileName.substring(0, typeInd)
					+ System.currentTimeMillis() + fileName.substring(typeInd);
		}

		File detfile = new File(savePath + File.separator + fileName);

		int BUFFER_SIZE = 99999;
		try {
			in = new BufferedInputStream(new FileInputStream(uploadfile),
					BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(detfile),
					BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			while (in.read(buffer) > 0) {
				out.write(buffer);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		FileInfo resultFile = new FileInfo();
		resultFile.setFilePath(detfile.getAbsolutePath());
		resultFile.setFileName(uploadfileFileName);
		resultFile.setFileSize(detfile.length());
		logger.info(resultFile);
		ResponseData responseData = null;
		responseData = ResponseUtils.success("保存成功！");
		responseData.setResult(resultFile);
		this.outResult(responseData);
	}

	public InputStream getDownloadFile() throws Exception {
		String filePath = ServletActionContext.getServletContext().getRealPath(
				"user.gif");
		return new FileInputStream(new File(filePath));
	}

	// Action调用的下载文件方法
	public String down() {
		return "down";
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	// //获得下载文件的内容，可以直接读入一个物理文件或从数据库中获取内容
	public InputStream getInputStream() throws Exception {
		// File file = new File("d:\\A.doc");
		String fileRealPath = ServletActionContext.getServletContext()
				.getRealPath(downTargetPath);
		File file = new File(fileRealPath);
		fileName = file.getName();
		if (file.exists()) {
			inputStream = new FileInputStream(file);
		}
		return inputStream;
	}

	public String getFileName() throws UnsupportedEncodingException {
		try {
			// 中文文件名也是需要转码为 ISO8859-1，否则乱码
			return new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedEncodingException("不支持该类型文件！");
		}

	}

	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getUploadfileFileName() {
		return uploadfileFileName;
	}

	public void setUploadfileFileName(String uploadfileFileName) {
		this.uploadfileFileName = uploadfileFileName;
	}

	public String getUploadfileContentType() {
		return uploadfileContentType;
	}

	public void setUploadfileContentType(String uploadfileContentType) {
		this.uploadfileContentType = uploadfileContentType;
	}

}
