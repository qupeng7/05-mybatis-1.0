/**
 * @项目名：myBatis
 * @创建人： qupeng
 * @创建时间： 2019-08-01
 * @公司： www.bjpowernode.com
 * @描述：TODO
 */

package com.qupeng.mybatis.mapping;

import java.util.Map;

/**
 * 配置文件的信息全部封装到该对象中
 * @author qupeng
 * @date 2019-08-01 21:17:25
 * @version 1.0
 */

public class MyConfiguration {
    //mybatis-config.xml
    private MyEnvironment myEnvironment;

    //XXXMapper.xml
    private Map<String, MyMapperStatement> myMapperStatementMap;

    /**
     * 获取myEnvironment.
     *
     * @return myEnvironment.
     */
    public MyEnvironment getMyEnvironment() {
        return myEnvironment;
    }

    /**
     * 设置 myEnvironment.
     *
     * <p>You can use getMyEnvironment() to get the value of myEnvironment</p>
     *
     * @param myEnvironment myEnvironment
     */
    public void setMyEnvironment(MyEnvironment myEnvironment) {
        this.myEnvironment = myEnvironment;
    }

    /**
     * 获取myMapperStatementMap.
     *
     * @return myMapperStatementMap.
     */
    public Map<String, MyMapperStatement> getMyMapperStatementMap() {
        return myMapperStatementMap;
    }

    /**
     * 设置 myMapperStatementMap.
     *
     * <p>You can use getMyMapperStatementMap() to get the value of myMapperStatementMap</p>
     *
     * @param myMapperStatementMap myMapperStatementMap
     */
    public void setMyMapperStatementMap(Map<String, MyMapperStatement> myMapperStatementMap) {
        this.myMapperStatementMap = myMapperStatementMap;
    }
}
