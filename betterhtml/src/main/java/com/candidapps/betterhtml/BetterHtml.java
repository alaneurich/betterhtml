package com.candidapps.betterhtml;

/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;

import com.candidapps.betterhtml.provider.BaseSpanProvider;
import com.candidapps.betterhtml.provider.BetterSpanProvider;

import org.ccil.cowan.tagsoup.HTMLSchema;
import org.ccil.cowan.tagsoup.Parser;

/**
 * This class processes HTML strings into displayable styled text.
 * Not all HTML tags are supported.
 */
public class BetterHtml {
    /**
     * Flag indicating that texts inside &lt;p&gt; elements will be separated from other texts with
     * one newline character by default.
     */
    public static final int FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH = 0x00000001;
    /**
     * Flag indicating that texts inside &lt;h1&gt;~&lt;h6&gt; elements will be separated from
     * other texts with one newline character by default.
     */
    public static final int FROM_HTML_SEPARATOR_LINE_BREAK_HEADING = 0x00000002;
    /**
     * Flag indicating that texts inside &lt;li&gt; elements will be separated from other texts
     * with one newline character by default.
     */
    public static final int FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM = 0x00000004;
    /**
     * Flag indicating that texts inside &lt;ul&gt; elements will be separated from other texts
     * with one newline character by default.
     */
    public static final int FROM_HTML_SEPARATOR_LINE_BREAK_LIST = 0x00000008;
    /**
     * Flag indicating that texts inside &lt;div&gt; elements will be separated from other texts
     * with one newline character by default.
     */
    public static final int FROM_HTML_SEPARATOR_LINE_BREAK_DIV = 0x00000010;
    /**
     * Flag indicating that texts inside &lt;blockquote&gt; elements will be separated from other
     * texts with one newline character by default.
     */
    public static final int FROM_HTML_SEPARATOR_LINE_BREAK_BLOCKQUOTE = 0x00000020;

    public static final int FROM_HTML_MODE_LEGACY = 0x00000000;

    public static final int FROM_HTML_MODE_COMPACT =
            FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH
                    | FROM_HTML_SEPARATOR_LINE_BREAK_HEADING
                    | FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM
                    | FROM_HTML_SEPARATOR_LINE_BREAK_LIST
                    | FROM_HTML_SEPARATOR_LINE_BREAK_DIV
                    | FROM_HTML_SEPARATOR_LINE_BREAK_BLOCKQUOTE;

    public static final String TYPEFACE_FAMILY_SERIF = "serif";
    public static final String TYPEFACE_FAMILY_SANS_SERIF = "sans-serif";
    public static final String TYPEFACE_FAMILY_MONOSPACE = "monospace";

    private BetterHtml() { }

    public static class Builder {
        private Context mContext;
        private float[] mHeaderSizes = new float[] {
                1.5f, 1.4f, 1.3f, 1.2f, 1.1f, 1f
        };
        private String mHtmlString;
        private Html.ImageGetter mImageGetter;
        private Html.TagHandler mTagHandler;
        private BaseSpanProvider mSpanProvider;
        private int mFlags = FROM_HTML_MODE_LEGACY;
        private int mListItemIndentSize;
        private boolean mCreateAuthorityAfterLinks = false;

        public Builder(Context context) {
            mContext = context;
            mSpanProvider = new BetterSpanProvider();
            setListItemIndentSizeDp(8);
        }

        public Builder setListItemIndentSizeDp(int sizeInDp) {
            return setListItemIndentSizeSp(dpToSp(sizeInDp));
        }

        public Builder setListItemIndentSizeSp(int sizeInSp) {
            mListItemIndentSize = sizeInSp;
            return this;
        }

        private int dpToSp(int value) {
            return Math.round(value * mContext.getResources().getDisplayMetrics().density);
        }

        public Builder setHeaderSizes(float[] sizes) {
            if(sizes == null || sizes.length != 6) {
                Log.e("betterhtml", "Supplied Header Size Array doesn't meet Requirements. Using default.");
                return this;
            }
            mHeaderSizes = sizes;
            return this;
        }

        public Builder setH1Size(float size) {
            mHeaderSizes[0] = size;
            return this;
        }

        public Builder setH2Size(float size) {
            mHeaderSizes[1] = size;
            return this;
        }

        public Builder setH3Size(float size) {
            mHeaderSizes[2] = size;
            return this;
        }

        public Builder setH4Size(float size) {
            mHeaderSizes[3] = size;
            return this;
        }

        public Builder setH5Size(float size) {
            mHeaderSizes[4] = size;
            return this;
        }

        public Builder setH6Size(float size) {
            mHeaderSizes[5] = size;
            return this;
        }

        public Builder setHtmlString(String htmlString) {
            mHtmlString = htmlString;
            return this;
        }

        public Builder setImageGetter(Html.ImageGetter imageGetter) {
            mImageGetter = imageGetter;
            return this;
        }

        public Builder setTagHandler(Html.TagHandler tagHandler) {
            mTagHandler = tagHandler;
            return this;
        }

        public Builder setSpanProvider(BaseSpanProvider spanProvider) {
            if(spanProvider == null) {
                Log.e("betterhtml", "A null SpanProvider was passed to the Builder. Using default.");
                return this;
            }
            mSpanProvider = spanProvider;
            return this;
        }

        public Builder setCreateAuthorityAfterLinks(boolean createAuthorityAfterLinks) {
            mCreateAuthorityAfterLinks = createAuthorityAfterLinks;
            return this;
        }

        public Builder setFlags(int flags) {
            mFlags = flags;
            return this;
        }

        public Spanned build() {
            Parser parser = new Parser();
            try {
                parser.setProperty(Parser.schemaProperty, HtmlParser.schema);
            } catch (org.xml.sax.SAXNotRecognizedException e) {
                // Should not happen.
                throw new RuntimeException(e);
            } catch (org.xml.sax.SAXNotSupportedException e) {
                // Should not happen.
                throw new RuntimeException(e);
            }
            HtmlToSpannedConverter converter =
                    new HtmlToSpannedConverter(mHtmlString, mImageGetter, mTagHandler, mSpanProvider, parser, mFlags);
            converter.setCreateAuthorityAfterLinks(mCreateAuthorityAfterLinks);
            converter.setHeaderSizes(mHeaderSizes);
            converter.setListItemIndentSize(mListItemIndentSize);
            return converter.convert();
        }
    }

    /**
     * Lazy initialization holder for HTML parser. This class will
     * a) be preloaded by the zygote, or b) not loaded until absolutely
     * necessary.
     */
    private static class HtmlParser {
        private static final HTMLSchema schema = new HTMLSchema();
    }
}
