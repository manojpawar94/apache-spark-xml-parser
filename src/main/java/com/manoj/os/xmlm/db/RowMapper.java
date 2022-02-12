/**
 * 
 */
package com.manoj.os.xmlm.db;

/**
 * @author Manoj Pawar
 *
 */
@FunctionalInterface
public interface RowMapper {

	<S, T> T map(S source);
}
