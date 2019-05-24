package com.seed.utils;

import java.util.Date;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.NullEnumeration;

public class LogUtils {

	private static Logger logger;

	private static void init() {
		if (logger == null) {
			synchronized (LogUtils.class) {
				if (logger == null) {
					Layout layout = new PatternLayout("%-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ms ] - [ %p ]  %m%n");
					logger = Logger.getLogger("log4j");
					logger.setLevel(Level.toLevel(PropertiesUtils.GetString(Consts.CONFIG_LOG_LOG_LEVEL)));
					try {
						for (String s : PropertiesUtils.GetString(Consts.CONFIG_LOG_ENV).split(",")) {
							switch (s.toUpperCase()) {
							case "DEV":
								// 调试模式 日志输出到控制台
								Appender appenderC = new ConsoleAppender(layout, "System.out");
								logger.addAppender(appenderC);
								logger.debug("当前在调试环境下，日志将打印至控制台Cont");
								break;
							case "RUNTIME":
								// 日志输出文件
								String filePath = ToolUtils.IsWindows() ? PropertiesUtils.GetString(Consts.CONFIG_LOG_FILE_PATH_WINDOWS)
										: PropertiesUtils.GetString(Consts.CONFIG_LOG_FILE_PATH_LINUX);
								if (!filePath.endsWith("/")) {
									filePath += "/";
								}
								Appender appender = new FileAppender(layout, filePath + ToolUtils.GetFmtDate(new Date()) + ".log");
								logger.addAppender(appender);
								logger.debug("日志开始输出到" + filePath);
								break;
							}
						}
						if (logger.getAllAppenders() instanceof NullEnumeration) {
							System.err.println("当前配置下无法输出日志");
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw new IllegalArgumentException("日志初始化失败", e);
					}
				}
			}
		}
	}

	public static void Debug(Object info) {
		init();
		if (logger != null)
			logger.debug(info);
	}

	public static void Info(Object info) {
		init();
		if (logger != null)
			logger.info(info);
	}

	public static void Warn(Object info) {
		init();
		if (logger != null)
			logger.warn(info);
	}

	public static void Error(Object info) {
		init();
		if (logger != null)
			logger.error(info);
	}

	public static void Error(Object info, Throwable e) {
		init();
		if (logger != null)
			logger.error(info, e);
	}

}
