package com.doushi.test.myproject.ui.welcome;

import com.doushi.library.util.AppInfoUtil;
import com.doushi.library.util.AppIntroUtil;
import com.doushi.test.myproject.base.mvp.BasePresenter;
import com.doushi.test.myproject.model.base.BaseApiResponse;
import com.doushi.test.myproject.model.user.ConfigResponse;
import com.doushi.test.myproject.znet.InterfaceConfig;
import com.doushi.test.myproject.znet.request.RxRequestCallback;
import com.doushi.test.myproject.znet.rx.RxHttpHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiemy
 * @date 2018/2/28.
 */
public class WelcomePresenter extends BasePresenter<WelcomeView> {

    WelcomePresenter(WelcomeView view) {
        super(view);
    }

    void loginByToken() {
        String version;
        try {
            version = AppInfoUtil.getAppVersionName();
        } catch (Exception e) {
            e.printStackTrace();
            version = "1.0.0";
        }

        Map<String, Object> params = new HashMap<>();
        params.put("version", version);
        params.put("platform", 1);
        params.put("firstStartup", AppIntroUtil.getInstance().getIsFirstStartUp());
        new RxRequestCallback().doRequestData(params, InterfaceConfig.HttpHelperTag.HTTPHelperTag_ReportCrashLog, ConfigResponse.class, this);
    }

    @Override
    public void onLoadDataSuccess(InterfaceConfig.HttpHelperTag apiTag, BaseApiResponse modelRes, Map<String, Object> params) {
        switch (apiTag) {
            case HTTPHelperTag_ReportCrashLog:
//                ConfigResponse cRes = (ConfigResponse) modelRes;
//                RxHttpHelper.sharedInstance().setToken(cRes.getData().getUserInfo().getToken());
//                RxHttpHelper.sharedInstance().setUid(cRes.getData().getUserInfo().getUid());
//                getMvpView().loginSuccess(modelRes.getErrcode());
                break;

            default:
                break;
        }
    }
}
