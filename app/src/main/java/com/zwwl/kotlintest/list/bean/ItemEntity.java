package com.zwwl.kotlintest.list.bean;

/**
 * Created by zhanghuipeng on 2021/10/19.
 */
public class ItemEntity {

    private ItemType itemType;
    private int resIcon;
    private String label;
    private String tips;
    private ItemClickAction action;

    @FunctionalInterface
    public interface ItemClickAction{
        void apply();
    }

    public ItemEntity(ItemType itemType, int resIcon, String label, String tips, ItemClickAction action) {
        this.itemType = itemType;
        this.resIcon = resIcon;
        this.label = label;
        this.tips = tips;
        this.action = action;
    }

    public ItemType getItemType() {
        return itemType;
    }


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
}
