package com.yun.yunPolice.service.impl;

/**
 * Created by xsiry on 2017/7/10.
 */

import com.yun.yunPolice.dao.INewsDao;
import com.yun.yunPolice.pojo.News;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("NewsService")
public class NewsServiceImpl {
    @Resource
    private INewsDao iNewsDao;

    public List<News> getList(int pageNo) {
        int limit = 10;
        int page = (pageNo - 1) * limit;
        List<News> list = iNewsDao.getList(page, limit);
        return list;
    }

    public News get(int id) {
        News news = iNewsDao.get(id);
        return news;
    }

    public boolean update(News news) {
        boolean bool = false;
        Integer count = iNewsDao.update(news);
        if (count > 0) {
            bool = true;
        }
        return bool;
    }

    public boolean del(int id) {
        boolean bool = false;
        Integer count = iNewsDao.del(id);
        if (count > 0) {
            bool = true;
        }
        return bool;
    }

}
