/**
 * @项目名：myBatis
 * @创建人： qupeng
 * @创建时间： 2019-08-01
 * @公司： www.qupeng.com
 * @描述：TODO
 */

package com.qupeng.mybatis.session;

import com.qupeng.mybatis.mapping.MyConfiguration;
import com.qupeng.mybatis.parsing.MyXMLConfigBuilder;

import java.io.InputStream;

/**
 * <p>NAME: MySqlSessionFactoryBuilder</p>
 * @author qupeng
 * @date 2019-08-01 20:59:43
 * @version 1.0
 */

public class MySqlSessionFactoryBuilder {

    public MySqlSessionFactory build(InputStream inputStream){
        MyXMLConfigBuilder xmlCondigBuilder=new MyXMLConfigBuilder(inputStream);
        MyConfiguration myConfiguration = xmlCondigBuilder.parse();
        return new MySqlSessionFactory(myConfiguration);
    }
}
