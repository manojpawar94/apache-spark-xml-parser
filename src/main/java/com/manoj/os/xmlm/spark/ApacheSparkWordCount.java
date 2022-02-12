package com.manoj.os.xmlm.spark;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApacheSparkWordCount {

	public void printFileWordCount(String filename) {
		var sparkContext = AppSparkContext.getInstance().getJavaSparkContext();
		// loading the file
		var lines = sparkContext.textFile(filename);

		// transformations
		var words = lines.filter(StringUtils::isNoneEmpty).flatMap(
				line -> Arrays.asList(line.strip().replace("\\p{Punct}", "").split(StringUtils.SPACE)).iterator());

		// counting words
		var wordCounts = words.countByValue();
		var sorted = wordCounts.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());

		for (var entry : sorted) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
}
