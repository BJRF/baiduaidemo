package com.baidu.ai.aip;

import com.baidu.ai.aip.utils.HttpUtil;
import com.baidu.ai.aip.utils.GsonUtils;
import com.hjf.demo.Base64Util;
import com.hjf.demo.FileUtil;

import java.io.IOException;
import java.util.*;

/**
 * ������������Է���
 */
public class FaceDetect {

	/**
	 * ��Ҫ��ʾ���������蹤���� FileUtil,Base64Util,HttpUtil,GsonUtils���
	 * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
	 * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
	 * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
	 * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3 ����
	 */
	public static String detect() {
		String Filepath = "G:/�ٶ�����ʶ��/test.png";
		// ����url
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
		try {
			String image = Base64Util.encode(FileUtil.readFileByBytes(Filepath));
			Map<String, Object> map = new HashMap<>();
			map.put("image", image);
			map.put("face_field", "faceshape,facetype,age,beauty,gender");
			map.put("image_type", "BASE64");

			String param = GsonUtils.toJson(map);

			// ע�������Ϊ�˼򻯱���ÿһ������ȥ��ȡaccess_token�����ϻ���access_token�й���ʱ�䣬
			// �ͻ��˿����л��棬���ں����»�ȡ��
			String accessToken = "24.e7d84cf565b95a2a9c9d04a1ed3942da.2592000.1528247783.282335-11197010";

			String result = HttpUtil.post(url, accessToken, "application/json",
					param);
			System.out.println(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		FaceDetect.detect();
	}
}