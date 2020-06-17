package com.aten.compiler.widget.banner.annotation;

import android.support.annotation.IntDef;
import com.aten.compiler.widget.banner.widget.BannerLayout;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * by y on 2017/1/19.
 */

@IntDef({BannerLayout.CENTER,
        BannerLayout.LEFT,
        BannerLayout.RIGHT})
@Retention(RetentionPolicy.SOURCE)
public @interface DotsAndTitleSiteMode {
}

