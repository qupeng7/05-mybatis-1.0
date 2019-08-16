package com.qupeng.mybatis.parsing;

import com.qupeng.mybatis.io.MyResources;
import com.qupeng.mybatis.mapping.MyConfiguration;
import com.qupeng.mybatis.mapping.MyEnvironment;
import com.qupeng.mybatis.mapping.MyMapperStatement;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MyXMLConfigBuilder {

    private MyXPathParser myXPathParser;

    public MyXMLConfigBuilder(InputStream inputStream) {
        myXPathParser = new MyXPathParser(inputStream);
    }

    /**
     * 解析，返回MyConfiguration配置对象
     *
     * @return
     */
    public MyConfiguration parse() {
        //数据源连接信息的Map
        Map<String, String> dataSourceMap = new HashMap<String, String>();

        //存放mapper.xml的sql信息
        Map<String, MyMapperStatement> myMapperStatementMap = new HashMap<String, MyMapperStatement>();


        //node代表mybatis-config.xml配置文件 <configuration/environment/dataSource>标签下的所有节点数据
        Node dataSourceNode = (Node)myXPathParser.evaluate("/configuration/environment/dataSource");
        NodeList proertiesNodeList = dataSourceNode.getChildNodes();
        for (int i=0; i< proertiesNodeList.getLength(); i++) {
            Node proertiesNode = proertiesNodeList.item(i);
            if (proertiesNode.getNodeType() == Node.ELEMENT_NODE) {
                String name = proertiesNode.getAttributes().getNamedItem("name").getNodeValue();
                String value = proertiesNode.getAttributes().getNamedItem("value").getNodeValue();
                dataSourceMap.put(name, value);
            }
        }
        //把mybatsi-config.xml数据源配置信息读取出来后，设置到MyEnvironment对象中
        MyEnvironment myEnvironment = new MyEnvironment();
        myEnvironment.setDriver(dataSourceMap.get("driver"));
        myEnvironment.setPassword(dataSourceMap.get("password"));
        myEnvironment.setUrl(dataSourceMap.get("url"));
        myEnvironment.setUsername(dataSourceMap.get("username"));


        //node代表mybatis-config.xml配置文件 <configuration/mappers>标签下的所有节点数据
        Node mappersNode = (Node)myXPathParser.evaluate("/configuration/mappers");
        NodeList mapperNodeList = mappersNode.getChildNodes();
        for (int i=0; i< mapperNodeList.getLength(); i++) {
            Node mapperNode = mapperNodeList.item(i);

            if (mapperNode.getNodeType() == Node.ELEMENT_NODE) {
                //xxxMapper.xml文件的路径
                String resource = mapperNode.getAttributes().getNamedItem("resource").getNodeValue();
                //根据xxxMapper.xml文件的路径读取xxxMapper.xml文件
                InputStream inputStream = MyResources.getResourceAsStream(resource);

                //解析xxxMapper.xml文件，创建新的xpath和document对象
                this.myXPathParser = new MyXPathParser(inputStream);

                Node mapperXmlNode = (Node)myXPathParser.evaluate("/mapper");
                //Mapper的命名空间
                String namespace = mapperXmlNode.getAttributes().getNamedItem("namespace").getNodeValue();

                NodeList mapperXmlNodeList = mapperXmlNode.getChildNodes();
                for (int ii=0; ii< mapperXmlNodeList.getLength(); ii++) {
                    Node mapperSQLNode = mapperXmlNodeList.item(ii);
                    if (mapperSQLNode.getNodeType() == Node.ELEMENT_NODE) {

                        String sqlId = mapperSQLNode.getAttributes().getNamedItem("id").getNodeValue();
                        String parameterType = mapperSQLNode.getAttributes().getNamedItem("parameterType").getNodeValue();
                        String resultType = mapperSQLNode.getAttributes().getNamedItem("resultType").getNodeValue();
                        String sql = mapperSQLNode.getTextContent();

                        //把sql信息读取出来后设置到MyMapperStatement对象
                        MyMapperStatement myMapperStatement = new MyMapperStatement();
                        myMapperStatement.setNamespace(namespace);
                        myMapperStatement.setSqlId(sqlId);
                        myMapperStatement.setParameterType(parameterType);
                        myMapperStatement.setResultType(resultType);
                        myMapperStatement.setSql(sql);

                        myMapperStatementMap.put(namespace + "." + sqlId, myMapperStatement);
                    }
                }
            }
        }

        //把两个xml文件 mybatis-config.xml 和 xxxMapper.xml 的配置信息数据封装到MyConfiguration对象
        MyConfiguration myConfiguration = new MyConfiguration();
        myConfiguration.setMyEnvironment(myEnvironment);
        myConfiguration.setMyMapperStatementMap(myMapperStatementMap);

        return myConfiguration;
    }
}