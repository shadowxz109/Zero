package com.shadowxz.service.Impl;

import com.shadowxz.dao.SectionDao;
import com.shadowxz.domain.Section;
import com.shadowxz.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xz on 2017/4/29.
 */
@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    SectionDao sectionDao;


    public void addSection(Section section) {
        sectionDao.insert(section);
    }

    public void updateSection(Section section) {
        sectionDao.updateByPrimaryKeySelective(section);
    }

    public void deleteSectionById(int id) {
        sectionDao.deleteByPrimaryKey(id);
    }

    public Section findSectionById(int id) {
        return sectionDao.selectByPrimaryKey(id);
    }

    public List<Section> findAllSection() {
        return sectionDao.selectAllSection();
    }
}
