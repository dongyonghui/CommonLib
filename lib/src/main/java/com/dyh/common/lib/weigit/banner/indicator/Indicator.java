package com.dyh.common.lib.weigit.banner.indicator;

import android.view.View;

import androidx.annotation.NonNull;

import com.dyh.common.lib.weigit.banner.config.IndicatorConfig;
import com.dyh.common.lib.weigit.banner.listener.OnPageChangeListener;

public interface Indicator extends OnPageChangeListener {
    @NonNull
    View getIndicatorView();

    IndicatorConfig getIndicatorConfig();

    void onPageChanged(int count, int currentPosition);

}
