# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


-keep public class com.lingan.edongspeechlibrary.listener.CommandListener { *; }

-keep public class com.lingan.edongspeechlibrary.listener.SmartConfigListener { *; }

-keep public class com.lingan.edongspeechlibrary.speech.SpeechCommand { *; }

-keep public class com.lingan.edongspeechlibrary.receiver.SmartConfig { *; }

-keep public class com.lingan.edongspeechlibrary.SpeechSpeaker { *; }

-keep public class com.lingan.edongspeechlibrary.SpeechApplication { *; }



-libraryjars ./src/main/jniLibs/armeabi/libaiengine.so
-libraryjars ./src/main/jniLibs/armeabi/libcpldaudiojni.so
-libraryjars ./src/main/jniLibs/armeabi/liblame.so
-libraryjars ./src/main/jniLibs/armeabi/libspiaudiojni.so


-keep class com.aispeech.**{*;}

-keep class com.google.gson.**{*;}

-keep class org.litepal.**{*;}
-keep class * extends org.litepal.crud.DataSupport { *; }
##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature
# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.lingan.edongspeechlibrary.bean.** { *; }

##---------------End: proguard configuration for Gson  ----------

