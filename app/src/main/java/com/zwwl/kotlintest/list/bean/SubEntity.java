package com.zwwl.kotlintest.list.bean;

/**
 * Created by zhanghuipeng on 2021/10/18.
 */
public class SubEntity {
    private int resIcon;
    private String label;
    private String tips;
    private ItemClickAction action;

    public int getResIcon() {
        return resIcon;
    }

    public String getLabel() {
        return label;
    }

    public String getTips() {
        return tips;
    }

    public ItemClickAction getAction() {
        return action;
    }

    public SubEntity(int resIcon, String label, String tips, ItemClickAction action) {
        this.resIcon = resIcon;
        this.label = label;
        this.tips = tips;
        this.action = action;
    }

    @FunctionalInterface
    public interface ItemClickAction{
        void apply();
    }

}


