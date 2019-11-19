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

/**
 * <p>NAME: MySqlSessionFactory</p>
 * @author qupeng
 * @date 2019-08-01 20:59:29
 * @version 1.0
 */

public class MySqlSessionFactory {

    private MyConfiguration myConfiguration ;

    public MySqlSessionFactory(MyConfiguration myConfiguration) {
        this.myConfiguration=myConfiguration;
    }

    public MySqlSession openMySqlSession (){
        MyExecutor myExecutor=new MyExecutor(myConfiguration);
        return new MySqlSession(myExecutor,myConfiguration);
    }
}
