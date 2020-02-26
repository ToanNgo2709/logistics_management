package Models;

import java.sql.*;

public class DBConnection{
	private static String url = "jdbc:sqlserver://localhost:1433;databaseName=LOGISTICS_MANAGEMENT;user=sa;password=sa";

	public DBConnection() {
		
	}
	public static final Connection Connect() {
		try {
			Connection conn;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url);
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static final ResultSet CallProc(String storeName) {
		try {
			//not parameter
			CallableStatement state = Connect().prepareCall("{ call " + storeName + "}");
			return state.executeQuery();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public static final ResultSet CallProc(String storeName, String[] parameters) {
		try {
			String str = "{ call" + storeName + "(";
			for (int i = 0; i < parameters.length; i++) {
				if (i + 1 == parameters.length) {
					str += "?";
				}else {
					str += "?,";
				}
			}
			str += ")}";
			CallableStatement state = Connect().prepareCall(str);
			//parse parameter by loop
			for (int i = 0; i < parameters.length; i++) {
				state.setString(i+1, parameters[i]);
			}
			return state.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public static final boolean CallProcExec(String storeName, String[] parameters)
	{
		try {
			String str = "{ call" + storeName + "(";
			for (int i = 0; i < parameters.length; i++) {
				if (i + 1 == parameters.length) {
					str += "?";
				}else {
					str += "?,";
				}
			}
			str += ")}";
			CallableStatement state = Connect().prepareCall(str);
			//parse parameter by using loop
			for (int i = 0; i < parameters.length; i++) {
				state.setString(i+1, parameters[i]);
			}
			return state.execute();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	public static final Object CallProcScala(String storeName, String[] parameters) {
		Object obj = null;
		try {
			String str = "{ call" + storeName + "(";
			for (int i = 0; i < parameters.length; i++) {
				if (i+1 == parameters.length) {
					str += "?";
				}else {
					str += "?,";
				}
			}
			str += ")}";
			CallableStatement state = Connect().prepareCall(str);
			for (int i = 0; i < parameters.length; i++) {
				state.setString(i+1, parameters[i]);
			}
			state.registerOutParameter(1, java.sql.Types.JAVA_OBJECT);
			state.execute();
			obj = state.getObject(parameters.length+1);
			return obj;
		} catch (SQLException e) {
			// TODO: handle exception\
			e.printStackTrace();
		}
		return obj;
	}
	public static final ResultSet Query(String strQuery) {
		Statement stmt;
		try {
			stmt = Connect().createStatement();
			ResultSet rs = stmt.executeQuery(strQuery);
			return rs;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public static final ResultSet Query(String strQuery, String[] parameters) {
		PreparedStatement stmt;
		try {
			stmt = Connect().prepareStatement(strQuery);
			for (int i = 0; i < parameters.length; i++) {
				stmt.setString(i+1, parameters[i]);
			}
			ResultSet rs = stmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public static final Statement Insert() {
		Statement stmt;
		try {
			stmt = Connect().createStatement();
			return stmt;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public static final int Create(String strQuery, String[] arrParameters) {
		PreparedStatement state;
		try {
			state = Connect().prepareStatement(strQuery);
			for (int i = 0; i < arrParameters.length; i++) {
				state.setString(i+1, arrParameters[i]);
			}
			int rs = state.executeUpdate();
			return rs;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}
}
