package com.doushi.test.myproject.ui.main.home.child;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.doushi.library.util.ImageLoadUtils;
import com.doushi.library.widgets.layoutManager.DividerItemDecoration;
import com.doushi.test.myproject.R;
import com.doushi.test.myproject.base.component.BaseRefreshFragment;
import com.doushi.test.myproject.global.DefaultValue;
import com.doushi.test.myproject.global.ParamConstants;
import com.doushi.test.myproject.model.search.SearchUserResponse;
import com.doushi.test.myproject.model.user.UserInfo;
import com.doushi.test.myproject.model.video.VideoDetails;
import com.doushi.test.myproject.ui.refresh.RefreshListActivity;
import com.doushi.test.myproject.ui.refresh.rp.RefreshPresenter;
import com.doushi.test.myproject.ui.refresh.rv.RefreshListView;
import com.doushi.test.myproject.znet.InterfaceConfig;

import java.util.List;

/**
 * @author xiemy
 * @date 2018/3/19.
 */
public class FirstFragment extends BaseRefreshFragment<VideoDetails> implements RefreshListView {

    private RefreshPresenter followPresenter;

    @Override
    public RecyclerView.Adapter getRefreshAdapter(List<VideoDetails> dataList) {
        LinearLayoutManager llm = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        canContentView.setLayoutManager(llm);
        DividerItemDecoration decoration = new DividerItemDecoration(_mActivity, 5, true,
                getResources().getColor(R.color.default_toast_bg));
        canContentView.addItemDecoration(decoration);
        BaseQuickAdapter adapter = new BaseQuickAdapter<VideoDetails, BaseViewHolder>(R.layout.item_search_user, dataList) {
            @Override
            protected void convert(BaseViewHolder helper, VideoDetails item) {
                helper.setText(R.id.tvUserName, item.getNick() + ":" + helper.getAdapterPosition());
                ImageView ivVideoBg = helper.getView(R.id.ivVideoBg);
                new ImageLoadUtils(FirstFragment.this).commonDisplayImage(item.getImgUrl(), ivVideoBg, DefaultValue.BACKGROUND);

                ImageView ivHead = helper.getView(R.id.ivUserAvatar);
                new ImageLoadUtils(FirstFragment.this).commonCircleImage(item.getHead(), ivHead, DefaultValue.HEAD);
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VideoDetails info = (VideoDetails) adapter.getItem(position);
                if (null == info) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString(ParamConstants.SEARCH_KEY, info.getNick());
                toPage(RefreshListActivity.class, bundle);
            }
        });
        return adapter;
    }

    @Override
    public void refreshDataList() {
        if (followPresenter == null) {
            followPresenter = new RefreshPresenter(this);
        }
        followPresenter.getSearchUsers(page, CNT, "ceshi");
    }

    @Override
    public void getDataSuccess(SearchUserResponse dataRes) {
        //List<UserInfo> dataList = ObjectUtils.isNotEmpty(dataRes.getData()) ? dataRes.getData().getUser_list() : null;
        //loadDataSuccess(dataList);
    }

    @Override
    public void getVideoListSuccess(List<VideoDetails> videoList) {
        loadDataSuccess(videoList);
    }

    @Override
    public void loadDataFail(InterfaceConfig.HttpHelperTag apiTag, String errorInfo) {

    }
}
