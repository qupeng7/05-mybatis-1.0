package com.qupeng.mybatis.parsing;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;

public class MyXPathParser {

    private XPath xpath;

    private Document document;

    public MyXPathParser(InputStream inputStream) {
        this.xpath = getXpath();
        this.document = createDocument(new InputSource(inputStream));
    }

    public XPath getXpath() {
        //XPath方式的xml解析对象
        XPathFactory factory = XPathFactory.newInstance();
        return factory.newXPath();
    }

    /**
     * 创建Document对象
     *
     * jdk自带dom解析
     *
     * @param inputSource
     * @return
     */
    private Document createDocument(InputSource inputSource) {
        try {
            //JDK提供的文档解析工厂对象
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //设置是否验证
            factory.setValidating(true);
            //设置是否支持命名空间
            factory.setNamespaceAware(false);
            //设置是否忽略注释
            factory.setIgnoringComments(true);
            //设置是否忽略元素内容的空白
            factory.setIgnoringElementContentWhitespace(false);
            //是否将CDATA节点转换为文本节点
            factory.setCoalescing(false);
            //设置是否展开实体引用节点，这里是sql片段引用
            factory.setExpandEntityReferences(true);

            //创建一个DocumentBuilder对象
            DocumentBuilder builder = factory.newDocumentBuilder();
            //设置解析mybatis xml文档节点的解析器,也就是上面的XMLMapperEntityResolver
            builder.setEntityResolver(new MyXMLEntityResolver());

            //设置解析文档错误的处理
            builder.setErrorHandler(new ErrorHandler() {
                @Override
                public void error(SAXParseException exception) throws SAXException {
                    throw exception;
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    throw exception;
                }

                @Override
                public void warning(SAXParseException exception) throws SAXException {
                }
            });

            //解析输入源的xml数据为一个Document文档对象
            return builder.parse(inputSource);

        } catch (Exception e) {
            throw new RuntimeException("Error creating document instance.  Cause: " + e, e);
        }
    }

    /**
     * 在一个指定的上下文文档中 评估、评价、计算 一个XPath表达式的值，并返回指定的类型
     *
     * @param expression
     * @return
     */
    public Object evaluate(String expression) {
        try {
            return xpath.evaluate(expression, document, XPathConstants.NODE);
        } catch (Exception e) {
            throw new RuntimeException("Error evaluating XPath.  Cause: " + e, e);
        }
    }
}