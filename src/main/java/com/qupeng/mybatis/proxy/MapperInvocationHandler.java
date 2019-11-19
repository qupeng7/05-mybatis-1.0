/**
 * @项目名：myBatis
 * @创建人： qupeng
 * @创建时间： 2019-08-02
 * @公司： www.qupeng.com
 * @描述：TODO
 */

package com.qupeng.mybatis.proxy;

import com.qupeng.mybatis.session.MySqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>NAME: MapperInvocationHandler</p>
 * @author qupeng
 * @date 2019-08-02 14:48:41
 * @version 1.0
 */

public class MapperInvocationHandler implements InvocationHandler {

    private MySqlSession mySqlSession;

    public MapperInvocationHandler(MySqlSession mySqlSession) {
        this.mySqlSession = mySqlSession;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //当你调用Mapper接口里面的方法对数据库进行操作的时候，就会被该invoke所拦截
        //我们目前只写了一个查询操作，（应该需要对insert、delete、update、select四种操作进行判断）
        //怎么判断是insert、delete、update、select，就是在我们读取mapper.xml文件的时候，
        //就根据xml节点，把每个sql语句关联一个操作类型（insert、delete、update、select）
        String statementKey = method.getDeclaringClass().getTypeName() + "." + method.getName();
        Object parameter = args == null ? null : args[0];
        return  mySqlSession.selectOne(parameter,statementKey);
    }
}
