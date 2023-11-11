-optimizationpasses 5

#okhttp
-dontwarn okio.**
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-adaptresourcefilenames okhttp3/internal/publicsuffix/PublicSuffixDatabase.gz
-dontwarn org.codehaus.mojo.animal_sniffer.*
-dontwarn okhttp3.internal.platform.**
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**

# Retrofit
-dontwarn javax.annotation.**
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,EnclosingMethod
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
-keepattributes AnnotationDefault
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
-dontwarn kotlin.Unit
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*
-dontwarn retrofit2.Platform$Java8
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface * extends <1>
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}


#Moshi
-keep class com.squareup.moshi.** { *; }
-keepclassmembers class com.squareup.moshi.** { *; }
-keepnames @kotlin.Metadata class com.squareup.moshi.**

-dontwarn org.jetbrains.annotations.**
-keepclasseswithmembers class * {
    @com.squareup.moshi.* <methods>;
}
-keep @com.squareup.moshi.JsonQualifier @interface *
-keepclassmembers @com.squareup.moshi.JsonClass class * extends java.lang.Enum {
    <fields>;
    **[] values();
}
-keepclassmembers class com.squareup.moshi.internal.Util {
    private static java.lang.String getKotlinMetadataClassName();
}
-keepclassmembers class * {
  @com.squareup.moshi.FromJson <methods>;
  @com.squareup.moshi.ToJson <methods>;
}
-keepclasseswithmembers class * {
    @com.squareup.moshi.* <methods>;
}
-keep class kotlin.Metadata { *; }
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}


#Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembers class kotlinx.coroutines.** {
    volatile <fields>;
}
-keepclassmembers class kotlin.coroutines.SafeContinuation {
    volatile <fields>;
}
-dontwarn java.lang.instrument.ClassFileTransformer
-dontwarn sun.misc.SignalHandler
-dontwarn java.lang.instrument.Instrumentation
-dontwarn sun.misc.Signal
-dontwarn java.lang.ClassValue

#Lifecycle
-keep class androidx.lifecycle.** { *; }


#chucker
-keep class com.chuckerteam.chucker.internal.data.entity.HttpTransaction { *; }
-keep class com.chuckerteam.chucker.** { *; }
-keepclassmembers class com.chuckerteam.chucker.** { *; }
-keepnames @kotlin.Metadata class com.chuckerteam.chucker.**
-keep class com.google.gson.** { *; }
-keepclassmembers class com.google.gson.** { *; }
-keepnames @kotlin.Metadata class com.google.gson.**


#android
-keep @interface androidx.annotation.Keep
-keep @androidx.annotation.Keep class *
-keepclasseswithmembers class * {
  @androidx.annotation.Keep <fields>;
}
-keepclasseswithmembers class * {
  @androidx.annotation.Keep <methods>;
}
-keepclassmembernames class * {
    java.lang.Class class$(java.lang.String);
    java.lang.Class class$(java.lang.String, boolean);
}
-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends java.lang.Enum {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}


#Application
-keepnames @kotlin.Metadata class app.yekzan.module.data.**
-keep class app.yekzan.module.data.** { *; }
-keepclassmembers class app.yekzan.module.data.** { *; }

-keepnames @kotlin.Metadata class app.yekzan.module.data.data.model.**
-keep class app.yekzan.module.data.data.model.** { *; }
-keepclassmembers class app.yekzan.module.data.data.model.** { *; }

-keepnames @kotlin.Metadata class app.yekzan.module.data.data.remote.**
-keep class app.yekzan.module.data.data.remote.** { *; }
-keepclassmembers class app.yekzan.module.data.data.remote.** { *; }

-keepnames @kotlin.Metadata class app.yekzan.module.core.util.audioPlayer.**
-keep class app.yekzan.module.core.util.audioPlayer.** { *; }
-keepclassmembers class app.yekzan.module.core.util.audioPlayer.** { *; }
