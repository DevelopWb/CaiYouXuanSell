package com.juntai.project.sell.mall.beans.order;

import com.juntai.disabled.basecomponent.base.BaseResult;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/19 9:26
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/19 9:26
 */
public class OrderPayZfbBean extends BaseResult {

    /**
     * data : alipay_sdk=alipay-sdk-java-3.7.110.ALL&app_id=2019122060094093&biz_content=%7B%22body%22%3A%22%E5%90%9B%E6%B3%B0%E5%95%86%E5%9F%8E%22%2C%22out_trade_no%22%3A%2220191226090756061%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22seller_id%22%3A%222088731141099773%22%2C%22subject%22%3A%22%E5%90%9B%E6%B3%B0%E5%95%86%E5%9F%8E%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%22101.29%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&sign=OfPI4N5dpNs4XWWOac%2F67EX%2BBE5dWy2eApQCAkRW9Gt3FbdXAPXKsAfPBwK%2BvKWhyh71bAMiCii9vxm3Hoyh39mpcUXozs9az%2FgN1ikGIfa%2FLlY56VCNAb6CguPwRzDRZCUAnidLQQK2pWZk9QYQhPQMuWvVAMMGDrrWWdd%2BXfmbiIbXMYmMNIT4JiVahUU%2FRt1eXifkc%2Brs043QER0%2FEakCFj5xQSCUVFZdEh1ylNUM9NI1zx8pYAlA0112mMs%2Fj32E1wa3226Xzn8fHXXYFkX8Xio8Tr7NyegJCOkEkFOxxPd%2BX3X2ikRmcHAYWNnZ2ekBXtctJMp7ryEIqWJb0g%3D%3D&sign_type=RSA2×tamp=2019-12-26+09%3A08%3A10&version=1.0
     */

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
