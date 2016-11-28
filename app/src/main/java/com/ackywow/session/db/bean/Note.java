package com.ackywow.session.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by Jiang on 2016/11/28.
 */
@Entity
public class Note {
  @Id
  private Long id;
  @NotNull
  private String name;
  private String age;
  @Generated(hash = 30400633)
  public Note(Long id, @NotNull String name, String age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  @Generated(hash = 1272611929)
  public Note() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }
}
