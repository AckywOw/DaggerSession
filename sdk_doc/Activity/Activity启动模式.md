# Activit启动模式

[TOC]

### 一.栈
&emsp;&emsp;Android的管理主要是通过Activity栈来进行，后进先出(Last In First Out)。

### 二. LaunchMode
1. standard 标准模式  
注意：非Activity的Context启动Activity时，需要带上Intent.FLAG_ACTIVITY_NEW_TASK，这就会创建一个新的栈，实际上是以singleTask模式启动的。
2. singleTop 栈顶复用模式  
此时复用时，Activity的onNewIntent方法会被调用。
3. singleTask 栈内复用模式  
这是一种单实例模式。  
如果此Activity需要的栈不存在(参考[TaskAffinity](#三-taskaffinity))，就同时创建需要的栈和此Activity；  
如果存在需要的栈，并且其中已经存在此Activity的实例，就复用它，和singleTop一样，调用其onNewIntent方法，并清空栈中其上面所有的Activity，就是clearTop。
4. singleInstance 单实例模式  
此种模式的Activity只能单独的位于一个栈中。

### 三. TaskAffinity
&emsp;&emsp;此参数标识了一个Activity所需要的栈的名字。  
&emsp;&emsp;默认情况下，此属性就是应用包名。
### 四. Flags
1. FLAG_ACTIVITY_NEW_TASK  
类似singleTask，却没有clearTop的性质
2. FLAG_ACTIVITY_SINGLE_TOP  
singleTop
3. FLAG_ACTIVITY_CLEAR_TOP  
需要与FLAG_ACTIVITY_NEW_TASK结合使用，清空同一栈内上面所有的Activity
4. FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS  
具有此标记的Activity不会出现在历史Activity列表中，等同于manifest中android:excludeFromRecents="true"。
5. FLAG_ACTIVITY_CLEAR_TASK  
此flag尽量不用，会影响系统对app默认activity的判断，在应用不正常退出后，在历史Activity列表打开app(6.0以后貌似已经修复)，此activity会被表示成默认activity。

### 五. IntentFilter
&emsp;&emsp;有两种启动Activity的模式，显示调用和隐式调用，显示优先级大。  
&emsp;&emsp;IntentFilter就是用来隐式调用的，一个Activity可以有多个IntentFilter,每个IntentFilter由action、category和data组成，一个Intent只要能匹配任意一组IntentFilter，就能成功启动Activity。  
1. action  
系统预定义了一些action，我们也可以自定义action。  
匹配要求：只要Intent中有action存在(可以有多个)并且和IntentFilter中的其中一个action相同(区分大小写)。
2. category  
系统预定义了一些category，我们也可以自定义category。  
Intent中可以没有category，但如果有，不管有几个，每个category都必须与IntentFilter中的一个相同。
如果不设置category，因为系统在startActivity时，会默认为Intent加上“android.intent.category.DEFAULT”这个category。，因此，为了我们自己的Activity能够被隐式调用，必须在IntentFilter中加上这个category。
3. data  
        待补充


&emsp;可以用以下两种方法,来判断Intent是否匹配IntentFilter：
``` java
PackageManager：//找不到就返回null
public abstract ResolveInfo resolveActivityAsUser(Intent intent, int flags, int userId);

Intent：//找不到就返回null
public ComponentName resolveActivity(PackageManager pm)

PackageManager：//返回所有匹成功配的Activity信息
public abstract List<ResolveInfo> queryIntentActivities(Intent intent, int flags);
```
