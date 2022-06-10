package com.juntai.project.sell.mall.beans.sell.adapterbean;

import com.chad.library.adapter.base.BaseQuickAdapter;


/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/7/3 11:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/7/3 11:03
 */
public class BaseNormalRecyclerviewBean {


    private  int  type = 0;
    private Object  object ;
    private BaseQuickAdapter adapter;

    public BaseNormalRecyclerviewBean(int type, Object object, BaseQuickAdapter adapter) {
        this.type = type;
        this.object = object;
        this.adapter = adapter;
    }

    public int getType() {
        return type;
    }

    public BaseQuickAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(BaseQuickAdapter adapter) {
        this.adapter = adapter;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
