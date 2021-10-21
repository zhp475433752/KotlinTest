package com.zwwl.kotlintest.list.bean;

import java.util.List;

/**
 * Created by zhanghuipeng on 2021/10/18.
 */
public class GroupEntity {
    private String label;
    private List<SubEntity> list;

    public String getLabel() {
        return label;
    }

    public List<SubEntity> getList() {
        return list;
    }

    public GroupEntity(String label, List<SubEntity> list) {
        this.label = label;
        this.list = list;
    }
}
