/**
 * @项目名：myBatis
 * @创建人： qupeng
 * @创建时间： 2019-08-01
 * @公司： www.qupeng.com
 * @描述：TODO
 */

package com.qupeng.mybatis.mapping;

/**
 * <p>NAME: MyEnvironment</p>
 * @author qupeng
 * @date 2019-08-01 21:20:15
 * @version 1.0
 */
//mybatis-config.xml里面的环境信息
public class MyEnvironment {
    private String driver;
    private String url;
    private String username;
    private String password;

    /**
     * 获取driver.
     *
     * @return driver.
     */
    public String getDriver() {
        return driver;
    }

    /**
     * 设置 driver.
     *
     * <p>You can use getDriver() to get the value of driver</p>
     *
     * @param driver driver
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * 获取url.
     *
     * @return url.
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置 url.
     *
     * <p>You can use getUrl() to get the value of url</p>
     *
     * @param url url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取username.
     *
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置 username.
     *
     * <p>You can use getUsername() to get the value of username</p>
     *
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取password.
     *
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置 password.
     *
     * <p>You can use getPassword() to get the value of password</p>
     *
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
