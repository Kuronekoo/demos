package cn.sc.demo;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class ThreeDes {
	private static final String Algorithm = "DESede"; // 定义 加密算法,可用
	// DES,DESede,Blowfish

	// keybyte为加密密钥，长度为24字节
	// src为被加密的数据缓冲区（源）
	public static byte[] encryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// System.out.print(deskey);
			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// keybyte为加密密钥，长度为24字节
	// src为加密后的缓冲区
	public static byte[] decryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

    //16进制字符串转数组
	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}
	
	//数组转16进制字符串
	public static String byteArr2HexStr(byte[] arrB) throws Exception { 
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		// 最大128位
		String result = sb.toString();
//		if(result.length()>128){
//			result = result.substring(0,result.length()-1);
//		}
		return result;
	}

	public static void main(String[] args) throws Exception {

		// 添加新安全算法,如果用JCE就要把它添加进去
		//Security.addProvider(new SunJCE());
//		final byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10, 0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD, 0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36, (byte) 0xE2 }; // 24字节的密钥

		System.out.println(ThreeDes.byteArr2HexStr("sortecsortecsortecsortec".getBytes()));

		String key = "crv_ehasdfu_sgfnasdbf_pw";

		String szSrc = "o1yl_jmLmIcxFSigk-KeOlkIv-VI";
		System.out.println("加密前的字符串:" + szSrc);

		byte[] encoded = encryptMode(key.getBytes(), szSrc.getBytes());
		System.out.println("加密后的字符串:" + ThreeDes.byteArr2HexStr(encoded));

		byte[] srcBytes = decryptMode(key.getBytes(), ThreeDes.hexStr2ByteArr(ThreeDes.byteArr2HexStr(encoded)));
		System.out.println("解密后的字符串:" + (new String(srcBytes)));


	}
}