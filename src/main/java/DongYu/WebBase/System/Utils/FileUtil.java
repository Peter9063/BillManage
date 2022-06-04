package DongYu.WebBase.System.Utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUtil {
	/**
	 * 得到UUID
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static String getUUIDpath(String path, String fileName){
		String filePath = path + UUID.randomUUID()+fileName;
		if(new File(filePath).exists()){
			return getUUIDpath(path,fileName);
		}else
			return filePath;
	}
	
	
	public static void initDownLoad(HttpServletResponse response,
			HttpServletRequest request, String filename) {
		response.setContentType("octets/stream");
		response.addHeader("Content-Type", "text/html; charset=utf-8");
		response.addHeader("Content-Disposition", "attachment;filename="
				+ FileUtil.getDownloadFileName(request,filename));
	}
	
	public static String getDownloadFileName(HttpServletRequest request,String filename){
		String agent = request.getHeader("USER-AGENT");
		String downLoadName = null;
		try {
			if (null != agent && -1 != agent.indexOf("MSIE")) // IE
			{
				downLoadName = java.net.URLEncoder.encode(filename, "UTF-8");

			} else if (null != agent && -1 != agent.indexOf("Mozilla")) // Firefox
			{
				downLoadName = new String(filename.getBytes("UTF-8"),
						"iso-8859-1");
			} else {
				downLoadName = java.net.URLEncoder.encode(filename, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			downLoadName = "file";
		}
		return downLoadName;
	}

	public static void outFile(InputStream is,HttpServletResponse response){
		OutputStream os = null;
		try{
			os = response.getOutputStream();
		    int i;
		    byte b[] = new byte[8192];
		    while ((i=is.read(b)) != -1) { //从输入流读取数据
		    	os.write(b, 0, i);
		    	b =  new byte[8192];
		    }
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			try {
				if(is != null)
					is.close();
			} catch (IOException e) {
			} 
		    try {
		    	if(os != null)
		    		os.close();
			} catch (IOException e) {
			}
		}
	}
	
	public static ResponseEntity<byte[]> outputFile(byte[] content,MediaType mediaType,String fileName){
		try {
			fileName = new String(fileName.getBytes(), "iso8859-1");//解决中文 文件名问题 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		ResponseEntity<byte[]> returnValue=null;
		if(content!=null && mediaType!=null && fileName!=null){
			HttpHeaders entityHeaders=new HttpHeaders();
			entityHeaders.setContentType(mediaType);
			entityHeaders.setContentDispositionFormData("attachment", fileName);
			returnValue=new ResponseEntity<byte[]>(content,
												   entityHeaders,
												   HttpStatus.OK);
		}
		return returnValue;
	}


	
	
	
	public static void createDirectory(String string) {
		new File(string).mkdirs();
	}

	public static List<File> getFiles(File f) {
		List<File> list = new ArrayList<File>();
		getFiles(f, list);
		return list;
	}

	public static void getFiles(File file, List<File> list) {
		if (file.isDirectory()) {
			// 得到当前目录里面的文件列表
			File files[] = file.listFiles();
			for (File f : files) {
				getFiles(f, list);
			}
		} else {
			list.add(file);
		}
	}
	public static void saveMultipartFile(MultipartFile f,String path){
		InputStream is = null;
		try {
			is = f.getInputStream();
		} catch (IOException e) {
			return;
		}
		saveFile(is,path);	
	}
	
	public static void saveFile(InputStream is, String path) {
		OutputStream os = null;
		try {
			File outFile = new File(path);
			os = new FileOutputStream(outFile);
			int i;
			byte b[] = new byte[8192];
			while ((i = is.read(b)) != -1) {
				os.write(b, 0, i);
				b = new byte[8192];
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("save file not success");
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception e) {
			}
			try {
				if (os != null)
					os.close();
			} catch (Exception e) {

			}
		}
	}

	
}
