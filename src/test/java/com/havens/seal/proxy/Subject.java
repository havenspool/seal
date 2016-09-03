package com.havens.seal.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @desc: TODO
 * @author: havens
 * @date: 2016/8/25 11:49
 */
@Document
public class Subject implements ISubject,MethodInterceptor {
    private transient long playerId;
    private String playerName;
    private Class<?> subjectClass;
    private ISubject proxySubject;

    public long getPlayerId() { return playerId; }
    public void setPlayerId(long playerId) { this.playerId = playerId; }
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
    public Class<?> getSubjectClass() { return subjectClass; }
    public void setSubjectClass(Class<?> subjectClass) { this.subjectClass = subjectClass; }
    public ISubject getProxySubject() { return proxySubject; }
    public void setProxySubject(ISubject proxySubject) { this.proxySubject = proxySubject; }


    public void createProxy(Object... key) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.subjectClass);// 设置代理目标
        enhancer.setCallback(this); // 设置回调
        enhancer.setClassLoader(this.subjectClass.getClassLoader());
        if (key.length == 0)
            proxySubject = (ISubject) enhancer.create();
        else
            proxySubject = (ISubject) enhancer.create(
                    new Class[]{getKeyType(this.getSubjectClass())},
                    key);
    }

    public static Class<?> getKeyType(Class<?> clazz){
//        Table table = clazz.getAnnotation(Table.class);
//        try {
//            String keyStr = table.keyField();
//            if (keyStr.isEmpty() && table.isPlayerIdKey()){
//                keyStr = "id";
//            }
//            Field field = clazz.getDeclaredField(keyStr);
//            Class<?> type = field.getType();
//            return type;
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    @Override
    public void action() {
        System.out.println("Subject:action");
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object result;
        // 调用原始对象的方法
        result = methodProxy.invokeSuper(o, args);

        String methodStr = method.getName();
        // 调用之后
        return result;
    }
}
