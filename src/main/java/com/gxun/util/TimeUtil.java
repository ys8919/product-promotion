package com.gxun.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	public static String getTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       return df.format(new Date());
	}
}
