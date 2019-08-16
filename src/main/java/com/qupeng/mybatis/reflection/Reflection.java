/**
 * @项目名：myBatis
 * @创建人： qupeng
 * @创建时间： 2019-08-05
 * @公司： www.bjpowernode.com
 * @描述：TODO
 */

package com.qupeng.mybatis.reflection;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

/**
 * <p>NAME: Reflection</p>
 * @author qupeng
 * @date 2019-08-05 09:52:25
 * @version 1.0
 */

public class Reflection {

    /**将ResultSet结果集数据设置到Java对象的属性中
     *
     * @param entity
     * @param resultSet
     */
    public static void setPropertiesToBeanFromResultSet(Object entity, ResultSet resultSet) {

        try {
            //根据结果集，获取到数据库表的元数据信息，这个信息里面有很详细的表的列、字段的信息
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            //字段的个数
            int columnCount = resultSetMetaData.getColumnCount();
            //获取到userInfo、orderInfo ...对象所声明的成员变量
            Field[] fieldArray = entity.getClass().getDeclaredFields();
            //循环数据库字段
            for(int i=0; i<columnCount; i++){
                //数据库表的字段的名字：id，user_name，phone，login_password ........
                String columnName = resultSetMetaData.getColumnName(i+1).replace("_", "");
                //循环对象的成员变量
                for (int ii=0; ii<fieldArray.length; ii++) {
                    //对象中的成员变量的名称，id;  userName;  phone;  loginPassword;
                    String fieldName=fieldArray[ii].getName();
                    //数据表的字段的名字 和 对象的成员变量的名字，忽略大小写后是否相等
                    if(columnName.equalsIgnoreCase(fieldName)){
                        //根据成员变量的名字，拿到代表该成员变量的反射对象Field
                        Field field = entity.getClass().getDeclaredField(fieldName);
                        //允许对该字段进行访问
                        field.setAccessible(true);

                        //把数据库该字段的字设置到entity对象(userinfo\orderinfo)中
                        if (field.getType().getSimpleName().equals("Integer")) {
                            //获取数据库字段的值
                            Integer columnValue = resultSet.getInt(resultSetMetaData.getColumnName(i+1));
                            //把columnValue这个数据库的值，设置到entity对象的field成员变量中
                            field.set(entity, columnValue);

                        } else if (field.getType().getSimpleName().equals("Long")) {
                            //获取数据库字段的值
                            Long columnValue = resultSet.getLong(resultSetMetaData.getColumnName(i+1));
                            //把columnValue这个数据库的值，设置到entity对象的field成员变量中
                            field.set(entity, columnValue);

                        } else if (field.getType().getSimpleName().equals("Double")) {
                            //获取数据库字段的值
                            Double columnValue = resultSet.getDouble(resultSetMetaData.getColumnName(i+1));
                            //把columnValue这个数据库的值，设置到entity对象的field成员变量中
                            field.set(entity, columnValue);

                        } else if (field.getType().getSimpleName().equals("String")) {
                            //获取数据库字段的值
                            String columnValue = resultSet.getString(resultSetMetaData.getColumnName(i+1));
                            //把columnValue这个数据库的值，设置到entity对象的field成员变量中
                            field.set(entity, columnValue);

                        } else if (field.getType().getSimpleName().equals("Date")) {
                            //获取数据库字段的值
                            Date columnValue = resultSet.getDate(resultSetMetaData.getColumnName(i+1));
                            //把columnValue这个数据库的值，设置到entity对象的field成员变量中
                            field.set(entity, columnValue);

                        } else if (field.getType().getSimpleName().equals("Boolean")) {
                            //获取数据库字段的值
                            Boolean columnValue = resultSet.getBoolean(resultSetMetaData.getColumnName(i+1));
                            //把columnValue这个数据库的值，设置到entity对象的field成员变量中
                            field.set(entity, columnValue);
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
