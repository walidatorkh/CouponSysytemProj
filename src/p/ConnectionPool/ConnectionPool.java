package p.ConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

	//private final static String CONNECTION_STRING = "jdbc:derby://localhost:1527/igordb;create=true;name=igork;password=igork";
	private final static String CONNECTION_STRING = "jdbc:derby://localhost:1527/sample;create=true;";

	private final static int POOL_SIZE = 10;

	private static Connection[] connections = new Connection[POOL_SIZE];
//	private static ConnectionsBackup[] connectionsBackup = new ConnectionsBackup[POOL_SIZE];
	/**volatile keyword here makes sure that
	*the changes made in one thread are 
	* immediately reflect in other thread*/
	private volatile static ConnectionPool connectionPool = null;
	
	
	private static int numOfUsedUpConnection = 0;

	private ConnectionPool() {
	}

	private static void initializeConnectionPool() {

		for (int i = 0; i < POOL_SIZE; i++) {

			try {

				connections[i] = DriverManager.getConnection(CONNECTION_STRING);

			} catch (SQLException e) {

				throw new RuntimeException("Can't initiate Pool");

			}

		}

	}

	public static ConnectionPool getInstance() {

		if (connectionPool == null) {

			synchronized (ConnectionPool.class) {

				connectionPool = new ConnectionPool();

				initializeConnectionPool();

			}

		}

		return connectionPool;

	}

	public synchronized Connection getConnection() {

		if (numOfUsedUpConnection == POOL_SIZE) {

			throw new RuntimeException("Not enough connections in the pool!!!");

		}

		Connection connection = connections[numOfUsedUpConnection];

		numOfUsedUpConnection++;

		return connection;

	}

	public synchronized void returnToPool(Connection connection) {

		if (numOfUsedUpConnection > 0) {

			numOfUsedUpConnection--;

		}
	}
	
	public void closeAllConnections() throws SQLException {
		for (Connection connections : connections) {
			if (connections != null) {
				connections.close();
			}
			System.out.println("Connection pool shutdown");
	}
}
}