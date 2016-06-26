## 一、ThreadLocal
&emsp;&emsp;一般来说，当某些数据是以线程为作用域并且不同线程具有不同的数据副本的时候，就可以考虑采用ThreadLocal。不同线程访问同一个ThreadLocal对象时，他们通过ThreadLocal获取的值不一样。下面是ThreadLocal的关键代码：

ThreadLocal的set方法：
``` java
/**
 * Sets the value of this variable for the current thread. If set to
 * {@code null}, the value will be set to null and the underlying entry will
 * still be present.
 *
 * @param value the new value of the variable for the caller thread.
 */
public void set(T value) {
    Thread currentThread = Thread.currentThread();
    Values values = values(currentThread);
    if (values == null) {
        values = initializeValues(currentThread); //初始化value new Valuse()
    }
    values.put(this, value);
}  
```
ThreadLocal的value方法：
``` java
/**
 * Gets Values instance for this thread and variable type.
 */
Values values(Thread current) {
    return current.localValues;
}
```  
这里是因为Thread类内部有个成员变量专门用于存储线程的ThreadLocal的数据：  
``` java
/**
 * Normal thread local values.
 */
ThreadLocal.Values localValues;
```
ThreadLocal的get方法：
``` java
/**
 * Returns the value of this variable for the current thread. If an entry
 * doesn't yet exist for this variable on this thread, this method will
 * create an entry, populating the value with the result of
 * {@link #initialValue()}.
 *
 * @return the current value of the variable for the calling thread.
 */
@SuppressWarnings("unchecked")
public T get() {
    // Optimized for the fast path.
    Thread currentThread = Thread.currentThread();
    Values values = values(currentThread);
    if (values != null) {
        Object[] table = values.table;
        int index = hash & values.mask;
        if (this.reference == table[index]) {
            return (T) table[index + 1];
        }
    } else {
        values = initializeValues(currentThread);
    }

    return (T) values.getAfterMiss(this);
}
```
