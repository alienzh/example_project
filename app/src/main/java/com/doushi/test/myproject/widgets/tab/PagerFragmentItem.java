package com.doushi.test.myproject.widgets.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

/**
 * @author xiemy
 * @date 2018/3/19.
 */
public class PagerFragmentItem extends FragmentPagerItem {

    private int itemId = -1;

    PagerFragmentItem(CharSequence title, float width, String className, Bundle extras) {
        super(title, width, className, extras);
    }

    public static PagerFragmentItem make(CharSequence title, Class<? extends Fragment> clazz, Bundle extras) {
        return new PagerFragmentItem(title, DEFAULT_WIDTH, clazz.getName(), extras);
    }

    public PagerFragmentItem setItemId(int itemId) {
        this.itemId = itemId;
        return this;
    }

    public int getItemId() {
        return itemId;
    }
}
