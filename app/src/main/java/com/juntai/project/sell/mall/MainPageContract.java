package com.juntai.project.sell.mall;

import com.juntai.disabled.basecomponent.mvp.IPresenter;
import com.juntai.disabled.basecomponent.mvp.IView;

/**
 * Describe: 首页
 * Create by zhangzhenlong
 * 2020-8-8
 * email:954101549@qq.com
 */
public interface MainPageContract {
    String DELETE_NEWS_DRAFTS = "delete_news_drafts";
    String UPLOAD_HISTORY = "upload_history";


    interface IMainPageView extends IView {
    }

    interface IMainPagePresent extends IPresenter<IMainPageView> {

        /**
         * 轨迹长传
         * @param data
         */
        void uploadHistory(String data, String tag);
    }
}
