# betterhtml
This is basically a fork of the [`Html`](https://github.com/android/platform_frameworks_base/blob/master/core/java/android/text/Html.java)
Class in Android with the toHtml Conversion stripped away and an easily customizable fromHtml Conversion.

# Include via Gradle

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

Then, add the library to your project `build.gradle`
```gradle
dependencies {
    compile 'com.github.budsmile:betterhtml:1.0.0'
}
```

# Usage
In contrast to the original [`Html`](https://github.com/android/platform_frameworks_base/blob/master/core/java/android/text/Html.java)
Class we're not using a single Function for conversion but a Builder Class instead.

##Simple Usage
If you want to use BetterHtml without customizing anything all you have to do
is the following:

```java
String htmlString = "<b>Hello World</b>"
BetterHtml.Builder builder = new BetterHtml.Builder(context)
        .setHtmlString(htmlString);
CharSequence convertedString = builder.build();
TextView sampleText = findViewById(R.id.sample_text);
sampleText.setMovementMethod(LinkMovementMethod.getInstance());
sampleText.setText(convertedString);
```

##BetterHtml.Builder
*Built-In Customizations*

The [`BetterHtml.Builder`](https://github.com/budsmile/betterhtml/blob/master/betterhtml/src/main/java/com/candidapps/betterhtml/BetterHtml.java)
already allows for some customizations not found in the original [`Html`](https://github.com/android/platform_frameworks_base/blob/master/core/java/android/text/Html.java)
Class.

###Header Sizes
To change all Header Sizes either use

```java
//The following are the default Values.
float[] headerSizes = new float[] {
    1.5f, //h1
    1.4f, //h2
    1.3f, //h3
    1.2f, //h4
    1.1f, //h5
    1f    //h6
}
builder.setHeaderSizes(headerSizes);
```

or use one of the following Methods to change individual Header Sizes:

```java
setH1Size(float size);
setH2Size(float size);
setH3Size(float size);
setH4Size(float size);
setH5Size(float size);
setH6Size(float size);
```

###List Item Indent

As most Lists get parsed without a margin it looks way better to apply
some margins to those by hand. This can be done by using one of the two
following methods:

```java
setListItemIndentSizeSp(int sizeInSp);
//Or if you want so set a dp Value
setListItemIndentSizeDp(int sizeInDp);
```

###Url Authority after a Text Link

If you'd like to append the Authority of the href Attribute to the Text inside
an a Tag just call the following Method:

```java
builder.setCreateAuthorityAfterLinks(true);
```

Example:

```html
<a href="https://www.google.com">Awesome Link</a>
```

will be converted to

[Awesome Link - (www.google.com)](https://www.google.com)

##Extending SpanProvider
*Full Customization*

###Using DefaultSpanProvider

By default BetterHtml uses the [`BetterSpanProvider`](https://github.com/budsmile/betterhtml/blob/master/betterhtml/src/main/java/com/candidapps/betterhtml/provider/BetterSpanProvider.java)
Class. This allows for some of the Customization Options and will allow further customizations
to the default parsing in the future.

[`BetterSpanProvider`](https://github.com/budsmile/betterhtml/blob/master/betterhtml/src/main/java/com/candidapps/betterhtml/provider/BetterSpanProvider.java)
extends from [`DefaultSpanProvider`](https://github.com/budsmile/betterhtml/blob/master/betterhtml/src/main/java/com/candidapps/betterhtml/provider/DefaultSpanProvider.java)
which will produce exactly the same output you'd expect from the fromHtml function in the original
Html Class. If you'd prefer the default Output just call the following
on your [`BetterHtml.Builder`](https://github.com/budsmile/betterhtml/blob/master/betterhtml/src/main/java/com/candidapps/betterhtml/BetterHtml.java):

```java
builder.setSpanProvider(new DefaultSpanProvider());
```

That's it! :) BetterHtml will now produce default Output. **Note that this will ignore
the settings set on the [`BetterHtml.Builder`](https://github.com/budsmile/betterhtml/blob/master/betterhtml/src/main/java/com/candidapps/betterhtml/BetterHtml.java).**

###Overriding Methods

Instead of just choosing between [`DefaultSpanProvider`](https://github.com/budsmile/betterhtml/blob/master/betterhtml/src/main/java/com/candidapps/betterhtml/provider/DefaultSpanProvider.java)
and [`BetterSpanProvider`](https://github.com/budsmile/betterhtml/blob/master/betterhtml/src/main/java/com/candidapps/betterhtml/provider/BetterSpanProvider.java)
it's also possible to extend these classes and override some of their methods
to get an unique Look for some Tags.

It's also possible to implement the [`BaseSpanProvider`](https://github.com/budsmile/betterhtml/blob/master/betterhtml/src/main/java/com/candidapps/betterhtml/provider/BaseSpanProvider.java)
Interface but then you'll have to provide all Creation Methods yourself.


License
--------

    Copyright (C) 2016 Alan Eurich
    Copyright (C) 2007 The Android Open Source Project

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

