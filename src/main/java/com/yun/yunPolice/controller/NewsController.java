package com.yun.yunPolice.controller;

import com.yun.yunPolice.pojo.News;
import com.yun.yunPolice.service.INewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xsiry on 2017/7/10.
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("/news/")
public class NewsController {
    @Resource
    private INewsService iNewsService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "getNews", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getNews(@PathParam(value = "pageNo") Integer pageNo) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            logger.info("RequestMapping('getNews') Param{pageNo: " + pageNo + "}");
            @SuppressWarnings("unchecked")
//            HashMap<String, Object> sessionObj = (HashMap<String, Object>) WebUtils.getSessionAttribute(request, TOKEN);
            List<News> list = iNewsService.getList(pageNo);
            map.put("success", true);
            map.put("list", list);
            logger.info("ResponseBody('getNews') Map(success:true)");
        } catch (Exception e) {
            map.put("success", false);
            map.put("error", e.getMessage());
            logger.error("ResponseBody('getNews') Map(success:false, error:" + e.getMessage() + ")");
        }

        return map;
    }

    @RequestMapping(value = "getNew", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getNew(@PathParam(value = "id") Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            logger.info("RequestMapping('getNews') Param{id: " + id + "}");
            @SuppressWarnings("unchecked")
//            HashMap<String, Object> sessionObj = (HashMap<String, Object>) WebUtils.getSessionAttribute(request, TOKEN);
            News news = iNewsService.get(id);
            map.put("success", true);
            map.put("news", news);
            logger.info("ResponseBody('getNews') Map(success:true)");
        } catch (Exception e) {
            map.put("success", false);
            map.put("error", e.getMessage());
            logger.error("ResponseBody('getNews') Map(success:false, error:" + e.getMessage() + ")");
        }

        return map;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public @ResponseBody Boolean update(@RequestBody News record) {
        boolean bool = false;
        try {
            logger.info("RequestMapping('newsUpdate') Param{News: " + record + "}");
            bool = iNewsService.update(record);
            logger.info("ResponseBody('newsUpdate') Boolean(true)");
        } catch (Exception e) {
            logger.error("ResponseBody('newsUpdate') Boolean(false) error: " + e.getMessage());
        }

        return bool;
    }

    @RequestMapping(value = "del/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Boolean del(@PathVariable("id") Integer id) {
        boolean bool = false;
        try {
            logger.info("RequestMapping('newsDelete') Param{id: " + id + "}");
            bool = iNewsService.del(id);
            logger.info("ResponseBody('newsDelete') Boolean(true)");
        } catch (Exception e) {
            logger.error("ResponseBody('newsDelete') Boolean(false) error: " + e.getMessage());
        }

        return bool;
    }
}
