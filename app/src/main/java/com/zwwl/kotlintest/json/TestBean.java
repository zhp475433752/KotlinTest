package com.zwwl.kotlintest.json;

import java.io.Serializable;

/**
 * Created by zhanghuipeng on 3/30/21.
 */
public class TestBean implements Serializable {
    // 如果需要将bean转为json字符串，则声明为public；否则生成get和set方法
    public String id;
    public String name;

    public TestBean(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
