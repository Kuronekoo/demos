package cn.sc.demo;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 加密方法DESede表示是3des加密方式; 运算模式CBC,ECB。在ECB模式下仅使用key;
 * 填充模式NoPadding、PKCS5Padding、SSL3Padding;
 */
public class DESedeSecret {

    private static SecretKeyFactory keyFactory;
    private static DESedeKeySpec dks;
    private static SecretKey securekey;

    /**
     * 加密方法/运算模式/填充模式
     */
    private final static String Algorithm = "DESede/ECB/PKCS5Padding";

    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "DESede";

	/*
	 * 加密方法DESede表示是3des加密方式 默认运算模式ECB。在ECB模式下仅使用key。 默认填充模式PKCS5Padding。
	 */

    private static DESedeSecret encrypt = null;

    private DESedeSecret(byte[] ENCRYPT_KEY) {

        // 添加jce支持(sun有其默认实现)
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        try {
            // 实例化DES密钥规则
            dks = new DESedeKeySpec(ENCRYPT_KEY);
            // 指定加密算法实例化密钥工厂
            keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
            // 生成一个密钥
            securekey = keyFactory.generateSecret(dks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public static DESedeSecret getInstance(byte[] ENCRYPT_KEY) {
        encrypt = new DESedeSecret(ENCRYPT_KEY);
        return encrypt;
    }

    /**
     * 3des加密
     *
     * @param bindParam
     *            绑定链接串;
     * @return 加密后密文
     */
    private String encrypt(String bindParam) {

        Cipher cipher = null;
        String url = null;
        try {
            // 实例化
            cipher = Cipher.getInstance(Algorithm);
            // 使用密钥初始化，设置为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, securekey);
            // 执行加密;
            byte[] arry = cipher.doFinal(bindParam.getBytes("utf-8"));
            // 将加密字节流数据进行Base64转码;
            // url = new String(Base64.encodeBase64(arry), "UTF-8");
            url = replaceStr(new String(Base64.encodeBase64(arry), "UTF-8"),
                    true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 3des解密
     *
     * @param url
     *            密文
     * @return 解密后密码
     */
    private String decrypt(String url) {

        Cipher cipher;
        String bindParam = null;
        try {
            url = replaceStr(url, false);
            // 实例化
            cipher = Cipher.getInstance(Algorithm);
            // 使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, securekey);
            // 执行解密;
            byte[] arry = cipher.doFinal(Base64.decodeBase64(url
                    .getBytes("UTF-8")));
            // 转换成字符串;
            bindParam = new String(arry, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bindParam;
    }
    /**
     * 3des解密发券子系统
     *
     * @param url
     *            密文
     * @return 解密后密码
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    private String decrypt1(String url) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {

        Cipher cipher;
        String bindParam = null;
        url = replaceStr(url, false);
        // 实例化
        cipher = Cipher.getInstance(Algorithm);
        // 使用密钥初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, securekey);
        // 执行解密;
        byte[] arry = cipher.doFinal(Base64.decodeBase64(url
                .getBytes("UTF-8")));
        // 转换成字符串;
        bindParam = new String(arry, "utf-8");

        return bindParam;
    }

    /**
     * 字符替换,替换链接中不允许字符;
     *
     * @param Str
     *            密文
     * @param flag
     *            标识正向替换或者反向替换;
     *
     * @return 解密后密码
     */
    private String replaceStr(String Str, boolean flag) {
        String repStr = "";

        if (flag) {
            repStr = Str.replaceAll("\\+", "*").replaceAll("\\/", ":")
                    .replaceAll("\\=", "_");
        } else {
            repStr = Str.replaceAll("\\*", "+").replaceAll("\\:", "/")
                    .replaceAll("\\_", "=");
        }
        return repStr;
    }

    /**
     * @param encStr
     *            加密明文串;
     * @return 返回加密字符串;
     */
    public String getEncryptKey(String encStr) {
        return encrypt(encStr);
    }

    /**
     * @param decStr
     *            加密串;
     * @return 返回解密明文串;
     */
    public String getDecryptKey(String decStr) {
        return decrypt(decStr);
    }
    /**
     * @param decStr
     *            加密串;
     * @return 返回解密明文串;
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public String getDecryptKey1(String decStr) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return decrypt1(decStr);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] ENCRYPT_KEY0 = new String("crv_ehasdfu_sgfnasdbf_pw").getBytes();
//        String test0 = "{\"appid\":\"yuhgjkas\",\"nowtime\":1407828167547,\"openid\":\"o_VstuFKop-UTHJ3conlL-IzVyWk\"}";
        String test0="o1yl_jqVhV1i_GTY0dormzWBVRQY";
        String testSc0 = DESedeSecret.getInstance(ENCRYPT_KEY0).getEncryptKey(test0);
//
//
//
        System.out.println(testSc0);
//        System.out.println(DESedeSecret.getInstance(ENCRYPT_KEY0).getDecryptKey(test0));
//        String tt = "2GBTySqAAXFw*7m7rXfV7WTX*vQVVs8f3*lrROv:LDzjpwSBYmYVdEzx8TbPZUgDn3sCZwvooxmWHZdri4sBfpfM6cN2Loy38qTIOK17Vg01ACOSimM0qQ__";
//
//        System.out.println(DESedeSecret.getInstance(ENCRYPT_KEY0).getDecryptKey(tt));
    }
}

