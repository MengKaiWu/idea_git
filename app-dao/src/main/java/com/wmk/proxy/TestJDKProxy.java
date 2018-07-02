package com.wmk.proxy;

import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestJDKProxy {
    @Test
    public void test1() throws Exception{
        //生成所需添加辅助功能的类的byte数组
        byte[] classBytes= ProxyGenerator.generateProxyClass("com.wmk.proxy.UserServiceProxy",new Class[] {UserService.class});
        //拿到类加载器，用于生成所需文件
        ClassLoader l1 = TestJDKProxy.class.getClassLoader();
        //加载classBytes所代表的文件
        Class superClass = l1.getClass().getSuperclass().getSuperclass().getSuperclass();
        //获得ClassLoader类中的defineClass方法
        Method method = superClass.getDeclaredMethod("defineClass",String.class,byte[].class,int.class,int.class);
        //可以拿到私有方法
        method.setAccessible(true);
        //调用defineClass方法，加载ClassBytes，生成Class对象，保存在JVM中
        Class ProxyClass = (Class)method.invoke(l1,null,classBytes,0,classBytes.length);
        //通过代理类的类对象获取有参的构造方法
        Constructor cons= ProxyClass.getConstructor(InvocationHandler.class);
        //
        final UserServiceImpl us = new UserServiceImpl();
        //添加额外的辅助功能
        InvocationHandler ih = new InvocationHandler(){

            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("----Invok start----");
                Object ret = method.invoke(us,args);
                System.out.println("----Invok end----");
                return ret;
            }
        };
        //创建代理类的实例对象
        UserService u= (UserService) cons.newInstance(ih);
        //调用代理类的方法
        u.register(new User());
    }
    @Test
    public void test2() {

        final UserService us = new UserServiceImpl();

        InvocationHandler ih = new InvocationHandler() {

            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                System.out.println("---start---");

                Object ret = method.invoke(us, args);

                System.out.println("---end---");

                return ret;
            }
        };

        UserService usProxy = (UserService) Proxy.newProxyInstance(
                TestJDKProxy.class.getClassLoader(),
                new Class[] { UserService.class }, ih);

        usProxy.register(new User());
    }
}
