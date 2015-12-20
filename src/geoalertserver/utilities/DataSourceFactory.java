package geoalertserver.utilities;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

// establishes a persistent database connection
public class DataSourceFactory {

	public static DataSource getMySQLDataSource() {
		MysqlDataSource mysqlDS = null;
		final String dbHostName = Configuration.getDbHostname();
		final String dbDatabaseName = Configuration.getDbDatabaseName();
		final String dbUsername = Configuration.getDbUsername();
		final String dbPassword = Configuration.getDbPassword();

		try {
			mysqlDS = new MysqlDataSource();
			mysqlDS.setURL(dbHostName + dbDatabaseName);
			mysqlDS.setUser(dbUsername);
			mysqlDS.setPassword(dbPassword);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		return mysqlDS;
	}
}