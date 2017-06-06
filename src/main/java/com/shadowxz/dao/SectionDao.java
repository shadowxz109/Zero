package com.shadowxz.dao;

import com.shadowxz.domain.Section;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionDao {
    int deleteByPrimaryKey(int id);

    int insert(Section record);

    int insertSelective(Section record);

    Section selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(Section record);

    int updateByPrimaryKey(Section record);

    List<Section> selectAllSection();
}