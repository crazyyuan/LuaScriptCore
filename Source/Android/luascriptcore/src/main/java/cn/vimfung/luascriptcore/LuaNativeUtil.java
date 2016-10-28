package cn.vimfung.luascriptcore;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.vimfung.luascriptcore.modules.oo.LuaObjectClass;

/**
 * 本地接口工具类
 * Created by vimfung on 16/8/29.
 */
public class LuaNativeUtil
{
    static
    {
        System.loadLibrary("LuaScriptCore");
    }

    private LuaNativeUtil()
    {
        throw new Error("Not allowed to instantiate the class");
    }

    /**
     * 创建Lua上下文对象
     */
    public static native LuaContext createContext ();

    /**
     * 添加搜索路径, 对于需要引用不同目录下的lua文件,需要设置其搜索路径,否则会导致无法找到脚本而运行出错
     * @param contextNativeId 上下文的本地标识
     * @param path  路径
     */
    public static native void addSearchPath(int contextNativeId, String path);

    /**
     * 解析Lua脚本
     * @param contextNativeId   上下文的本地标识
     * @param script            Lua脚本
     * @return                  返回值
     */
    public static native LuaValue evalScript (int contextNativeId, String script);

    /**
     * 解析Lua脚本文件
     * @param contextNativeId   上下文的本地标识
     * @param path              Lua脚本文件路径
     * @return                  返回值
     */
    public static native LuaValue evalScriptFromFile (int contextNativeId, String path);

    /**
     * 执行Lua方法
     * @param contextNativeId   上下文的本地标识
     * @param methodName        方法名称
     * @param arguments         方法参数列表
     * @return                  返回值
     */
    public static native LuaValue callMethod (int contextNativeId, String methodName, LuaValue[] arguments);

    /**
     * 注册Lua方法
     * @param contextNativeId   上下文的本地标识
     * @param methodName        方法名称
     */
    public static native void registerMethod (int contextNativeId, String methodName);

    /**
     * 释放本地对象
     * @param nativeId  本地对象标识
     */
    public static native void releaseNativeObject (int nativeId);

    /**
     * 注册模块
     * @param contextNativeId 上下文的本地标识
     * @param moduleClass    模块类
     * @param fields 注册的字段
     * @param methods 注册的方法
     * @return  模块对象
     */
    public static native LuaModule registerModule(
            int contextNativeId,
            String moduleName,
            Class<? extends LuaModule> moduleClass,
            Field[] fields,
            Method[] methods
    );

    /**
     * 判断模块是否注册
     * @param contextNativeId   上下文的本地标识
     * @param moduleName    模块名称
     * @return  true 已注册,否则,未注册。
     */
    public static native boolean isModuleRegisted(int contextNativeId, String moduleName);

    /**
     * 注册类型
     * @param context   上下文对象
     * @param className 类名称
     * @param superClassName    父类型名称
     * @param objectClass   类型
     * @param fields    字段集合
     * @param methods   方法集合
     * @return  类型对象
     */
    public static native LuaObjectClass registerClass (
            LuaContext context,
            String className,
            String superClassName,
            Class<? extends LuaObjectClass> objectClass,
            Field[] fields,
            Method[] methods);

}