package com.zwwl.kotlintest.func

/**
 * 自定义高阶函数
 * Created by zhanghuipeng on 2022/11/17.
 * 参考：https://juejin.cn/post/7065982112767148068 Kotlin中的高阶函数、内置高阶函数和内联
 */

/**
 * ()->R
 * 定义一个高阶函数：
 * @param funcUnit 函数：无参返回值是 Unit
 * @param funcUser 函数：无参返回值是 User
 * @return Unit
 *
 * 该高阶函数 func1 转换为 Java 代码 如下：
 * public static final void func1(@NotNull Function0 funcUnit, @NotNull Function0 funcUser) {
Intrinsics.checkNotNullParameter(funcUnit, "funcUnit");
Intrinsics.checkNotNullParameter(funcUser, "funcUser");
funcUnit.invoke();
((User)funcUser.invoke()).printMessage();
}

 */
fun func1(funcUnit: () -> Unit, funcUser: () -> User) {
    funcUnit()
    funcUser().printMessage()
}

/**
 * (T...)->R
 * 定义一个高阶函数
 * @param funcUnit 函数：参数 user,返回 Unit
 * @param funcUser 函数：参数 user 和 afterYears,返回 String
 * @return Unit
 */
fun func2(funcUnit: (user: User) -> Unit, funcUser: (user: User, afterYears: Int) -> String) {
    val user = User("zhang", 30)
    funcUnit(user)
    val message = funcUser(user, 10)
    println(message)
}

/**
 * T.()->R ，T 的扩展函数
 * 定义一个高阶函数
 * @param funcUnit 函数：User 的扩展函数，和 funcUnit: (user: User) -> Unit 一样
 * @param funcUser 函数：User 的扩展函数，和 funcUser: (user: User, afterYears: Int) -> String 一样
 * @return Unit
 */
fun func3(funcUnit: User.() -> Unit, funcUser: User.(afterYears: Int) -> String) {
    val user = User("我", 30)

    val funcUnit = user.funcUnit()
//    val funcUnit = funcUnit(user)

    // 方式1：通过 T.funcUser(10) 访问
    val funcString = user.funcUser(10)
    // 方式2：通过 funcUser(T, 10) 访问
//    val funcString = funcUser(user, 10)
    println(funcUnit)
    println(funcString)
}

/**
 * 定义一个高阶函数
 * @param 无参
 * @return 函数：() -> Unit
 */
fun func4():() -> Unit {
    return fun() {
        println("高阶函数 - 返回值是函数的函数 - 年龄 ${User("我是谁", 18).age}")
    }
}

/**
 * 特殊：参数和返回值都是函数
 * 返回入参的函数
 * 如果 func 是内联函数，入参展开代码后，返回值 ？？？
 * 所以 func 不能是内联的，必须使用 noinline 修饰它，它就不再参与内联，而是使用匿名内部类的方式参与其中，可以返回了
 */
inline fun lineFun(num:Int, noinline func:() -> Unit): () -> Unit{
    return func
}
