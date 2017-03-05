package com.example.ravi.depaul.lessons;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

public class Class implements ParentListItem {

    private String mName;
    private List<Subject> mSubjects;

    public Class(String name, List<Subject> subjects) {
        mName = name;
        mSubjects = subjects;
    }

    public String getName() {
        return mName;
    }

    @Override
    public List<?> getChildItemList() {
        return mSubjects;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
