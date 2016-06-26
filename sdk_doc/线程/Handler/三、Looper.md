## 三、Looper
Looper扮演的是处理消息循环的角色。  
构造方法：
``` java
private Looper(boolean quitAllowed) {
    mQueue = new MessageQueue(quitAllowed);
    mThread = Thread.currentThread();
}
```
创建Handler之前要为当前线程创建Looper，通过下面的方法：
``` java
private static void prepare(boolean quitAllowed) {
    if (sThreadLocal.get() != null) {
        throw new RuntimeException("Only one Looper may be created per thread");
    }
    sThreadLocal.set(new Looper(quitAllowed));
}
```
Looper除了prepare方法外，还提供了prepareMainLooper方法，这个方法是给ActivityThread创建Looper用的，其本质就是通过prepare方法。另外，Looper提供了getMainLooper方法来获取主线程的Looper。  
Looper退出的时候，有quit和quilSafely来退出，区别是：quit会直接退出Looper，而quitSafely是把消息队列中延迟消息全部取消，不延迟消息还是交给Hanlder来执行后，Lopper才退出。代码如下：
``` java
/**
 * Quits the looper safely.
 * <p>
 * Causes the {@link #loop} method to terminate as soon as all remaining messages
 * in the message queue that are already due to be delivered have been handled.
 * However pending delayed messages with due times in the future will not be
 * delivered before the loop terminates.
 * </p><p>
 * Any attempt to post messages to the queue after the looper is asked to quit will fail.
 * For example, the {@link Handler#sendMessage(Message)} method will return false.
 * </p>
 */
public void quitSafely() {
    mQueue.quit(true);
}

void quit(boolean safe) {
    if (!mQuitAllowed) {
        throw new IllegalStateException("Main thread not allowed to quit.");
    }

    synchronized (this) {
        if (mQuitting) {
            return;
        }
        mQuitting = true;

        if (safe) {
            removeAllFutureMessagesLocked();
        } else {
            removeAllMessagesLocked();
        }

        // We can assume mPtr != 0 because mQuitting was previously false.
        nativeWake(mPtr); //唤醒Looper去执行，直接退出或者先执行完剩下的非延迟消息
    }
}

private void removeAllFutureMessagesLocked() {
    final long now = SystemClock.uptimeMillis();
    Message p = mMessages;
    if (p != null) {
        if (p.when > now) {
            removeAllMessagesLocked();
        } else {
            Message n;
            for (;;) { //for循环找到延迟消息
                n = p.next;
                if (n == null) {
                    return;
                }
                if (n.when > now) {
                    break;
                }
                p = n;
            }
            p.next = null; //清楚引用
            do { //清除延迟消息
                p = n;
                n = p.next;
                p.recycleUnchecked();
            } while (n != null);
        }
    }
}
```
Looper退出后，Handler的send方法会返回false。子线程中，如果手动创建了Looper，在所有事情处理完成后，应先调用quit方法来终止消息循环，否则子线程会一直处于等待状态。  
Looper最重要的方法就是loop方法，只有调用loop方法后，消息循环才会真正开始：
``` java
/**
 * Run the message queue in this thread. Be sure to call
 * {@link #quit()} to end the loop.
 */
public static void loop() {
    final Looper me = myLooper();
    if (me == null) {
        throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
    }
    final MessageQueue queue = me.mQueue;

    // Make sure the identity of this thread is that of the local process,
    // and keep track of what that identity token actually is.
    Binder.clearCallingIdentity();
    final long ident = Binder.clearCallingIdentity();

    for (;;) {
        Message msg = queue.next(); // might block 这里其实是MessageQueue的阻塞方法
        if (msg == null) {
            // No message indicates that the message queue is quitting.
            return;
        }

        // This must be in a local variable, in case a UI event sets the logger
        Printer logging = me.mLogging;
        if (logging != null) {
            logging.println(">>>>> Dispatching to " + msg.target + " " +
                    msg.callback + ": " + msg.what);
        }

        msg.target.dispatchMessage(msg); //这里的target就是Handler

        if (logging != null) {
            logging.println("<<<<< Finished to " + msg.target + " " + msg.callback);
        }

        // Make sure that during the course of dispatching the
        // identity of the thread wasn't corrupted.
        final long newIdent = Binder.clearCallingIdentity();
        if (ident != newIdent) {
            Log.wtf(TAG, "Thread identity changed from 0x"
                    + Long.toHexString(ident) + " to 0x"
                    + Long.toHexString(newIdent) + " while dispatching to "
                    + msg.target.getClass().getName() + " "
                    + msg.callback + " what=" + msg.what);
        }

        msg.recycleUnchecked();
    }
}
```
loop方法是一个死循环，只有当MessageQueue的next方法返回了null时，才会跳出循环。也就是Looper必须quit，Looper才会停止循环退出。  

如果MessageQueue的next方法返回了新消息，Looper就会处理这条消息：  
&emsp;&emsp;msg.target.dispatchMessage(msg)，这里的msg.target就是发送这条消息的Handler对象，这样Handler发送的消息最终又交给它的dispatchMessage方法来处理，不同的是，这个dispatchMessage方法是在创建Handler时所用的Looper中执行的，这样就跨线程执行了。
