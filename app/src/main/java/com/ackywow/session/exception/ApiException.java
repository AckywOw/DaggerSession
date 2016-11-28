package com.ackywow.session.exception;

import com.ackywow.session.bean.HttpResult;

/**
 * Created by Jiang on 2016/11/24.
 */

public class ApiException extends RuntimeException {
  private HttpResult httpResult;

  public <T> ApiException(HttpResult<T> httpResult) {
    super(httpResult.getResultMessage());
    this.httpResult = httpResult;
  }

  public int getResultCode() {
    return httpResult.getResultCode();
  }

  public String getResultMessage() {
    return httpResult.getResultMessage();
  }
}
