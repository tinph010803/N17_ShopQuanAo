package connectDB;

import java.sql.*;

//class connection để
//kết nối csdl 
//thực hiện các lệnh truy vấn 
//insert update delete
//select

public class ConnectionManager {
	private static String DB_URL = "jdbc:sqlserver://localhost;"
			+ "databaseName=SFSHOP;";
	private static String USER_NAME = "sa";
	private static String PASSWORD = "123";
	public static Connection conn = null;

	static PreparedStatement preparedStatement(String cauTruyVan) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public ConnectionManager() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			System.out.println("connect successfully(kết nối thành công)");
		} catch (ClassNotFoundException ex) {
			System.out.println("lỗi thiếu thư viện");
		} catch (SQLException ex) {
			System.out.println("lỗi kết nối csdl");
		}

	}

	// hàm thực thi câu lệnh select
	public static ResultSet getdata(String cauTruyVan) {
		try {
//			Statement stm=conn.createStatement();
            //thực thicaau truy vấn select dc truyền vào từ
            //tham số cautruyvan
            //trả về kết quả là ResultSet
//            ResultSet rs=stm.executeQuery(cauTruyVan);
//            return rs;//trả về resultset nếu thành công
			
			PreparedStatement preparedStatement = conn
					.prepareStatement(cauTruyVan);
			ResultSet rs = preparedStatement.executeQuery();
			return rs;
		} catch (SQLException ex) {
			System.out.println("lỗi truy vấn");
			return null;
		}

	}

	// hàm thực thi 3 câu lệnh insert delete update
	public static int executeTruyVan(String cauTruyVan) {
		try {
//			Statement stm = conn.createStatement();
//			return stm.executeUpdate(cauTruyVan);
			PreparedStatement preparedStatement = conn.prepareStatement(cauTruyVan);
            return preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("lỗi thực thi truy vấn");
			return -1;
		}
	}

}
