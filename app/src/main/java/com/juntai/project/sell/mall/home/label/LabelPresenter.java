package com.juntai.project.sell.mall.home.label;


import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.disabled.basecomponent.mvp.IView;
import com.juntai.project.sell.mall.home.label.labelbean.LabelListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe:标签关联类
 * Create by zhangzhenlong
 * 2021-2-5
 * email:954101549@qq.com
 */
public class LabelPresenter extends BasePresenter<IModel, LabelContract.ILabelView> implements LabelContract.ILabelPresenter {
    private IView iView;

    public void setCallBack(IView iView) {
        this.iView = iView;
    }

    @Override
    protected IModel createModel() {
        return null;
    }

    @Override
    public void getLabelList(String tag) {
        IView viewCallBack = null;
        if (getView() == null) {
            if (iView != null) {
                viewCallBack = iView;
            }
        } else {
            viewCallBack = getView();
        }
        IView finalViewCallBack = viewCallBack;
//        AppNetModuleMall.createrRetrofit()
//                .getAllLabelList(MyApp.isLogin()? MyApp.getUid()+"" : null)
//                .compose(RxScheduler.ObsIoMain(viewCallBack))
//                .subscribe(new BaseObserver<LabelListBean>(null) {
//                    @Override
//                    public void onSuccess(LabelListBean o) {
//                        if (finalViewCallBack != null) {
//                            finalViewCallBack.onSuccess(tag, o);
//                        }
//                    }
//                    @Override
//                    public void onError(String msg) {
//                        if (finalViewCallBack != null) {
//                            finalViewCallBack.onError(tag, msg);
//                        }
//                    }
//                });
    }

    @Override
    public void updateUserLabel(List<Integer> ids, String tag) {
        IView viewCallBack = null;
        if (getView() == null) {
            if (iView != null) {
                viewCallBack = iView;
            }
        } else {
            viewCallBack = getView();
        }
        IView finalViewCallBack = viewCallBack;

//        AppNetModuleMall.createrRetrofit()
//                .updateUserLabel(MyApp.getUid(), ids)
//                .compose(RxScheduler.ObsIoMain(viewCallBack))
//                .subscribe(new BaseObserver<BaseResult>(viewCallBack) {
//                    @Override
//                    public void onSuccess(BaseResult o) {
//                        if (finalViewCallBack != null) {
//                            finalViewCallBack.onSuccess(tag, o);
//                        }
//                    }
//                    @Override
//                    public void onError(String msg) {
//                        if (finalViewCallBack != null) {
//                            finalViewCallBack.onError(tag, msg);
//                        }
//                    }
//                });
    }

    @Override
    public void addUserLabel(int userId, String name, String tag) {
//        AppNetModuleMall.createrRetrofit()
//                .addUserLabel(userId, name)
//                .compose(RxScheduler.ObsIoMain(getView()))
//                .subscribe(new BaseObserver<BaseResult>(getView()) {
//                    @Override
//                    public void onSuccess(BaseResult o) {
//                        if (getView() != null) {
//                            getView().onSuccess(tag, o);
//                        }
//                    }
//                    @Override
//                    public void onError(String msg) {
//                        if (getView() != null) {
//                            getView().onError(tag, msg);
//                        }
//                    }
//                });
    }

    /**
     * 固定标签
     * @return
     */
    public List<LabelListBean.LabelBean> getDefaultLabels(){
        List<LabelListBean.LabelBean> labels = new ArrayList<>();
        labels.add(new LabelListBean.LabelBean(0, "推荐", 1, true));
        labels.add(new LabelListBean.LabelBean(1, "关注", 1, true));
        labels.add(new LabelListBean.LabelBean(2, "视频", 1, true));
        labels.add(new LabelListBean.LabelBean(3, "直播", 1, true));
        return labels;
    }
}
