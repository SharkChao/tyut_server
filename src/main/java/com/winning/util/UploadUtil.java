package com.winning.util;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.winning.common.UploadProperty;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Component
@EnableConfigurationProperties(UploadProperty.class)
public class UploadUtil {

	//spring 中无法注入静态变量，只能通过间接注入的方式，使用 @AutoWired直接报错，使用Resource时
    // 直接报找到了2个同样的bean，但是我其实只有1个这样的Bean。
    @Resource(name = "uploadProperty")
    private UploadProperty tempUploadProperty;

    private static UploadProperty uploadProperty;

    // 在servlet中 会在构造函数之后执行, 同样可以实现  InitializingBean 接口
    @PostConstruct
    private void init(){
        uploadProperty = tempUploadProperty;
    }

    /**
     * 上传图片
     * @param file
     * @return 图片存储路径。
     * @throws FileNotFoundException 
     */
    public static String uploadImage(MultipartFile file) throws FileNotFoundException{
    	//1.获取图片后缀名
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //2.生成路径
        Path path = Paths.get("static");
        String filePath = path.toAbsolutePath().toString();
        //3.生成文件路径
        File fileDir = new File(filePath);
        fileDir.mkdirs();
        String uuid = UUID.randomUUID().toString() + suffix;
        File realFile = new File(fileDir, uuid);
        try {
            IOUtils.copy(file.getInputStream(),new FileOutputStream(realFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String tempPath =  "http://106.12.84.31:9090/image/"+uuid;
        return tempPath;
    }
    /**
     * 校验图片类型
     * @param file
     * @return
     */
	public static boolean checkImageType(MultipartFile[] files) {
		List<String> acceptTypes = uploadProperty.getAcceptType();
		if(acceptTypes.size()==0){
			acceptTypes.add("image/png");
			acceptTypes.add("image/jpeg");
			acceptTypes.add("image/jpg");
		}
		for (MultipartFile file : files) {
			// 判断图片类型
			String type = file.getContentType();
			if (!acceptTypes.contains(type)) {
				return false;
			}			
		}
		return true;
	}
	


    /**
     * 校验图片大小
     * @param file
     * @return
     */
	public static boolean checkImageSize(MultipartFile[] files) {
		int maxSize = uploadProperty.getMaxSize();
		if(maxSize==0)	{
			maxSize=20480;//单位KB
		}		
		for (MultipartFile file : files) {
			// 判断图片大小
			int size = (int) Math.ceil(file.getSize() / 1024 );
			if (size > maxSize) {
				return false;
			}		
		}
		return true;
	}
    
    
}