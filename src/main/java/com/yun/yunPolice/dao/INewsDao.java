package com.yun.yunPolice.dao;

import com.yun.yunPolice.pojo.News;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xsiry on 2017/7/10.
 */
public interface INewsDao {
    List<News> getList(HashMap<String, Object> map);

    Integer totalList();

    News get(@Param("id") int id);

    Integer update(News record);

    Integer del(@Param("id") int id);
}
