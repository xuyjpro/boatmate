package com.xuyj.boatmate.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String contentType;// 文件的类型
	private long contentLength;// 流的长度
	private String contentDisposition;// 用于指定文件名的内容配置头值
	private InputStream inputStream;// 读取文件的输入流

	public String load() throws IOException {
		// 通过getParameters（）方法获取请求的参数集合
		Map<String, Object> map = ActionContext.getContext().getParameters();
		// 通过对应的key获取相应的vuale[]
		String[] fName = (String[]) map.get("fileName");
		// 遍历数组获取对应的value值（本文的值只有一个）
		String fName1 = fName[0];
		contentType = "text/html";// 文件下载的类型
		contentDisposition = "attachment; filename = " + fName1 + "";
		// 获取指定下载文件的路径
		ServletContext servletContext = ServletActionContext.getServletContext();
		String fileName = servletContext.getRealPath("/image/" + fName1 + "");
		inputStream = new FileInputStream(fileName);
		contentLength = inputStream.available();
		return SUCCESS;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
