package com.ackywow.session.data.db.upgrade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.ackywow.session.data.db.dao.DaoMaster;
import org.greenrobot.greendao.database.Database;

/**
 * Created by Jiang on 16/1/20.
 */
public class MainDBUpgradeOpenHelper extends DaoMaster.OpenHelper {

  private static final String TAG = "UpgradeOpenHelper";

  public MainDBUpgradeOpenHelper(Context context, String name,
      SQLiteDatabase.CursorFactory factory) {
    super(context, name, factory);
  }

  @Override
  public void onUpgrade(Database db, int oldVersion, int newVersion) {
    for (int i = oldVersion; i < newVersion; i++) {
      try {
        AbstractMigratorHelper migratorHelper = (AbstractMigratorHelper) Class.forName(
            "com.hecom.db.upgrade.DBMigrationHelper" + (i + 1))
                                                                              .newInstance();
        if (migratorHelper != null) {
          migratorHelper.onUpgrade(db);
        }
      } catch (Exception e) {
        Log.e(TAG, "Could not migrate from schema from schema: " + i + " to " + i++);
        break;
      }
    }
  }
}
