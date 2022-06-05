package com.juntai.project.sell.mall.entrance;


import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2020/3/5 15:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/5 15:55
 */
public class EntrancePresent extends BasePresenter<IModel, EntranceContract.IEntranceView> implements EntranceContract.IEntrancePresent {
    private IView iView;
    public void  setCallBack(IView iView) {
        this.iView = iView;
    }

    @Override
    protected IModel createModel() {
        return null;
    }







}
