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
如果此Activity需要的栈不存在(参考[TaskAffinity](#jump))，就同时创建需要的栈和此Activity；  
如果存在需要的栈，并且其中已经存在此Activity的实例，就复用它，和singleTop一样，调用其onNewIntent方法，并清空栈中其上面所有的Activity，就是clearTop。
4. singleInstance 单实例模式  
此种模式的Activity只能单独的位于一个栈中。

### 三. TaskAffinity<span id="jump"/>
&emsp;&emsp;此参数标识了一个Activity所需要的栈的名字。  
&emsp;&emsp;默认情况下，此属性就是应用包名。
