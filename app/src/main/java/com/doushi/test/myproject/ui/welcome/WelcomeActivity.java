package com.doushi.test.myproject.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.doushi.test.myproject.R;
import com.doushi.test.myproject.base.component.BaseActivity;
import com.doushi.test.myproject.ui.main.MainActivity;
import com.doushi.test.myproject.znet.InterfaceConfig;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 欢迎页/程序入口
 *
 * @author xiemy
 * @date 2018-2-27
 */
public class WelcomeActivity extends BaseActivity implements WelcomeView {

    @BindView(R.id.tvWelcome)
    TextView tvWelcome;

    @Override
    public int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_welcome;
    }

    @Override
    public void initEnv() {
        new WelcomePresenter(this).loginByToken();
    }

    @Override
    public void loginSuccess(int showWelcome) {
        toPage(MainActivity.class);
        closePage();
    }

    @Override
    public void loadDataFail(InterfaceConfig.HttpHelperTag apiTag, String errorInfo) {
        tvWelcome.setText(errorInfo);
    }

    @OnClick(R.id.btTest1)
    public void onViewClicked() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
