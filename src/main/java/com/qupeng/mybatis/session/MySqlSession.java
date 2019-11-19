/**
 * @项目名：myBatis
 * @创建人： qupeng
 * @创建时间： 2019-08-01
 * @公司： www.qupeng.com
 * @描述：TODO
 */

package com.qupeng.mybatis.session;

import com.qupeng.mybatis.executor.MyExecutor;
import com.qupeng.mybatis.mapping.MyConfiguration;
import com.qupeng.mybatis.mapping.MyMapperStatement;
import com.qupeng.mybatis.proxy.MapperInvocationHandler;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * <p>NAME: MySqlSession</p>
 * @author qupeng
 * @date 2019-08-01 22:48:06
 * @version 1.0
 */

public class MySqlSession {

    private MyConfiguration myConfiguration;

    private MyExecutor myExecutor;

    public MySqlSession(MyExecutor myExecutor,MyConfiguration myConfiguration) {
        this.myConfiguration=myConfiguration;
        this.myExecutor = myExecutor;
    }
    /**
     * 泛型方法
     *
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz) {
        //jdk动态代理
        MapperInvocationHandler mapperInvocationHandler = new MapperInvocationHandler(this);
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(),new Class<?>[] {clazz} , mapperInvocationHandler);
    }

    public  <T> T selectOne(Object parameter,String statementKey){
        //MyMapperStatement是mapper.xml的其中一条操作语句的封装
        MyMapperStatement myMapperStatement = myConfiguration.getMyMapperStatementMap().get(statementKey);
        List<T> list = myExecutor.query(parameter, myMapperStatement);
        if (list.size() == 1) {
            return list.get(0);
        } else if (list.size() > 1) {
            throw new RuntimeException("Expected one result (or null) to be returned by selectOne(), but found: " + list.size());
        } else {
            return null;
        }
    }
}
