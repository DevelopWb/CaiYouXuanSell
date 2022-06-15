package com.juntai.project.sell.mall.beans.sell;

import com.contrarywind.interfaces.IPickerViewData;
import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  店铺商品类目列表
 * @CreateDate: 2022/6/12 14:02
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/12 14:02
 */
public class ShopCommodityCategoryListBean extends BaseResult {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean  implements IPickerViewData {
        /**
         * id : 52
         * shopId : 1
         * shopClassifyName : 上衣
         */

        private int id;
        private int shopId;
        private String shopClassifyName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getShopClassifyName() {
            return shopClassifyName;
        }

        public void setShopClassifyName(String shopClassifyName) {
            this.shopClassifyName = shopClassifyName;
        }

        @Override
        public String getPickerViewText() {
            return shopClassifyName;
        }
    }
}
