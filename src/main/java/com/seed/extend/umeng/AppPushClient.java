package com.seed.extend.umeng;

import java.util.concurrent.TimeUnit;

import com.seed.utils.CryptUtils;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AppPushClient {

	// The user agent
	protected final String USER_AGENT = "Mozilla/5.0";

	// This object is used for sending the post request to Umeng
	protected final OkHttpClient client = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
			.readTimeout(300, TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).build();

	// The host
	protected static final String host = "http://msg.umeng.com";

	// The post path
	protected static final String postPath = "/api/send";

	public boolean send(UmengNotification msg) throws Exception {
		String timestamp = Integer.toString((int) (System.currentTimeMillis() / 1000));
		msg.setPredefinedKeyValue("timestamp", timestamp);
		String url = host + postPath;
		String postBody = msg.getPostBody();
		String sign = CryptUtils.GetMD5("POST" + url + postBody + msg.getAppMasterSecret()).toLowerCase();
		
		url = url + "?sign=" + sign;
		
		// System.out.println("body : " + postBody);
		// System.out.println("sign : " + sign);
		
		RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), postBody);
		Builder builder = new Request.Builder().url(url).addHeader("User-Agent", USER_AGENT).method("POST", requestBody);

		// Send the post request and get the response
		Response response = client.newCall(builder.build()).execute();
		
		boolean isalert = false;
		
		if (isalert) {
			int status = response.code();
			
			System.out.println("Response Code : " + status);
			System.out.println(response.body().string());
			
			if (status == 200) {
				System.out.println("消息推送成功");
			} else {
				System.out.println("消息推送失败");
			}
		}
		
		return true;
	}
}
