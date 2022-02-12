/**
 * 
 */
package com.manoj.os.xmlm.spark;

import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema;

/**
 * @author Manoj Pawar
 *
 */
public class XmlFileLoader implements FileLoader {

	private static final String XML_FORMAT = "com.databricks.spark.xml";
	private AppSparkContext appSparkContext;

	public XmlFileLoader(AppSparkContext appSparkContext) {
		super();
		this.appSparkContext = appSparkContext;
	}

	@Override
	public Dataset<Row> getDataset(String filename, String xsdFilepath, Map<String, String> options) {
		var sparkSession = appSparkContext.getSparkSession();
		return sparkSession.read().format(XML_FORMAT).options(options).load(filename);
	}

	@Override
	public void processDataset(Dataset<Row> dataset) {
		dataset.foreach(row -> {
			IntStream.range(0, row.length()).forEach(index -> {
				var object = row.get(index);
				if (Objects.isNull(object)) {
					System.out.println("Index field " + index + " is null");
				} else {
					if (object instanceof GenericRowWithSchema) {
						var genericRowSchema = (GenericRowWithSchema) object;
						System.out.println("Index field " + index + " is " + genericRowSchema.prettyJson());
					} else {
						System.out.println("Index field " + index + " is " + object);
					}
				}
			});
		});

	}

}
