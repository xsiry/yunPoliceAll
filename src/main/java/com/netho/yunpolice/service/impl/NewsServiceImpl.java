package com.netho.yunpolice.service.impl;

/**
 * Created by xsiry on 2017/7/10.
 */

import com.netho.yunpolice.dao.INewsDao;
import com.netho.yunpolice.pojo.News;
import com.netho.yunpolice.service.INewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service("newsService")
public class NewsServiceImpl implements INewsService {
    @Resource
    private INewsDao newsDao;
    @Override
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

    public Boolean save(News record) {
        boolean bool = false;
        int count = newsDao.save(record);
        if (count > 0) {
            bool = true;
        }
        return bool;
    }

    public boolean update(News record) {
        boolean bool = false;
        int count = newsDao.update(record);
        if (count > 0) {
            bool = true;
        }
        return bool;
    }

    public boolean del(int id) {
        boolean bool = false;
        int count = newsDao.del(id);
        if (count > 0) {
            bool = true;
        }
        return bool;
    }

    public boolean applyTop(int id) {
        boolean bool = false;
        int applyTotal = newsDao.applyTotalTop();
        if (applyTotal < 5) {
            int count = newsDao.applyTop(id);
            if (count > 0) {
                bool = true;
            }
        }
        return bool;
    }

    public boolean cancelTop(int id) {
        boolean bool = false;
        int count = newsDao.cancelTop(id);
        if (count > 0) {
            bool = true;
        }
        return bool;
    }

    public List<News> getListTop() {
        List<News> list = newsDao.getListTop();
        return list;
    }

}
