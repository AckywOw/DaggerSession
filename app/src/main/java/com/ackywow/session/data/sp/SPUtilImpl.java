package com.ackywow.session.data.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.Set;

/**
 * SharedPreferences处理工具类
 * <p>
 * Created by Jiang on 2016/10/13.
 */

public class SPUtilImpl implements SPUtil {

  /**
   * The shared preferences.
   */
  private SharedPreferences sharedPreferences;

  public SPUtilImpl(Context context) {
    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
  }

  private SharedPreferences getSharedPreferences() {
    return sharedPreferences;
  }

  /**
   * 清空全部
   */
  @Override
  public void clean() {
    getSharedPreferences().edit()
                          .clear()
                          .commit();
  }

  @Override
  public boolean putString(String key, String value) {
    return getSharedPreferences().edit()
                                 .putString(key, value)
                                 .commit();
  }

  @Override
  public String getString(String key, String defValue) {
    return getSharedPreferences().getString(key, defValue);
  }

  @Override
  public boolean putBoolean(String key, boolean value) {
    return getSharedPreferences().edit()
                                 .putBoolean(key, value)
                                 .commit();
  }

  @Override
  public boolean getBoolean(String key, boolean defValue) {
    return getSharedPreferences().getBoolean(key, defValue);
  }

  @Override
  public boolean putInt(String key, int value) {
    return getSharedPreferences().edit()
                                 .putInt(key, value)
                                 .commit();
  }

  @Override
  public int getInt(String key, int defValue) {
    return getSharedPreferences().getInt(key, defValue);
  }

  @Override
  public boolean putLong(String key, long value) {
    return getSharedPreferences().edit()
                                 .putLong(key, value)
                                 .commit();
  }

  @Override
  public long getLong(String key, long defValue) {
    return getSharedPreferences().getLong(key, defValue);
  }

  @Override
  public boolean putFloat(String key, float value) {
    return getSharedPreferences().edit()
                                 .putFloat(key, value)
                                 .commit();
  }

  @Override
  public float getFloat(String key, float defValue) {
    return getSharedPreferences().getFloat(key, defValue);
  }

  @Override
  public boolean putStringSet(String key, Set<String> values) {
    return getSharedPreferences().edit()
                                 .putStringSet(key, values)
                                 .commit();
  }

  @Override
  public Set<String> getStringSet(String key, Set<String> defValues) {
    return getSharedPreferences().getStringSet(key, defValues);
  }
}
