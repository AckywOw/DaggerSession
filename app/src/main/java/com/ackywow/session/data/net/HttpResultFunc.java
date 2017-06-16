package com.ackywow.session.data.net;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
 *
 * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
 */
public class HttpResultFunc<T> implements Function<HttpResult<T>, T> {

  @Override
  public T apply(@NonNull HttpResult<T> tHttpResult) throws Exception {
    if (tHttpResult.getResultCode() != 0) {
      throw new ApiException(tHttpResult);
    }
    return tHttpResult.getData();
  }
}
