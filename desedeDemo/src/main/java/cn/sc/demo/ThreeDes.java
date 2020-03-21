package cn.sc.demo;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class ThreeDes {
	private static final String Algorithm = "DESede"; // ���� �����㷨,����
	// DES,DESede,Blowfish

	// keybyteΪ������Կ������Ϊ24�ֽ�
	// srcΪ�����ܵ����ݻ�������Դ��
	public static byte[] encryptMode(byte[] keybyte, byte[] src) {
		try {
			// ������Կ
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// System.out.print(deskey);
			// ����
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

	// keybyteΪ������Կ������Ϊ24�ֽ�
	// srcΪ���ܺ�Ļ�����
	public static byte[] decryptMode(byte[] keybyte, byte[] src) {
		try {
			// ������Կ
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// ����
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

    //16�����ַ���ת����
	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// �����ַ���ʾһ���ֽڣ������ֽ����鳤�����ַ������ȳ���2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}
	
	//����ת16�����ַ���
	public static String byteArr2HexStr(byte[] arrB) throws Exception { 
		int iLen = arrB.length;
		// ÿ��byte�������ַ����ܱ�ʾ�������ַ����ĳ��������鳤�ȵ�����
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// �Ѹ���ת��Ϊ����
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// С��0F������Ҫ��ǰ�油0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		// ���128λ
		String result = sb.toString();
//		if(result.length()>128){
//			result = result.substring(0,result.length()-1);
//		}
		return result;
	}

	public static void main(String[] args) throws Exception {

		// ����°�ȫ�㷨,�����JCE��Ҫ������ӽ�ȥ
		//Security.addProvider(new SunJCE());
//		final byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10, 0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD, 0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36, (byte) 0xE2 }; // 24�ֽڵ���Կ

		System.out.println(ThreeDes.byteArr2HexStr("sortecsortecsortecsortec".getBytes()));

		String key = "crv_ehasdfu_sgfnasdbf_pw";

		String szSrc = "o1yl_jmLmIcxFSigk-KeOlkIv-VI";
		System.out.println("����ǰ���ַ���:" + szSrc);

		byte[] encoded = encryptMode(key.getBytes(), szSrc.getBytes());
		System.out.println("���ܺ���ַ���:" + ThreeDes.byteArr2HexStr(encoded));

		byte[] srcBytes = decryptMode(key.getBytes(), ThreeDes.hexStr2ByteArr(ThreeDes.byteArr2HexStr(encoded)));
		System.out.println("���ܺ���ַ���:" + (new String(srcBytes)));


	}
}