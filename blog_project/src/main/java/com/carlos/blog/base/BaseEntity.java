package com.carlos.blog.base;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Solrsky
 * @date 2018/12/4
 */
public class BaseEntity implements Serializable {

    // 版本序列号
    private static final long serialVersionUID = -8341777225186143756L;

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        // 如果类型相同
        if(obj.getClass() == this.getClass()){
            // 当前类反射得到方法组
            Method[] thisObjMethods = this.getClass().getMethods();
            try {
                for (Method thisObjMethod : thisObjMethods) {
                    // 过滤getClass()方法
                    if(thisObjMethod.getName().startsWith("get") && !thisObjMethod.getName().equals("getClass")){
                        // 将当前类属性的get方法的返回值与比较类属性的get方法的返回值比较
                        Method objCurrentMethod = obj.getClass().getMethod(thisObjMethod.getName());
                        // 执行方法获得值
                        // 比较对象返回的值
                        Object objReturnValue = objCurrentMethod.invoke(obj);
                        // 当前对象返回的值
                        Object thisReturnValue = objCurrentMethod.invoke(this);

                        //
                        if (objReturnValue == null && thisReturnValue != null) {
                            return false;
//                            System.err.println("异常信息：类" + obj.getClass().getName()
//                                    + "中的" + currentMethod.getName() + "方法为null值！无法进行对象比较！");
                        }
                        if (thisReturnValue == null && objReturnValue != null) {
                            return false;
//                            System.err.println("异常信息：类" + this.getClass().getName()
//                                    + "中的" + method.getName() + "方法为null值！无法进行对象比较！");
                        }

                        if(thisReturnValue != null && objReturnValue != null){
                            if(!objReturnValue.equals(thisReturnValue)){
                                return false;
                            }
                        }
                    }
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        // 生成简单的位运算hash散列码
        String key = this.toString();
        int prime = key.hashCode();
        int hash = prime;
        for (int i = 0; i < key.length(); i++) {
            hash ^= (hash << 23 >> 17) ^ key.charAt(i) * 13131;
        }
        // 返回结果
        return (hash % prime) * 33;
    }
}
