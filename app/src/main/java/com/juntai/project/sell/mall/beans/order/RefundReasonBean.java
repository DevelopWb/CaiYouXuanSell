package com.juntai.project.sell.mall.beans.order;

import com.juntai.disabled.basecomponent.base.BaseResult;

import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/15 14:56
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/15 14:56
 */
public class RefundReasonBean extends BaseResult {

    private List<DataDTO> data;

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO {
        private int id;
        private int typeId;
        private String causeName;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public String getCauseName() {
            return causeName;
        }

        public void setCauseName(String causeName) {
            this.causeName = causeName;
        }
    }
}
