package com.seed.extend.umeng.ios;

import com.seed.extend.umeng.IOSNotification;

public class IOSBroadcast extends IOSNotification {
	
	public IOSBroadcast(String appkey, String appMasterSecret) throws Exception {
		setAppMasterSecret(appMasterSecret);
		setPredefinedKeyValue("appkey", appkey);
		this.setPredefinedKeyValue("type", "broadcast");
	}
}
