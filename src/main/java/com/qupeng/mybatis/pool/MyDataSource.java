/**
 * @项目名：myBatis
 * @创建人： qupeng
 * @创建时间： 2019-08-01
 * @公司： www.bjpowernode.com
 * @描述：TODO
 */

package com.qupeng.mybatis.pool;

import com.qupeng.mybatis.mapping.MyEnvironment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>NAME: MyDataSource</p>
 * @author qupeng
 * @date 2019-08-01 22:53:53
 * @version 1.0
 */
// 数据源连接池
public class MyDataSource  implements   MyDataSourceInterface {
    private static MyDataSource instance;
    //连接池的大小是30个连接
    private static final int POOL_SIZE=30;

    private MyEnvironment myEnvironment;

    private List<Connection> pool;

    private Connection connection;

    private MyDataSource(MyEnvironment myEnvironment){
        this.myEnvironment=myEnvironment;
        this.pool = new ArrayList<Connection>(POOL_SIZE);
        this.initConnection();
    }
    public static MyDataSource getInstance(MyEnvironment myEnvironment) {
        if (instance == null) {
            instance = new MyDataSource(myEnvironment);
        }
        return instance;
    }
    /**
     * 创建数据源连接池:初始化30个
     */
    public void initConnection() {
        try {
            Class.forName(myEnvironment.getDriver());
            for (int i=0; i<POOL_SIZE; i++) {
                connection = DriverManager.getConnection(myEnvironment.getUrl(), myEnvironment.getUsername(), myEnvironment.getPassword());
                pool.add(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public synchronized  Connection getConnection() throws SQLException {
        if(pool.size()>0){
            Connection connection=pool.get(0);
            pool.remove(connection);
            return connection;
        }
        return null;
    }

    /**
     * 释放连接池的连接
     *
     * @return
     * @throws SQLException
     */
    public synchronized void releaseConnection(Connection connection) {
        pool.add(connection);
    }
}
