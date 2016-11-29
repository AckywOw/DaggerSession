package com.ackywow.session.data.sp;

import java.util.Set;

/**
 * Created by Jiang on 2016/11/29.
 */

public interface SPUtil {

  void clean();

  boolean putString(String key, String value);

  String getString(String key, String defValue);

  boolean putBoolean(String key, boolean value);

  boolean getBoolean(String key, boolean defValue);

  boolean putInt(String key, int value);

  int getInt(String key, int defValue);

  boolean putLong(String key, long value);

  long getLong(String key, long defValue);

  boolean putFloat(String key, float value);

  float getFloat(String key, float defValue);

  boolean putStringSet(String key, Set<String> values);

  Set<String> getStringSet(String key, Set<String> defValues);
}
