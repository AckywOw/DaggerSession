package com.ackywow.session.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.ackywow.session.MyApp;
import java.util.Set;

/**
 * SharedPreferences处理工具类
 * <p>
 * Created by Jiang on 2016/10/13.
 */

public class PrefUtil {

  /**
   * The shared preferences.
   */
  private static SharedPreferences sharedPreferences =
      PreferenceManager.getDefaultSharedPreferences(MyApp.getApplication());

  private static SharedPreferences getSharedPreferences() {
    return sharedPreferences;
  }

  /**
   * 清空全部
   */
  public static void clean() {
    getSharedPreferences().edit()
                          .clear()
                          .commit();
  }

  public static boolean putString(String key, String value) {
    return getSharedPreferences().edit()
                                 .putString(key, value)
                                 .commit();
  }

  public static String getString(String key, String defValue) {
    return getSharedPreferences().getString(key, defValue);
  }

  public static boolean putBoolean(String key, boolean value) {
    return getSharedPreferences().edit()
                                 .putBoolean(key, value)
                                 .commit();
  }

  public static boolean getBoolean(String key, boolean defValue) {
    return getSharedPreferences().getBoolean(key, defValue);
  }

  public static boolean putInt(String key, int value) {
    return getSharedPreferences().edit()
                                 .putInt(key, value)
                                 .commit();
  }

  public static int getInt(String key, int defValue) {
    return getSharedPreferences().getInt(key, defValue);
  }

  public static boolean putLong(String key, long value) {
    return getSharedPreferences().edit()
                                 .putLong(key, value)
                                 .commit();
  }

  public static long getLong(String key, long defValue) {
    return getSharedPreferences().getLong(key, defValue);
  }

  public static boolean putFloat(String key, float value) {
    return getSharedPreferences().edit()
                                 .putFloat(key, value)
                                 .commit();
  }

  public static float getFloat(String key, float defValue) {
    return getSharedPreferences().getFloat(key, defValue);
  }

  public static boolean putStringSet(String key, Set<String> values) {
    return getSharedPreferences().edit()
                                 .putStringSet(key, values)
                                 .commit();
  }

  public static Set<String> getStringSet(String key, Set<String> defValues) {
    return getSharedPreferences().getStringSet(key, defValues);
  }
}
