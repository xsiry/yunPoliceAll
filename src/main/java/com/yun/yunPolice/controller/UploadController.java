package com.yun.yunPolice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xsiry on 2017/7/12.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("/system/")
public class UploadController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "fileupload", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> fileupload(@RequestParam("file_data") CommonsMultipartFile[] files, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> filepahtls = new ArrayList<String>() ;
        try {
            logger.info("RequestMapping('fileupload') start...");
            System.out.println(files[0].getStorageDescription());
            for(int i = 0;i<files.length;i++){
                if(!files[i].isEmpty()){
                    int pre = (int) System.currentTimeMillis();
                    try {
                        //取得当前上传文件的文件名称
                        String myFileName = files[i].getOriginalFilename();
                        //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                        if(myFileName.trim() !=""){
                            System.out.println(myFileName);
                            //重命名上传后的文件名
                            String fileName = System.currentTimeMillis() + files[i].getOriginalFilename();
                            //定义上传路径
                            String path =  request.getSession().getServletContext().getRealPath("/") + "upload/" + fileName;
                            File localFile = new File(path);
                            files[i].transferTo(localFile);
                            filepahtls.add(path);
                        }
                        int finaltime = (int) System.currentTimeMillis();
                        logger.info("RequestMapping('fileupload') fileName" + files[i].getOriginalFilename() + "times "+(finaltime - pre));
                    } catch (Exception e) {
                        logger.error("ResponseBody('fileupload') error:"+ e.getMessage());
                    }
                }
            }
            map.put("success", true);
            map.put("files", filepahtls);
            logger.info("ResponseBody('fileupload') Map(success:true)");
        } catch (Exception e) {
            map.put("success", false);
            map.put("error", e.getMessage());
            logger.error("ResponseBody('fileupload') Map(success:false, error:" + e.getMessage() + ")");
        }

        return map;
    }
}
