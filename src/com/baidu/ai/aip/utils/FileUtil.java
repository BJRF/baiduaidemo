package com.baidu.ai.aip.utils;

import java.io.*;

/**
 * �ļ���ȡ������
 */
public class FileUtil {

	/**
	 * ��ȡ�ļ����ݣ���Ϊ�ַ�������
	 */
	public static String readFileAsString(String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			throw new FileNotFoundException(filePath);
		}

		if (file.length() > 1024 * 1024 * 1024) {
			throw new IOException("File is too large");
		}

		StringBuilder sb = new StringBuilder((int) (file.length()));
		// �����ֽ�������
		FileInputStream fis = new FileInputStream(filePath);
		// ����һ������Ϊ10240��Buffer
		byte[] bbuf = new byte[10240];
		// ���ڱ���ʵ�ʶ�ȡ���ֽ���
		int hasRead = 0;
		while ((hasRead = fis.read(bbuf)) > 0) {
			sb.append(new String(bbuf, 0, hasRead));
		}
		fis.close();
		return sb.toString();
	}

	/**
	 * �����ļ�·����ȡbyte[] ����
	 */
	public static byte[] readFileByBytes(String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			throw new FileNotFoundException(filePath);
		} else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream(
					(int) file.length());
			BufferedInputStream in = null;

			try {
				in = new BufferedInputStream(new FileInputStream(file));
				short bufSize = 1024;
				byte[] buffer = new byte[bufSize];
				int len1;
				while (-1 != (len1 = in.read(buffer, 0, bufSize))) {
					bos.write(buffer, 0, len1);
				}

				byte[] var7 = bos.toByteArray();
				return var7;
			} finally {
				try {
					if (in != null) {
						in.close();
					}
				} catch (IOException var14) {
					var14.printStackTrace();
				}

				bos.close();
			}
		}
	}
}
