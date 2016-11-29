package com.ackywow.session.data.net;

import com.ackywow.session.bean.HttpResult;
import com.ackywow.session.exception.ApiException;
import rx.functions.Func1;

/**
 * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
 *
 * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
 */
public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

  @Override
  public T call(HttpResult<T> httpResult) {
    if (httpResult.getResultCode() != 0) {
      throw new ApiException(httpResult);
    }
    return httpResult.getData();
  }
}
