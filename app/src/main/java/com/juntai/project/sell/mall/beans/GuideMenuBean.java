package com.juntai.project.sell.mall.beans;

import android.text.TextUtils;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/18 15:33
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/18 15:33
 */
public class GuideMenuBean {


    private  String menuName;
    private  String menuEnName;
    private int menuPicId;
    private int menuPicBgId;


    public GuideMenuBean(String menuName, String menuEnName, int menuPicId, int menuPicBgId) {
        this.menuName = menuName;
        this.menuEnName = menuEnName;
        this.menuPicId = menuPicId;
        this.menuPicBgId = menuPicBgId;
    }

    public int getMenuPicBgId() {
        return menuPicBgId;
    }

    public void setMenuPicBgId(int menuPicBgId) {
        this.menuPicBgId = menuPicBgId;
    }

    public String getMenuEnName() {
        return menuEnName == null ? "" : menuEnName;
    }

    public void setMenuEnName(String menuEnName) {
        this.menuEnName = menuEnName == null ? "" : menuEnName;
    }

    public String getMenuName() {
        return TextUtils.isEmpty(menuName) ? "暂无" : menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? "" : menuName;
    }

    public int getMenuPicId() {
        return menuPicId;
    }

    public void setMenuPicId(int menuPicId) {
        this.menuPicId = menuPicId;
    }
}
