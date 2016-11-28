package com.ackywow.session.db;

import com.ackywow.session.db.bean.Note;
import java.util.Map;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 *
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

  private final DaoConfig noteDaoConfig;

  private final NoteDao noteDao;

  public DaoSession(Database db, IdentityScopeType type,
      Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> daoConfigMap) {
    super(db);

    noteDaoConfig = daoConfigMap.get(NoteDao.class)
                                .clone();
    noteDaoConfig.initIdentityScope(type);

    noteDao = new NoteDao(noteDaoConfig, this);

    registerDao(Note.class, noteDao);
  }

  public void clear() {
    noteDaoConfig.clearIdentityScope();
  }

  public NoteDao getNoteDao() {
    return noteDao;
  }
}
