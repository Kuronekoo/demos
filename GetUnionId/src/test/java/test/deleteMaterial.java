package test;

import java.io.*;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: shenchao
 * @Date: created in 18:19 2018/3/5
 * @Description:
 */
public class deleteMaterial {

    public static void main(String[] args) {
        double deleteFileSizeCount = 184686.0;
        deleteFileSizeCount = deleteFileSizeCount/1024/1024;
        deleteFileSizeCount = reservePoint(deleteFileSizeCount,3);
        double surplusCapacity = 52984.353 - deleteFileSizeCount;
        System.out.println(surplusCapacity);
        surplusCapacity = reservePoint(surplusCapacity,3);
        System.out.println(surplusCapacity);

    }

    public static double reservePoint(double dble , int point){
        return new BigDecimal(dble).setScale(point, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    public static String processImg(String path) {
        String[] arr = path.split("/");
        int length = arr.length;
        if (isNumber(arr[length-2])) {
            return arr[length-3] + "/" + arr[length-2] + "/" + arr[length-1];
        }
        else {
            return arr[length-1];
        }
    }

    public static boolean isNumber(String str) {
        try {
            Long.parseLong(str);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }


    public static String getFileSize(String addr) {
        StringBuilder rtn = new StringBuilder();
        OutputStream os = null;
        PrintWriter pw = null;
        BufferedReader in = null;
        System.out.println("addr:" + addr);
        try {
            URL url = new URL(addr);
            HttpURLConnection uc = (HttpURLConnection)url.openConnection();

            uc.setConnectTimeout(2000);		//连接超时设置为2s
            uc.setReadTimeout(20000);			//读超时设置为20s
            uc.setDoOutput(true);				//因为这个是post请求，参数要放在http正文内，因此需要设为true，默认情况下是false
            uc.setUseCaches(false);				//post请求不能使用缓存
            // 设定传送的内容类型是可序列化的java对象(如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
            uc.setRequestProperty("Content-type", "text/xml;charset=GBK");
            uc.setRequestMethod("POST");	// 设定请求的方法为"POST"，默认是GET
            uc.connect();

            os = uc.getOutputStream();
            OutputStreamWriter out = new OutputStreamWriter(os, "GBK");
            pw = new PrintWriter(out);
            pw.print("");
            pw.flush();

			 /*获取服务器端返回信息*/
            in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "GBK"));
            String inputLine;
            while((inputLine = in.readLine()) != null){
                rtn.append(inputLine).append("\r\n");
            }

        } catch(SocketTimeoutException e) {
            return "time_out";
        } catch(ConnectException e) {
            return "connect_exception";
        }
        catch (Exception ex) {
            System.out.println("调用IMCC监控中心接口异常" + ex);
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                    pw = null;
                }

                if (os != null) {
                    os.close();
                    os = null;
                }

                if (in != null) {
                    in.close();
                    in = null;
                }
            }
            catch (Exception ex) {
                System.out.println("释放资源异常" + ex);
            }
        }
        return rtn.toString();
    }



}
