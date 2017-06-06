package com.shadowxz.service;

import com.shadowxz.domain.Section;

import java.util.List;

/**
 * Created by xz on 2017/4/29.
 */
public interface SectionService {

    void addSection(Section section);

    void updateSection(Section section);

    void deleteSectionById(int id);

    Section findSectionById(int id);

    List<Section> findAllSection();
}
