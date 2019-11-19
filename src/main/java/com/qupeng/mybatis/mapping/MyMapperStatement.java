/**
 * @项目名：myBatis
 * @创建人： qupeng
 * @创建时间： 2019-08-01
 * @公司： www.qupeng.com
 * @描述：TODO
 */

package com.qupeng.mybatis.mapping;

/**
 * <p>NAME: MyMapperStatement</p>
 * @author qupeng
 * @date 2019-08-01 21:20:42
 * @version 1.0
 */
//XXXMapper.xml
public class MyMapperStatement {

    private String namespace;

    private String sqlId;

    private String parameterType;

    private String resultType;

    private String sql;

    /**
     * 获取namespace.
     *
     * @return namespace.
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * 设置 namespace.
     *
     * <p>You can use getNamespace() to get the value of namespace</p>
     *
     * @param namespace namespace
     */
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    /**
     * 获取sqlId.
     *
     * @return sqlId.
     */
    public String getSqlId() {
        return sqlId;
    }

    /**
     * 设置 sqlId.
     *
     * <p>You can use getSqlId() to get the value of sqlId</p>
     *
     * @param sqlId sqlId
     */
    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    /**
     * 获取parameterType.
     *
     * @return parameterType.
     */
    public String getParameterType() {
        return parameterType;
    }

    /**
     * 设置 parameterType.
     *
     * <p>You can use getParameterType() to get the value of parameterType</p>
     *
     * @param parameterType parameterType
     */
    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    /**
     * 获取resultType.
     *
     * @return resultType.
     */
    public String getResultType() {
        return resultType;
    }

    /**
     * 设置 resultType.
     *
     * <p>You can use getResultType() to get the value of resultType</p>
     *
     * @param resultType resultType
     */
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    /**
     * 获取sql.
     *
     * @return sql.
     */
    public String getSql() {
        return sql;
    }

    /**
     * 设置 sql.
     *
     * <p>You can use getSql() to get the value of sql</p>
     *
     * @param sql sql
     */
    public void setSql(String sql) {
        this.sql = sql;
    }
}
