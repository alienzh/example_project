package com.doushi.test.myproject.ui.main.mv;

import com.doushi.test.myproject.base.mvp.BaseView;

/**
 * 欢迎页UI更新
 *
 * @author xiemy
 * @date 2017/7/26.
 */
public interface MainView extends BaseView {

    /**
     * 成功获取数据
     *
     * @param test 测试
     */
    void getDataSuccess(boolean test);
}