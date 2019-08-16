/**
 * @项目名：myBatis
 * @创建人： qupeng
 * @创建时间： 2019-08-01
 * @公司： www.bjpowernode.com
 * @描述：TODO
 */

package com.qupeng.mybatis.executor;

import com.qupeng.mybatis.mapping.MyConfiguration;
import com.qupeng.mybatis.mapping.MyMapperStatement;
import com.qupeng.mybatis.parsing.SQLTokenParser;
import com.qupeng.mybatis.pool.MyDataSource;
import com.qupeng.mybatis.reflection.Reflection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>NAME: MyExecutor</p>
 * @author qupeng
 * @date 2019-08-01 22:51:33
 * @version 1.0
 */

public class MyExecutor {


    private MyDataSource myDataSource;

    public MyExecutor(MyConfiguration myConfiguration) {
        myDataSource = MyDataSource.getInstance(myConfiguration.getMyEnvironment());
    }
    @SuppressWarnings("unchecked")
    public <T>  List<T> query(Object parameter, MyMapperStatement myMapperStatement){
        List<T> resultList= new ArrayList<T>();

        //封装jdbc代码 返回结果
        ResultSet resultSet=null;
        Connection connection=null;
        PreparedStatement preparedStatement = null;
        try {
            connection=myDataSource.getConnection();
            //jdbc预编译sql语句
            String sql = SQLTokenParser.parse(myMapperStatement.getSql());
            preparedStatement = connection.prepareStatement(sql);
            //for 多个参数
            //设置参数
            if (parameter instanceof Integer) {
                preparedStatement.setInt(1, (Integer) parameter);
            } else if (parameter instanceof Double) {
                preparedStatement.setDouble(1, (Double) parameter);
            } else if (parameter instanceof Long) {
                preparedStatement.setLong(1, (Long) parameter);
            } else if (parameter instanceof String) {
                preparedStatement.setString(1, (String) parameter);
            }
            //执行操作返回结果
            resultSet = preparedStatement.executeQuery();

            //将数据库结果resultSet映射到java对象中
            handlerResultSet(resultSet, resultList, myMapperStatement.getResultType());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                //关闭连接，此处不是真的关闭连接，而是把连接归还到连接池中
                myDataSource.releaseConnection(connection);
            }
        }
        return resultList;
    }
    /**
     * 对数据库ResultSet结果集进行处理，映射为Java对象
     *
     * @param resultSet
     * @param resultList
     * @param resultType
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public <T> void handlerResultSet(ResultSet resultSet, List<T> resultList, String resultType) {
        //resultType : com.bjpowernode.model.UserInfo , OrderInfo
        try {
            Class<T> clazz = (Class<T>) Class.forName(resultType);
                while(resultSet.next()){
                    Object entity =clazz.newInstance();
                    Reflection.setPropertiesToBeanFromResultSet(entity, resultSet);
                    //把设置好了的java对象放入到最终的结果List中
                    resultList.add((T)entity);
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
