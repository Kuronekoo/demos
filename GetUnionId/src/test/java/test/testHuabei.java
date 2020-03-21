package test;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.UnsupportedEncodingException;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;
        import java.io.StringReader;
        import java.io.UnsupportedEncodingException;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.net.URLConnection;
        import java.util.HashMap;
        import java.util.Map;

        import javax.xml.parsers.DocumentBuilder;
        import javax.xml.parsers.DocumentBuilderFactory;
        import javax.xml.parsers.ParserConfigurationException;

import it.sauronsoftware.base64.Base64;
import org.w3c.dom.Document;
        import org.w3c.dom.Element;
        import org.w3c.dom.NodeList;
        import org.xml.sax.InputSource;
        import org.xml.sax.SAXException;

public class testHuabei {
    public static String[] sendSms(String phoneNumber, String smsContent) {
        System.out.println("sendSms begin");
//		String url = "http://211.137.171.46:9191/adc_posthandler_new";
        String url = "http://123.56.14.50:9191/adc_posthandler_test";						//此处修改为最终发送地址
        String result = testPost(url, phoneNumber, smsContent);
        System.out.println(result);
        if (result == ""){
            String[] arr = {"-1", "null err"};
            return arr;
        }else{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(new InputSource(new StringReader(result)));
                Element root = doc.getDocumentElement();
                NodeList books = root.getChildNodes();
                String nl =  books.item(0).getFirstChild().getNodeValue();
                System.out.println(nl);
                if (nl.equalsIgnoreCase("0")){
                    return null;
                }else{
                    return nl.split(":");
                }
            } catch (ParserConfigurationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SAXException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch blockwebservice.putheader("
                e.printStackTrace();
            }
        }
        return null;
    }
    public static String testPost(String urlStr, String phoneNumber, String smsContent) {
        String line = "";
        try {
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
//            con.setRequestProperty("Pragma:", "no-cache");
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Connection", "close");
            con.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            con.setRequestProperty("Action", "\"submitreq11\"");								//此处按照接口文档说明按收发实际正确配置

            String xmlInfo = getXmlInfo(phoneNumber, smsContent);
            System.out.println("urlStr=" + urlStr);
            System.out.println("xmlInfo=" + xmlInfo);
            if (xmlInfo == ""){
                return "";
            }else{
                con.setRequestProperty("Content-Length", Integer.toString(new String(xmlInfo.getBytes("UTF-8")).length()));
                OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
                out.write(new String(xmlInfo.getBytes("UTF-8")));
                out.flush();
                out.close();
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//	            System.out.println(br.readLine());
                for (line = br.readLine(); line != null; line = br.readLine()) {
                    break;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            line = "";
        } catch (IOException e) {
            e.printStackTrace();
            line = "";
        }
        return line;
    }

    private static String getXmlInfo(String phoneNumber, String smsContent) {
        StringBuffer msg = new StringBuffer();
        msg.append(smsContent);
        String msgutf8;
        try {
            msgutf8 = new String(msg.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            msgutf8 = "";
        }
        if (msgutf8 != ""){
            StringBuilder sb = new StringBuilder();
            sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?><Body>");
            sb.append("<user>123456</user><password>123456</password><version>1.2</version><submit><usermsgid>123456789012</usermsgid><desttermid>" + phoneNumber + "</desttermid><srctermid></srctermid><msgcontent>" + getBase64(msgutf8) + "</msgcontent><signid></signid><desttype>0</desttype><needreply>1</needreply></submit></Body>");			//此处改成正式配给的账号密码

//            sb.append("<user>388182</user><password>930170</password><version>1.2</version><submit><usermsgid>123456789012</usermsgid><desttermid>" + phoneNumber + "</desttermid><srctermid></srctermid><msgcontent>" + getBase64(msgutf8) + "</msgcontent><signid></signid><desttype>0</desttype><needreply>1</needreply></submit></Body>");
            return sb.toString();
        }else{
            return "";
        }
    }
    public static String getBase64(String str) {
        String encoded = Base64.encode(str, "UTF-8");
        return encoded;
    }

    public String getFromBase64(String s) {
        String decoded = Base64.decode(s, "UTF-8");
        return decoded;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        String[] results = sendSms("18682117217", "测试下发【测试】");										//此处改成目的手机号码和实际短信内容
//        String[] results = sendSms("18682117217", "测试下发【华润万家】");										//此处改成目的手机号码和实际短信内容
        System.out.println(results);
        if (results == null) {
            System.out.println(results);
        }else{
            for (int i = 0 ; i <results.length ; i++ ) {
                System.out.println("--"+results[i]);
            }
        }
    }
}
