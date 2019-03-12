package com.xuyj.boatmate.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageTools {
	public static String imageDirPath;
	static{
		File file=new File(ToolUtil.getWebRootSubDir("/image"));
		String parentPath=file.getParentFile().getParentFile().getAbsolutePath();
		File imageDir=new File(parentPath+File.separator+"image");
		if(!imageDir.exists()){
			imageDir.mkdir();
		}
		imageDirPath=imageDir.getAbsolutePath();
	}
	public static boolean saveImage (File imagePic,String name){
		//File file=new File(ToolUtil.getWebRootSubDir("/image"));
		
		
//		if(!file.exists()){
//			file.mkdir();
//		}
		String path=imageDirPath+File.separator+name;
		boolean isSuccess=false;
		 OutputStream os = null;
		 InputStream is=null;
	        try {
	            // 2、保存到临时文件
	            // 1K的数据缓冲
	            byte[] bs = new byte[1024];
	            // 读取到的数据长度
	            int len;
	            File tempFile=new File(path);
	            
	            // 输出的文件流保存到本地文件
	            tempFile.createNewFile();
	            is=new FileInputStream(imagePic);
	            os = new FileOutputStream(tempFile);
	            // 开始读取
	            while ((len = is.read(bs)) != -1) {
	                os.write(bs, 0, len);
	            }
	            isSuccess=true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            
	        } finally {
	            // 完毕，关闭所有链接
	            try {
	                os.close();
	                is.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        	return isSuccess;
	        }
	        
	}

}
