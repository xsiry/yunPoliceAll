package com.yun.yunPolice.service;

import com.yun.yunPolice.pojo.News;

import java.util.List;

/**
 * Created by xsiry on 2017/7/10.
 */
public interface INewsService {
    public List<News> getList(int page, int pagesize, String sortname, String sortorder);

    public Integer totalList();

    public News get(int id);

    public Boolean save(News record);

    public boolean update(News record);

    public boolean del(int id);

    public boolean applyTop(int id);

    public boolean cancelTop(int id);
}
