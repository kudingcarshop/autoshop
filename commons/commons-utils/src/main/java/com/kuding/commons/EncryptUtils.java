package com.kuding.commons;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class EncryptUtils {
	
	private static final Logger LOGGER = Logger.getLogger(EncryptUtils.class);
	
	public static final String DIGEST_ALGORITHM_MD5 = "MD5";

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String digestWithMD5(String str) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(EncryptUtils.DIGEST_ALGORITHM_MD5);
			byte[] str_byte = str.getBytes();
			byte[] md5_result_byte = md.digest(str_byte);
			String md5_result = bytesToHex(md5_result_byte);
			return md5_result;
		} catch (NoSuchAlgorithmException e) {
			LOGGER.info("digest with MD5 fail: " + e.getMessage());
			return null;
		}
	}

	/**
	 * 将字节数组转化为16进制字符串
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex(byte[] bytes) {
		StringBuffer md5str = new StringBuffer();
		// 把数组每一字节换成16进制连成md5字符串
		int digital;
		for (int i = 0; i < bytes.length; i++) {
			digital = bytes[i];
			if (digital < 0) { // 16及以上的数转16进制是两位
				digital += 256;
			}
			if (digital < 16) {
				md5str.append("0");// 如果是0~16之间的数的话则变成0X
			}
			md5str.append(Integer.toHexString(digital));
		}
		return md5str.toString().toUpperCase();
	}
}
