package com.yun.yunPolice.service.impl;

/**
 * Created by xsiry on 2017/7/10.
 */

import com.yun.yunPolice.dao.INewsDao;
import com.yun.yunPolice.pojo.News;
import com.yun.yunPolice.service.INewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service("newsService")
public class NewsServiceImpl implements INewsService{
    @Resource
    private INewsDao newsDao;

    public List<News> getList(int page, int pagesize, String sortname, String sortorder) {
        page = (page - 1) * pagesize;
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("pagesize", pagesize);
        map.put("sortname", sortname);
        map.put("sortorder", sortorder);
        List<News> list = newsDao.getList(map);
        return list;
    }

    public Integer totalList() {
        int total = newsDao.totalList();
        return total;
    }

    public News get(int id) {
        News news = newsDao.get(id);
        return news;
    }

    public boolean update(News news) {
        boolean bool = false;
        Integer count = newsDao.update(news);
        if (count > 0) {
            bool = true;
        }
        return bool;
    }

    public boolean del(int id) {
        boolean bool = false;
        Integer count = newsDao.del(id);
        if (count > 0) {
            bool = true;
        }
        return bool;
    }

}
