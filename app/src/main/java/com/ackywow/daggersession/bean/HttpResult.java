package com.ackywow.daggersession.bean;

/**
 * Created by Jiang on 2016/11/24.
 */

public class HttpResult<Data> {

  public static final int SUCCESS = 0;

  private int resultCode;
  private String resultMessage;
  private Data data;

  public int getResultCode() {
    return resultCode;
  }

  public void setResultCode(int resultCode) {
    this.resultCode = resultCode;
  }

  public String getResultMessage() {
    return resultMessage;
  }

  public void setResultMessage(String resultMessage) {
    this.resultMessage = resultMessage;
  }

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }
}
