/**
 * 
 */
package com.manoj.os.xmlm.spark;

import java.util.Objects;
import java.util.Properties;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

/**
 * @author Manoj Pawar
 *
 */
public class AppSparkContext {
	private static AppSparkContext appSparkContext;
	private static SparkContext sparkContext;
	private static final Object SYNCHRONIZATION_OBJECT = new Object();

	private AppSparkContext() {
	}

	public static AppSparkContext getInstance() {
		if (Objects.nonNull(appSparkContext)) {
			return appSparkContext;
		}
		throw new AppSparkContextNotInitException(
				"App Spark Context has not initialized. To initialize call AppSparkContext.init() method.");
	}

	public static void init(Properties properties) {
		if (Objects.isNull(appSparkContext)) {
			synchronized (SYNCHRONIZATION_OBJECT) {
				if (Objects.isNull(appSparkContext)) {
					AppSparkContext.appSparkContext = new AppSparkContext();
					var sparkConf = new SparkConf().setAppName(properties.getProperty("appName", "xmlmapper"))
							.setMaster(properties.getProperty("master", "local"));
					AppSparkContext.sparkContext = SparkContext.getOrCreate(sparkConf);
				}
			}
		}
	}

	public JavaSparkContext getJavaSparkContext() {
		return new JavaSparkContext(AppSparkContext.sparkContext);
	}

	public SparkSession getSparkSession() {
		return new SparkSession(AppSparkContext.sparkContext);
	}

}
