# Merge
-flattenpackagehierarchy com.github.catvod.spider.merge

# dontwarn
-dontwarn org.slf4j.impl.**
-dontwarn org.xmlpull.v1.**
-dontwarn android.content.res.**

# slf4j
-keep class org.slf4j.** { *; }

# AndroidX
-keep class androidx.core.** { *; }

# Spider
-keep class com.github.catvod.crawler.* { *; }
-keep class com.github.catvod.spider.* { public <methods>; }
-keep class com.github.catvod.js.Function { *; }

# OkHttp
-dontwarn okhttp3.**
-keep class okio.** { *; }
-keep class okhttp3.** { *; }

# QuickJS
-keep class com.whl.quickjs.** { *; }

# Sardine
-keep class com.thegrizzlylabs.sardineandroid.** { *; }

# Logger
-keep class com.orhanobut.logger.** { *; }

# Google Guava
-keep class com.google.common.** { *; }
-dontwarn com.google.common.**

# Java AWT
-keep class java.awt.** { *; }
-dontwarn java.awt.**

# JSR 354 (javax.money)
-keep class javax.money.** { *; }
-dontwarn javax.money.**

# Moneta (org.javamoney.moneta)
-keep class org.javamoney.moneta.** { *; }
-dontwarn org.javamoney.moneta.**

# Joda-Time
-keep class org.joda.time.** { *; }
-dontwarn org.joda.time.**

# Springfox
#-keep class springfox.documentation.spring.web.json.Json { *; }
-dontwarn springfox.documentation.spring.web.json.Json
# Merge
-flattenpackagehierarchy com.github.catvod.spider.merge

# dontwarn
-dontwarn org.slf4j.impl.**
-dontwarn org.xmlpull.v1.**
-dontwarn android.content.res.**

# slf4j
-keep class org.slf4j.** { *; }

# AndroidX
-keep class androidx.core.** { *; }

# Spider
-keep class com.github.catvod.crawler.* { *; }
-keep class com.github.catvod.spider.* { public <methods>; }
-keep class com.github.catvod.js.Function { *; }

# OkHttp
-dontwarn okhttp3.**
-keep class okio.** { *; }
-keep class okhttp3.** { *; }

# QuickJS
-keep class com.whl.quickjs.** { *; }

# Sardine
-keep class com.thegrizzlylabs.sardineandroid.** { *; }

# Logger
-keep class com.orhanobut.logger.** { *; }