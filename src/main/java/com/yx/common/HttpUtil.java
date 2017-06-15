package com.yx.common;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by xiezg@317hu.com on 2017/6/15 0015.
 */
public class HttpUtil {
    public static final String ROCKETMQ_CONSOLE_NAMESVR_URL = "https://rocketmq.317hu.com:8443/rocketmq/nsaddr";

    public static String loadNameSrvList() {
        String namesrvAddr = null;
        ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
        Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
        HttpClient httpClient = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
        GetMethod getMethod = new GetMethod(ROCKETMQ_CONSOLE_NAMESVR_URL);
        try {
            httpClient.executeMethod(getMethod);
            namesrvAddr = getMethod.getResponseBodyAsString();
        } catch (Throwable e) {
            namesrvAddr = System.getProperty("rocketmq.config.namesrvAddr");
        } finally {
            getMethod.releaseConnection();
        }

        if (StringUtils.isBlank(namesrvAddr)) {
            namesrvAddr = System.getProperty("rocketmq.config.namesrvAddr");
        }

        return namesrvAddr;
    }

    public static void main(String args[]) {
        System.out.println(loadNameSrvList());
    }
}
