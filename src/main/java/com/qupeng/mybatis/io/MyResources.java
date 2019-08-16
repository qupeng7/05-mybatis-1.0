/**
 * @项目名：myBatis
 * @创建人： qupeng
 * @创建时间： 2019-08-01
 * @公司： www.bjpowernode.com
 * @描述：TODO
 */

package com.qupeng.mybatis.io;

import java.io.InputStream;

/**
 * <p>NAME: MyResources</p>
 * @author qupeng
 * @date 2019-08-01 20:56:49
 * @version 1.0
 */

public class MyResources {
    public static InputStream getResourceAsStream(String resources){
        return ClassLoader.getSystemClassLoader().getResourceAsStream(resources);
    }
}
