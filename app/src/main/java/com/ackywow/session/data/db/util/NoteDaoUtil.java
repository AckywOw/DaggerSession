package com.ackywow.session.data.db.util;

import com.ackywow.session.data.db.bean.Note;
import com.ackywow.session.data.db.dao.NoteDao;

/**
 * Created by Jiang on 2016/11/29.
 */
public class NoteDaoUtil extends AbstractMainDaoUtil<NoteDao, Note, Long> {

  public NoteDaoUtil(NoteDao dao) {
    super(dao);
  }
}
