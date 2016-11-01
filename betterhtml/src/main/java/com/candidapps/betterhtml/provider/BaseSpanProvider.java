package com.candidapps.betterhtml.provider;

import android.graphics.drawable.Drawable;

import com.candidapps.betterhtml.annotations.TypefaceFamilyDef;
import com.candidapps.betterhtml.annotations.TypefaceStyleDef;

public interface BaseSpanProvider {
    Object[] onCreateBackgroundColorSpans(int bgColor);
    Object[] onCreateForegroundColorSpans(int fgColor);
    Object[] onCreateStrikethroughSpans();
    Object[] onCreateSuperscriptSpans();
    Object[] onCreateSubscriptSpans();
    Object[] onCreateUnderlineSpans();
    Object[] onCreateSizeSpans(float size);
    Object[] onCreateHeadingSpans(int level, float size);
    Object[] onCreateTypefaceStyleSpans(@TypefaceStyleDef int typefaceStyle);
    Object[] onCreateImageSpans(Drawable draw, String src);
    Object[] onCreateTypefaceSpans(@TypefaceFamilyDef String fontFamily);
    Object[] onCreateListItemSpans(int indent);
    Object[] onCreateQuoteSpans();
    Object[] onCreateUrlSpans(String url);
    String onCreateAuthorityString(String url);
}
