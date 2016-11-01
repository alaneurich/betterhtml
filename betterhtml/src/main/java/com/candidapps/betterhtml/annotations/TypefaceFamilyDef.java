package com.candidapps.betterhtml.annotations;

import android.support.annotation.StringDef;

import com.candidapps.betterhtml.BetterHtml;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({BetterHtml.TYPEFACE_FAMILY_SERIF, BetterHtml.TYPEFACE_FAMILY_SANS_SERIF,
        BetterHtml.TYPEFACE_FAMILY_MONOSPACE})
public @interface TypefaceFamilyDef {}

