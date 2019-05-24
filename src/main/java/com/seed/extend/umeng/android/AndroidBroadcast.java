package com.seed.extend.umeng.android;

import com.seed.extend.umeng.AndroidNotification;

public class AndroidBroadcast extends AndroidNotification {
	
	public AndroidBroadcast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "broadcast");	
	}
}
