/**
 * 
 */
package com.manoj.os.xmlm;

import java.util.HashMap;
import java.util.Properties;

import com.manoj.os.xmlm.spark.AppSparkContext;
import com.manoj.os.xmlm.spark.XmlFileLoader;

/**
 * @author Manoj Pawar
 *
 */
public class Application {

	private static final String ROOT_PATH = "D:\\Windows\\Users\\Manoj Pawar\\eclipse-workspace\\xml-mapper\\src\\main\\resources\\";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		var inputXmlFilename = ROOT_PATH + "customer_direct_debit_initiation_message.xml" /* "sample.xml" */;
		var xsdFilename = ROOT_PATH + "pain.008.001.03.xsd" /* "sample.xsd" */;

		var properties = new Properties();
		AppSparkContext.init(properties);

		var appSparkContext = AppSparkContext.getInstance();
		var fileLoader = new XmlFileLoader(appSparkContext);

		var map = new HashMap<String, String>();
		map.put("rootTag", "CstmrDrctDbtInitn");
		map.put("rowTag", "PmtInf");
		var dataset = fileLoader.getDataset(inputXmlFilename, xsdFilename, map);

		//dataset.printSchema();
		dataset.show();
		fileLoader.processDataset(dataset);

		/*
		 * var localFile =
		 * "D:\\Windows\\Users\\Manoj Pawar\\test-data\\apache-spark-intro.txt";
		 * ApacheSparkWordCount.printFileWordCount(localFile);
		 */
	}

}
