package com.baidu.ai.aip.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * http ������
 */
public class HttpUtil {

	public static String post(String requestUrl, String accessToken,
			String params) throws Exception {
		String contentType = "application/x-www-form-urlencoded";
		return HttpUtil.post(requestUrl, accessToken, contentType, params);
	}

	public static String post(String requestUrl, String accessToken,
			String contentType, String params) throws Exception {
		String encoding = "UTF-8";
		if (requestUrl.contains("nlp")) {
			encoding = "GBK";
		}
		return HttpUtil.post(requestUrl, accessToken, contentType, params,
				encoding);
	}

	public static String post(String requestUrl, String accessToken,
			String contentType, String params, String encoding)
			throws Exception {
		String url = requestUrl + "?access_token=" + accessToken;
		return HttpUtil.postGeneralUrl(url, contentType, params, encoding);
	}

	public static String postGeneralUrl(String generalUrl, String contentType,
			String params, String encoding) throws Exception {
		URL url = new URL(generalUrl);
		// �򿪺�URL֮�������
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		// ����ͨ�õ���������
		connection.setRequestProperty("Content-Type", contentType);
		connection.setRequestProperty("Connection", "Keep-Alive");
		connection.setUseCaches(false);
		connection.setDoOutput(true);
		connection.setDoInput(true);

		// �õ���������������
		DataOutputStream out = new DataOutputStream(
				connection.getOutputStream());
		out.write(params.getBytes(encoding));
		out.flush();
		out.close();

		// ����ʵ�ʵ�����
		connection.connect();
		// ��ȡ������Ӧͷ�ֶ�
		Map<String, List<String>> headers = connection.getHeaderFields();
		// �������е���Ӧͷ�ֶ�
		for (String key : headers.keySet()) {
			System.err.println(key + "--->" + headers.get(key));
		}
		// ���� BufferedReader����������ȡURL����Ӧ
		BufferedReader in = null;
		in = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), encoding));
		String result = "";
		String getLine;
		while ((getLine = in.readLine()) != null) {
			result += getLine;
		}
		in.close();
		System.err.println("result:" + result);
		return result;
	}
}
