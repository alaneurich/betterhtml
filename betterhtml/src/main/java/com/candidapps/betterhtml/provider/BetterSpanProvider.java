package com.candidapps.betterhtml.provider;

import android.graphics.Typeface;
import android.net.Uri;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.candidapps.betterhtml.spans.BetterBulletSpan;

public class BetterSpanProvider extends DefaultSpanProvider {
    @Override
    public Object[] onCreateListItemSpans(int indent) {
        return new Object[] {
                new BetterBulletSpan(indent)
        };
    }

    @Override
    public Object[] onCreateHeadingSpans(int level, float size) {
        return new Object[] {
                new RelativeSizeSpan(size),
                new StyleSpan(Typeface.BOLD)
        };
    }

    @Override
    public String onCreateAuthorityString(String url) {
        return " - (" + Uri.parse(url).getAuthority() + ")";
    }
}
