package com.shadowxz.util;


import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Created by xz on 2017/6/12.
 */
public class ImageUtil {
        static String accessKey = "";
        static String secretKey = "";
        static String bucket = "";

    public static  String saveImg(MultipartFile multipartFile, String imgType) throws IOException {
        Configuration c = new Configuration(Zone.autoZone());
        UploadManager uploadManager = new UploadManager(c);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        String contentType = multipartFile.getContentType();
        String fileName = "img/user/"+UUID.randomUUID();
        if(contentType.equals("image/jpeg")||contentType.equals("application/x-jpg")){
            fileName = fileName +".jpg";
        }else if(contentType.equals("image/png")){
            fileName = fileName +".png";
        }
        Response response = uploadManager.put(multipartFile.getInputStream(), fileName, upToken,null,contentType);
        return (String) response.jsonToMap().get("key");
    }


    public static  String getImg(String key) throws UnsupportedEncodingException {

        String encodedFileName = URLEncoder.encode(key, "utf-8");

        String publicUrl = String.format("%s/%s", bucket, encodedFileName);

        Auth auth = Auth.create(accessKey,secretKey);

        String finalUrl = auth.privateDownloadUrl(publicUrl,31536000);

        return finalUrl;
    }
}
