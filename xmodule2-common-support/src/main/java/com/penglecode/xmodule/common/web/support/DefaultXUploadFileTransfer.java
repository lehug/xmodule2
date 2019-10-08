package com.penglecode.xmodule.common.web.support;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.penglecode.xmodule.common.consts.GlobalConstants;
import com.penglecode.xmodule.common.exception.ApplicationFileUploadException;
import com.penglecode.xmodule.common.util.DateTimeUtils;
import com.penglecode.xmodule.common.util.FileUtils;
import com.penglecode.xmodule.common.util.SpringWebMvcUtils;
import com.penglecode.xmodule.common.util.UUIDUtils;

/**
 * 默认的基于Tomcat服务器的通用小文件上传助手
 * 
 * @author 	pengpeng
 * @date	2018年4月18日 上午8:29:47
 */
public class DefaultXUploadFileTransfer implements XUploadFileHelper {

	@Override
	public String createRenamedFileName(String originalFileName) {
		//重命名上传后的文件名
        String renamedFileName = UUIDUtils.uuid() + originalFileName.substring(originalFileName.lastIndexOf('.'));
        String tempFileSavePath = GlobalConstants.DEFAULT_UPLOAD_TEMP_SAVE_PATH + "/" + DateTimeUtils.formatNow("yyyyMMdd") + "/" + renamedFileName;
        return FileUtils.formatPath(tempFileSavePath);
	}

	@Override
	public TransferResult tempTransfer(MultipartFile uploadFile, String tempFileSavePath)  {
		try {
			HttpServletRequest request = SpringWebMvcUtils.getCurrentHttpServletRequest();
			String fileServerDir = request.getServletContext().getRealPath("/");
			
			String httpContextPath = request.getRequestURL().toString();
			String requestUri = request.getRequestURI();
			httpContextPath = httpContextPath.substring(0, httpContextPath.indexOf(requestUri));
			
			String destFullFileName = FileUtils.formatPath(fileServerDir + "/" + tempFileSavePath);
			FileUtils.mkDirIfNecessary(destFullFileName);
			uploadFile.transferTo(new File(destFullFileName));
			return new TransferResult(tempFileSavePath, destFullFileName, FileUtils.formatPath(httpContextPath + tempFileSavePath));
		} catch (Exception e) {
			throw new ApplicationFileUploadException(e.getMessage(), e);
		}
	}

	@Override
	public TransferResult storeTransfer(String tempFileSavePath, String fileStorePath) {
		try {
			HttpServletRequest request = SpringWebMvcUtils.getCurrentHttpServletRequest();
			String fileServerDir = request.getServletContext().getRealPath("/");
			
			String httpContextPath = request.getRequestURL().toString();
			String requestUri = request.getRequestURI();
			httpContextPath = httpContextPath.substring(0, httpContextPath.indexOf(requestUri));
			
			String srcFullFileName = FileUtils.formatPath(fileServerDir + "/" + tempFileSavePath);
			String storeUploadFileName = tempFileSavePath.replace(GlobalConstants.DEFAULT_UPLOAD_TEMP_SAVE_PATH, fileStorePath);
			String fileServerRootDir = GlobalConstants.GLOBAL_APP_CONFIG.getFileServerDir();
			String destFullFileName = FileUtils.formatPath(fileServerRootDir + storeUploadFileName);
			FileUtils.mkDirIfNecessary(destFullFileName);
			FileUtils.copyFile(srcFullFileName, destFullFileName);
			return new TransferResult(storeUploadFileName, destFullFileName, FileUtils.formatPath(httpContextPath + storeUploadFileName));
		} catch (Exception e) {
			throw new ApplicationFileUploadException(e.getMessage(), e);
		}
	}

	@Override
	public void tempRemove(String fileSavePath) {
		HttpServletRequest request = SpringWebMvcUtils.getCurrentHttpServletRequest();
		String fileServerDir = request.getServletContext().getRealPath("/");
		String destFullFileName = FileUtils.formatPath(fileServerDir + "/" + fileSavePath);
		FileUtils.deleteFileQuietly(destFullFileName);
	}

	@Override
	public void storeRemove(String fileSavePath) {
		HttpServletRequest request = SpringWebMvcUtils.getCurrentHttpServletRequest();
		String fileServerDir = request.getServletContext().getRealPath("/");
		String destFullFileName = FileUtils.formatPath(fileServerDir + "/" + fileSavePath);
		FileUtils.deleteFileQuietly(destFullFileName);
	}
	
}
