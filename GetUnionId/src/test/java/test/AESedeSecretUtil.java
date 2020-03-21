package test;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * AES�
 * @author yanghl
 *
 */
public class AESedeSecretUtil {

    private static String DEFALUT_KEY = "ABCDEFGHIJKLMN";
    private final static String UTF_8="utf-8";
    public static final String KEY="wjcard2017szyrgs";
    public static final  String IV="0218964300239865";
    private static Logger logger = Logger.getLogger(AESedeSecretUtil.class);
    /**
     *
     * @param strKey
     * @param
     * @return
     * @throws Exception
     * @date 2017-5-8
     * @author yanghl
     */
    public static String encrypt(String strKey, String strIn) throws Exception {
        SecretKeySpec skeySpec = getKey(strKey);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(IV.getBytes(UTF_8));
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(strIn.getBytes());

        //return new BASE64Encoder().encode(encrypted);
        return parseByte2HexStr(encrypted);
    }

    /**
     *
     * @param strKey
     * @param strIn
     * @return
     * @throws
     * @date 2017-5-8
     * @author yanghl
     */
    public static String decrypt(String strKey, String strIn) throws Exception {
        String originalString="";
        try{
            SecretKeySpec skeySpec = getKey(strKey);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes(UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            //byte[] encrypted1 = new BASE64Decoder().decodeBuffer(strIn);
            byte[] encrypted1 = parseHexStr2Byte(strIn);

            byte[] original = cipher.doFinal(encrypted1);
            originalString = new String(original,UTF_8);
        }catch(Exception e){
            logger.info("AES:   "+strIn);
            e.printStackTrace();
        }
        return originalString;
    }

    private static SecretKeySpec getKey(String strKey) throws Exception {
        if(strKey == null || "".equals(strKey))
        {
            strKey = DEFALUT_KEY;
        }
        byte[] arrBTmp = strKey.getBytes(UTF_8);
        byte[] arrB = new byte[16]; //

        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");
        return skeySpec;
    }

    /**

     * @param hexStr

     * @return

     */

    public static byte[] parseHexStr2Byte(String hexStr) {

        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }

        return result;

    }

		/*

		 * @param buf

		 * @return

		 */

    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        String KEY1="wjcard2017szyrgs";

        String bizMerchantCode ="D2B539EC614353C6F052E18E60594F7A";
        String memberid="B901AF4353D89A069D4C43A705E6364B6682780EBC6E6A1B05DE124357A14902";
        String imNumber="C96C857D0E4889D3975BEF5645F8A26B";
        String openId = "7E4F25C01A4E556559DAAF1159690EEBF62EE3CD6FC149F32937DBA6E6DAB75B";

//        memberid = encrypt(KEY1,memberid);
        memberid = decrypt(KEY1,memberid);

//        bizMerchantCode = encrypt(KEY1,bizMerchantCode);
        bizMerchantCode = decrypt(KEY1,bizMerchantCode);

//        imNumber = encrypt(KEY1,imNumber);
        imNumber = decrypt(KEY1,imNumber);

//        String openid = encrypt(KEY1,Reserve3);
        String openid = decrypt(KEY1,openId);

        String parmeter="&bizMerchantCode="+bizMerchantCode+"&memberId="+memberid+"&merchantAppId="+imNumber+"&thirdAccount="+openid;

        System.out.println(parmeter);


        System.out.println(decrypt(KEY1,"B26F183C054B4DB35C9F547E636C21C415F73945D10C9E9F332228013F06962B"));





    }


}
