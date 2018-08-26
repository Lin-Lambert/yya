package com.lambert.manager.controller;

import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("file")
public class FileUploadController {

    @Value("${IMAGE_URL}")
    private String IMAGE_URL;

    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Map<String, Object> upload(MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        try {
            ClientGlobal.init(System.getProperty("user.dir") + "/src/main/resources/properties/tracker_server.properties");
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            String original = StringUtils.substringAfter(file.getOriginalFilename(), ".");
            String[] uploadFile = storageClient.upload_file(file.getBytes(), original, null);
//            state : 状态
//            url : 上传成功后图片地址
//            size : 文件大小
//            original : 后缀名 .jpg
//            type : 文件类型
            String URL = IMAGE_URL;
            for (String s : uploadFile) {
                URL = URL + "/" + s;
            }
            map.put("state", "SUCCESS");
            map.put("url", URL);
            map.put("size", file.getSize());
            map.put("original", original);
            map.put("type", file.getContentType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
