package com.miya.DynamicDataScourceDemo.utils;

import java.util.UUID;

public class Constants {

	/**
	 * 获取uuid
	 *
	 * @return
	 */
	public static String uuid() {
		String s = UUID.randomUUID().toString().toLowerCase();
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}

	public static String operateId() {
		UUID u = UUID.randomUUID();
		return toIDString(u.getMostSignificantBits()) + toIDString(u.getLeastSignificantBits());
	}

	private final static char[] DIGITS64 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-=".toCharArray();

	private static String toIDString(long l) {
		char[] buf = "00000000000".toCharArray(); // 限定11位长度
		int length = 11;
		long least = 63L; // 0x0000003FL
		do {
			buf[--length] = DIGITS64[(int) (l & least)]; // l & least取低6位
			l >>>= 6;
		} while (l != 0);
		return new String(buf);
	}

}
