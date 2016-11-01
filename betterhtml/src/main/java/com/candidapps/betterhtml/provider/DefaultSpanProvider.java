package com.candidapps.betterhtml.provider;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;

import com.candidapps.betterhtml.annotations.TypefaceFamilyDef;
import com.candidapps.betterhtml.annotations.TypefaceStyleDef;
import com.candidapps.betterhtml.spans.ClickableUrlSpan;

public class DefaultSpanProvider implements BaseSpanProvider {

    private final static float[] HEADER_SIZES = new float[] {
            1.5f, 1.4f, 1.3f, 1.2f, 1.1f, 1f
    };

    @Override
    public Object[] onCreateBackgroundColorSpans(int bgColor) {
        return new Object[] {
                new BackgroundColorSpan(bgColor)
        };
    }

    @Override
    public Object[] onCreateForegroundColorSpans(int fgColor) {
        return new Object[] {
                new ForegroundColorSpan(fgColor)
        };
    }

    @Override
    public Object[] onCreateStrikethroughSpans() {
        return new Object[] {
                new StrikethroughSpan()
        };
    }

    @Override
    public Object[] onCreateSuperscriptSpans() {
        return new Object[] {
                new SuperscriptSpan()
        };
    }

    @Override
    public Object[] onCreateSubscriptSpans() {
        return new Object[] {
                new SubscriptSpan()
        };
    }

    @Override
    public Object[] onCreateUnderlineSpans() {
        return new Object[] {
                new UnderlineSpan()
        };
    }

    @Override
    public Object[] onCreateSizeSpans(float size) {
        return new Object[] {
                new RelativeSizeSpan(size)
        };
    }

    @Override
    public Object[] onCreateHeadingSpans(int level, float size) {
        return new Object[] {
                new RelativeSizeSpan(HEADER_SIZES[level]),
                new StyleSpan(Typeface.BOLD)
        };
    }

    @Override
    public Object[] onCreateTypefaceStyleSpans(@TypefaceStyleDef int typefaceStyle) {
        return new Object[] {
                new StyleSpan(typefaceStyle)
        };
    }

    @Override
    public Object[] onCreateImageSpans(Drawable draw, String src) {
        return new Object[] {
                new ImageSpan(draw, src)
        };
    }

    @Override
    public Object[] onCreateTypefaceSpans(@TypefaceFamilyDef String fontFamily) {
        return new Object[] {
                new TypefaceSpan(fontFamily)
        };
    }

    @Override
    public Object[] onCreateListItemSpans(int indent) {
        return new Object[]{
                new BulletSpan()
        };
    }

    @Override
    public Object[] onCreateQuoteSpans() {
        return new Object[]{
                new QuoteSpan()
        };
    }

    @Override
    public Object[] onCreateUrlSpans(String url) {
        return new Object[] {
                new ClickableUrlSpan(url)
        };
    }

    @Override
    public String onCreateAuthorityString(String url) {
        return "";
    }
}
