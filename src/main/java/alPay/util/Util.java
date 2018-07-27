package alPay.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 工具类
 * 日期：2018/07/20 17:32
 * by 陶复平
 */
public class Util {

	private static Logger logger = LoggerFactory.getLogger(Util.class);
	/**
     * 获取字符串md5
     *
     * @param str
     * @return
     */
    public static String getMd5(String str) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.toString());
            return str;
        }
        md.update(str.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * 生成随机令牌
     *
     * @return
     */
    public static String getTokenCode() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 32);
    }

    /**
     * 获取当前时间
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
    
    /**
     * 获取当前日期
     * @return
     */
    public static java.sql.Date getCurrentDate(){
        return new java.sql.Date(System.currentTimeMillis());
    }

    /**
     * 获取未来日期
     * @param day
     * @return
     */
    public static Date getFutureDate(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + day);
        return new Date(calendar.getTimeInMillis());
    }
    /**
     * 获取当前时间戳
     */
    public static long getCurrentTime() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds   精确到秒的字符串
     * @param formatStr
     * @return
     */
    public static Timestamp time2Timestamp(String seconds, String formatStr) {
        if (seconds == null || seconds.length() <= 0) {
            return null;
        }
        if (formatStr == null || formatStr.length() <= 0) {
            formatStr = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        String timestampStr = sdf.format(new Date(Long.valueOf(seconds + "000")));
        return Timestamp.valueOf(timestampStr);
    }

    /**
     * 获取将来时间
     *
     * @param type   Calendar.YEAR(年) Calendar.MONTH(月) Calendar.DATE(日)
     * @param amount 数量，根据type的不同代表几年、几个月、几天
     * @return
     */
    public static Timestamp getFutureTimestamp(int type, int amount) {
        Timestamp timestamp = Util.getCurrentTimestamp();
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp);
        cal.add(type, amount);
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * 获取将来时间
     *
     * @param time   需要添加的时间
     * @param type   Calendar.YEAR(年) Calendar.MONTH(月) Calendar.DATE(日) Calendar.SECOND(秒)
     * @param amount 数量，根据type的不同代表几年、几个月、几天
     * @return
     */
    public static Timestamp getFutureTimestamp(Timestamp time, int type, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(type, amount);
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * 获取一天的0点时间
     */
    public static Timestamp getTimestampOfDayStart(Timestamp time) {
        if (time == null) {
            time = getCurrentTimestamp();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return Timestamp.valueOf(simpleDateFormat.format(time) + " 00:00:00");
    }

    /**
     * 获取一天的23:59:59时间
     */
    public static Timestamp getTimestampOfDayEnd(Timestamp time) {
        if (time == null) {
            time = getCurrentTimestamp();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return Timestamp.valueOf(simpleDateFormat.format(time) + " 23:59:59");
    }

    /**
     * 获取一小时的:00:00时间
     */
    public static Timestamp getTimestampOfHourStart(Timestamp time) {
        if (time == null) {
            time = getCurrentTimestamp();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        return Timestamp.valueOf(simpleDateFormat.format(time) + ":00:00");
    }

    /**
     * 获取当前时间的小时HH
     */
    public static String formatTimestampOfHour(Timestamp time) {
        if (time == null) {
            time = getCurrentTimestamp();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        return simpleDateFormat.format(time);
    }

    /**
     * 获取当前时间的年yyyy
     */
    public static int formatTimestampOfYear(Timestamp time) {
        if (time == null) {
            time = getCurrentTimestamp();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前时间的年MM
     */
    public static int formatTimestampOfMonth(Timestamp time) {
        if (time == null) {
            time = getCurrentTimestamp();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前时间的天dd
     */
    public static int formatTimestampOfDay(Timestamp time) {
        if (time == null) {
            time = getCurrentTimestamp();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取当前时间的yyyy-MM-dd格式
     */
    public static String formatTimestamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(getCurrentTimestamp());
    }

    /**
     * 获取当前时间的yyyy-MM-dd HH:mm:ss格式
     *
     * @return
     */
    public static String formatTimestampHasS() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(getCurrentTimestamp());
    }

    /**
     * 获取指定时间的yyyy-MM-dd HH:mm:ss格式
     *
     * @return
     */
    public static String formatTimestampHasS(Timestamp time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(time);
    }

    /**
     * 字符串转时间戳
     *
     * @return
     */
    public static Timestamp formatTimestampHasS(String time) {
        if (time == null) {
            return new Timestamp(0);
        }
        return Timestamp.valueOf(time);
    }

    /**
     * 获取指定时间的yyyy-MM-dd HH:mm:ss格式
     */
    public static String formatTimestamp(Timestamp time) {
        if (time == null) {
            time = getCurrentTimestamp();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(time);
    }

    /**
     * 获取IP
     */
    public static String getAddressIP() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return getAddressIP(request);
    }

    public static String getAddressIP(HttpServletRequest request) {
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                //Based on the IP network card in the machine configuration
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //在通过多个代理的情况下,第一个真正的IP IP为客户,多个IP依照“,”分割,"***.***.***.***".length()
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }



    /**
     * http GET 请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String httpGet(String url) throws Exception {
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        //设置最大请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        try {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            	logger.info(response.getStatusLine().getStatusCode()+"");
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            } else {
                throw new Exception(response.getStatusLine().toString());
            }
        } finally {
            response.close();
        }
        return result;
    }

    /**
     * http GET 请求
     *
     * @param url
     * @param timeout 超时时间
     * @return
     * @throws Exception
     */
    public static String httpGet(String url, int timeout) throws Exception {
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        //设置最大请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        try {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            } else {
                throw new Exception(response.getStatusLine().toString());
            }
        } finally {
            response.close();
        }
        return result;
    }

    /**
     * http GET 请求
     *
     * @param url
     * @param defaultCharset 编码例如UTF-8
     * @return
     * @throws Exception
     */
    public static String httpGet(String url, String defaultCharset) throws Exception {
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        //设置最大请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        try {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, defaultCharset);
                EntityUtils.consume(entity);
            } else {
                throw new Exception(response.getStatusLine().toString());
            }
        } finally {
            response.close();
        }
        return result;
    }

    /**
     * http POST 请求
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String httpPost(String url, HashMap<String, Object> params) throws Exception {
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        //设置最大请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(25000).setConnectTimeout(25000).build();
        httpPost.setConfig(requestConfig);
        //拼接参数
        java.util.List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (String key : params.keySet()) {
            nvps.add(new BasicNameValuePair(key, params.get(key).toString()));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        CloseableHttpResponse response = httpClient.execute(httpPost);

        try {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, "utf-8");
                EntityUtils.consume(entity);
            } else {
            	System.out.println("信息所有："+response.getAllHeaders().toString());
                throw new Exception(response.getStatusLine().toString());
            }
        } finally {
            response.close();
        }
        return result;
    }
    
    public static String httpPost(String url, java.util.List<NameValuePair> nvps) throws Exception {
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        //设置最大请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(25000).setConnectTimeout(25000).build();
        httpPost.setConfig(requestConfig);
        //拼接参数
//        java.util.List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        for (String key : params.keySet()) {
//            nvps.add(new BasicNameValuePair(key, params.get(key).toString()));
//        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        CloseableHttpResponse response = httpClient.execute(httpPost);

        try {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, "utf-8");
                EntityUtils.consume(entity);
            } else {
            	System.out.println("信息所有："+response.getAllHeaders().toString());
                throw new Exception(response.getStatusLine().toString());
            }
        } finally {
            response.close();
        }
        return result;
    }

    /**
     * http post
     * @param url
     * @param jsonParam
     * @return
     * @throws Exception
     */
    public static String httpPost(String url, String jsonParam) throws Exception {
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        //设置最大请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(25000).setConnectTimeout(25000).build();
        httpPost.setConfig(requestConfig);
        //参数
        //解决中文乱码问题
        StringEntity entityParam = new StringEntity(jsonParam.toString(), "utf-8");
        entityParam.setContentEncoding("UTF-8");
        entityParam.setContentType("application/json");
        httpPost.setEntity(entityParam);
        CloseableHttpResponse response = httpClient.execute(httpPost);

        try {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, "utf-8");
                EntityUtils.consume(entity);
            } else {
                throw new Exception(response.getStatusLine().toString());
            }
        } finally {
            response.close();
        }
        return result;
    }
    
    /**
     * 拼接url参数
     *
     * @param params
     * @return
     * @throws Exception
     */
    public static String buildQueryString(HashMap<String, String> params) {
        String queryString = "";
        try {
            int i = 0;
            for (String key : params.keySet()) {
                queryString += (key + "=" + java.net.URLEncoder.encode(params.get(key), "utf-8"));
                if (i < params.size() - 1) {
                    queryString += "&";
                }
                i++;
            }
        } catch (Exception e) {
        }
        return queryString;
    }
    /**
     * 拼接url参数
     *
     * @param params
     * @return
     * @throws Exception
     */
    public static String buildQueryString1(HashMap<String, Object> params) {
        String queryString = "";
        try {
            int i = 0;
            for (String key : params.keySet()) {
                queryString += (key + "=" + java.net.URLEncoder.encode(params.get(key).toString(), "utf-8"));
                if (i < params.size() - 1) {
                    queryString += "&";
                }
                i++;
            }
        } catch (Exception e) {
        }
        return queryString;
    }
    /**
     * 字符串进行url编码
     * @param str
     * @return
     */
    public static String getUrlEncodeString(String str) {
        try{
            str = java.net.URLEncoder.encode(str, "utf-8");
        }catch (Exception e){
            logger.info("ShopHelper->getUrlEncodeString异常为"+e.getMessage());
        }
        return str;
    }
    /**
     * 转换逗号分隔的整形字符串为整形List
     *
     * @param idString 例:'1,2,3,4'
     * @return
     */
    public static List<Integer> convertIdStringToIntegerList(String idString) {
        List<Integer> list = new ArrayList<>();

        String[] stringArray = idString.split(",");

        for (int i = 0; i < stringArray.length; i++) {
            try {
                list.add(Integer.valueOf(stringArray[i]));
            } catch (NumberFormatException ex) {
                continue;
            }
        }

        return list;
    }
    
    /**
     * wxpay 生成签名
     * @param packageParams 参数列表
     * @param API_KEY 商户秘钥
     * @return
     */
    public static String createSign(SortedMap<Object, Object> packageParams, String API_KEY) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + API_KEY);
        String sign = getMd5(sb.toString()).toUpperCase();
        return sign;
    }
    
    /**
     * 将参数转化为XML
     * @param parameters 参数列表
     * @return
     */
    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }
    
    /**
     * 解析xml
     * @param strxml
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    public static Map doXMLParse(String strxml) throws JDOMException, IOException {
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
        if(null == strxml || "".equals(strxml)) {
            return null;
        }
        Map m = new HashMap();
        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while(it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if(children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }
            m.put(k, v);
        }
        //关闭流
        in.close();
        return m;
    }
    /**
     * 获取子结点的xml
     * @param children
     * @return
     */
    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if(!children.isEmpty()) {
            Iterator it = children.iterator();
            while(it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if(!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }
        return sb.toString();
    }
}
