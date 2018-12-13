package com.wjf.dao;

import com.wjf.domain.entity.HotelResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelResourceDao {

    int insertSelective(HotelResource record);//添加酒店静态资源

    int insertOrUpdateList(List<HotelResource> list);

}
