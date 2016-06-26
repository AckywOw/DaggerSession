## 四、Handler
Handler的工作是发送和接受消息。发送可以通过post和send方法来实现，post最终也是通过send的一系列方法：
``` java
public final boolean sendEmptyMessage(int what) {
    return sendEmptyMessageDelayed(what, 0);
}

public final boolean sendEmptyMessageDelayed(int what, long delayMillis) {
    Message msg = Message.obtain();
    msg.what = what;
    return sendMessageDelayed(msg, delayMillis);
}

public final boolean sendEmptyMessageAtTime(int what, long uptimeMillis) {
    Message msg = Message.obtain();
    msg.what = what;
    return sendMessageAtTime(msg, uptimeMillis);
}

public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
    MessageQueue queue = mQueue;
    if (queue == null) {
        RuntimeException e = new RuntimeException(
                this + " sendMessageAtTime() called with no mQueue");
        Log.w("Looper", e.getMessage(), e);
        return false;
    }
    return enqueueMessage(queue, msg, uptimeMillis);
}
```
可以发现，Handler发送消息的过程仅仅是向消息队列MessageQueue中插入了一条消息。  

Handler处理消息的方法dispatchMessag的实现如下：
``` java
/**
 * Handle system messages here.
 */
public void dispatchMessage(Message msg) {
    if (msg.callback != null) { //先判断msg是否有Handler来post的Runnable
        handleCallback(msg);
    } else {
        if (mCallback != null) { //这里的mCallback是Handler构造方法传入的，用来同意处理msg的
            if (mCallback.handleMessage(msg)) {
                return;
            }
        }
        handleMessage(msg);
    }
}

private static void handleCallback(Message message) {
    message.callback.run();
}

/**
 * Callback interface you can use when instantiating a Handler to avoid
 * having to implement your own subclass of Handler.
 *
 * @param msg A {@link android.os.Message Message} object
 * @return True if no further handling is desired
 */
public interface Callback { //用了这个就不需要Handler创建子类了
    public boolean handleMessage(Message msg);
}
```
Handler有个特殊方法，通过一个特定的Looper来构造个Handler，这个Handler发送的消息就在传入的这个Looper绑定的线程中执行了：
``` java
/**
 * Use the provided {@link Looper} instead of the default one.
 *
 * @param looper The looper, must not be null.
 */
public Handler(Looper looper) {
    this(looper, null, false);
}
```
Handler默认构造方法：
``` java
/**
 * Use the {@link Looper} for the current thread with the specified callback interface
 * and set whether the handler should be asynchronous.
 *
 * Handlers are synchronous by default unless this constructor is used to make
 * one that is strictly asynchronous.
 *
 * Asynchronous messages represent interrupts or events that do not require global ordering
 * with respect to synchronous messages.  Asynchronous messages are not subject to
 * the synchronization barriers introduced by {@link MessageQueue#enqueueSyncBarrier(long)}.
 *
 * @param callback The callback interface in which to handle messages, or null.
 * @param async If true, the handler calls {@link Message#setAsynchronous(boolean)} for
 * each {@link Message} that is sent to it or {@link Runnable} that is posted to it.
 *
 * @hide
 */
public Handler(Callback callback, boolean async) {
    if (FIND_POTENTIAL_LEAKS) {
        final Class<? extends Handler> klass = getClass();
        if ((klass.isAnonymousClass() || klass.isMemberClass() || klass.isLocalClass()) &&
                (klass.getModifiers() & Modifier.STATIC) == 0) {
            Log.w(TAG, "The following Handler class should be static or leaks might occur: " +
                klass.getCanonicalName());
        }
    }

    mLooper = Looper.myLooper();
    if (mLooper == null) {
        throw new RuntimeException(
            "Can't create handler inside thread that has not called Looper.prepare()");
    }
    mQueue = mLooper.mQueue;
    mCallback = callback;
    mAsynchronous = async;
}
```
