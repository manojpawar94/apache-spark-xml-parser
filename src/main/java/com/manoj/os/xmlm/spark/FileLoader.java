/**
 * 
 */
package com.manoj.os.xmlm.spark;

import java.util.Map;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import lombok.NonNull;

/**
 * @author Manoj Pawar
 *
 */
public interface FileLoader {

	Dataset<Row> getDataset(@NonNull String filename, String schema, Map<String, String> options);

	void processDataset(Dataset<Row> dataset);
}
