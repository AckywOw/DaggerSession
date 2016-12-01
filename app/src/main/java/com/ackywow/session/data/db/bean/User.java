package com.ackywow.session.data.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by Jiang on 2016/11/24.
 */
@Entity
public class User {
  @Id
  private String userId;
  @NotNull
  private String name;
  private String age;
  private String address;

  @Generated(hash = 1792887877)
  public User(String userId, @NotNull String name, String age, String address) {
    this.userId = userId;
    this.name = name;
    this.age = age;
    this.address = address;
  }

  @Generated(hash = 586692638)
  public User() {
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
