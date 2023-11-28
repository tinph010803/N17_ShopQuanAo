package dao;

import entity.Enum_BangLoaiSanPham;
import entity.Enum_ChatLieu;
import entity.Enum_KichThuoc;
import entity.Enum_Mau;
import entity.Enum_NhanHieu;
import entity.KhuyenMai;
import entity.NhaCungCap;
import entity.SanPham;
import gui.thongbao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectionManager;

public class DAO_SanPham {
	public static ResultSet laySP() {
		String sql = "SELECT * FROM [dbo].[SanPham]";
		return ConnectionManager.getdata(sql);
	}
	
	public static SanPham laySPCuoi(){
		String sql = "select top 1 * from SanPham order by maSanPham desc";
		ResultSet rs = ConnectionManager.getdata(sql);
		SanPham sp = null;
		KhuyenMai km;
		try {
			while (rs.next()) {
				
				NhaCungCap ncc = DAO_NhaCungCap.layNCCTheoMa(rs.getString("maNhaCungCap").trim());

				if(rs.getString("maKhuyenMai")==null) {
					km=null;
				}
				else
					 km= DAO_KhuyenMai.layKhuyenMaiTheoMa(rs.getString("maKhuyenMai").trim());
//				
				sp = new SanPham(rs.getString(1).trim(), rs.getString(2).trim(),
						Enum_BangLoaiSanPham.fromString(rs.getString(3).trim()), rs.getDouble("giaNhap"), ncc,
						Enum_Mau.fromString(rs.getString("mau").trim()),
						Enum_ChatLieu.fromString(rs.getString("chatLieu").trim()),
						Enum_NhanHieu.fromString(rs.getString("nhanHieu").trim()), rs.getString("hinhAnh").trim(),
						Enum_KichThuoc.fromString(rs.getString("kichThuoc").trim()), rs.getInt("soLuong"), rs.getString("moTa").trim(),km);
			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("lỗi tạo sản phẩm !");
		}
		return sp;
	}

	public static ResultSet laySPTheoMa(String maSP) {
		String sql = "SELECT * FROM [dbo].[SanPham] where maSanPham =N'" + maSP
				+ "'";
		return ConnectionManager.getdata(sql);
	}
	
	public static SanPham laySanPhamTheoMa(String ma) {
		String sql = "SELECT * FROM [dbo].[SanPham] where maSanPham = '" + ma + "'";
		ResultSet rs = ConnectionManager.getdata(sql);
		SanPham sp = null;
		KhuyenMai km;
		try {
			while (rs.next()) {
				
				NhaCungCap ncc = DAO_NhaCungCap.layNCCTheoMa(rs.getString("maNhaCungCap").trim());

				if(rs.getString("maKhuyenMai")==null) {
					km=null;
				}
				else
					 km= DAO_KhuyenMai.layKhuyenMaiTheoMa(rs.getString("maKhuyenMai").trim());
//				
				sp = new SanPham(rs.getString(1).trim(), rs.getString(2).trim(),
						Enum_BangLoaiSanPham.fromString(rs.getString(3).trim()), rs.getDouble("giaNhap"), ncc,
						Enum_Mau.fromString(rs.getString("mau").trim()),
						Enum_ChatLieu.fromString(rs.getString("chatLieu").trim()),
						Enum_NhanHieu.fromString(rs.getString("nhanHieu").trim()), rs.getString("hinhAnh").trim(),
						Enum_KichThuoc.fromString(rs.getString("kichThuoc").trim()), rs.getInt("soLuong"), rs.getString("moTa").trim(),km);
			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("lỗi tạo sản phẩm !");
		}
		return sp;

	}


	public static boolean them(SanPham sp) {
		String sql = "INSERT INTO [dbo].[SanPham] "
				+ "([maSanPham],[tenSanPham],[loai],[maNhaCungCap],[moTa],[chatLieu],[mau],[kichThuoc],[giaNhap],[soLuong],[nhanHieu],[hinhAnh],[maKhuyenMai]) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		System.out.println(sp.getNhaCC().getMaNhaCungCap());
		int kq = 0;
		try {
			ConnectionManager connection = new ConnectionManager(); // Lấy kết
																	// nối từ
																	// ConnectionManager
																	// của bạn
			Connection conn = connection.conn;
			PreparedStatement preparedStatement = conn.prepareStatement(sql);

			// Thiết lập các giá trị cho truy vấn
			preparedStatement.setString(1, sp.getMaSanPham());
			preparedStatement.setString(2, sp.getTenSanPham());
			preparedStatement.setString(3, sp.getLoai().getValue());
			preparedStatement.setString(4, sp.getNhaCC().getMaNhaCungCap());
			preparedStatement.setString(5, sp.getMoTa());
			preparedStatement.setString(6, sp.getChatLieu().getValue());
			preparedStatement.setString(7, sp.getMau().getValue());
			preparedStatement.setString(8, sp.getKichThuoc().getValue());
			preparedStatement.setDouble(9, sp.getGiaNhap());
			preparedStatement.setInt(10, sp.getSoLuong());
			preparedStatement.setString(11, sp.getNhanHieu().getValue());
			preparedStatement.setString(12, sp.getHinhAnh());
			preparedStatement.setNull(13, java.sql.Types.NCHAR); // Đặt giá trị
																	// NULL cho
																	// maKhuyenMai

			// Thực hiện truy vấn
			kq = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(); // Xử lý lỗi ở đây
		}
		if (kq > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static void xoa(String maSP) {
		String sql = "DELETE FROM SanPham WHERE maSanPham='"
				+ maSP + "'";
		ConnectionManager.executeTruyVan(sql);
	}

	public static void sua(SanPham sp) {
		String sql = "UPDATE [dbo].[SanPham] " + "   SET [tenSanPham] = N'"
				+ sp.getTenSanPham() + "' " + "      ,[giaNhap] ="
				+ sp.getGiaNhap() + " " + "      ,[soLuong] ='"
				+ sp.getSoLuong() + "' " + " WHERE maSanPham='"
				+ sp.getMaSanPham() + "'";
		int kq = ConnectionManager.executeTruyVan(sql);
		if (kq > 0) {
			thongbao.thongbao("Sửa thành công", "");
		} else {
			thongbao.thongbao("Sửa không thành công", "");
		}
	}
	
	public static void capNhatSoLuong(SanPham sp, int soLuong) {
		String sql = "UPDATE [dbo].[SanPham] " 
					+ "   SET [soLuong] = "
				+ (sp.getSoLuong() - soLuong) + " WHERE maSanPham='"
				+ sp.getMaSanPham() + "'";
		ConnectionManager.executeTruyVan(sql);
//		int kq = ConnectionManager.executeTruyVan(sql);
//		if (kq > 0) {
//			thongbao.thongbao("Sửa thành công", "");
//		} else {
//			thongbao.thongbao("Sửa không thành công", "");
//		}
	}
	
	public static ResultSet timKiem(String tukhoa, String loai) {
		String sql = "SELECT * FROM SanPham WHERE (tenSanPham LIKE N'%"
				+ tukhoa + "%' OR maSanPham LIKE N'%" + tukhoa
				+ "%' OR maNhaCungCap LIKE N'%" + tukhoa
				+ "%' OR giaNhap LIKE N'%" + tukhoa + "%')";

		if (!loai.trim().equalsIgnoreCase("Tất cả")) {
			// Nếu loại sản phẩm không phải "Tất cả", thêm điều kiện vào câu
			// truy vấn
			sql += " AND loai = N'" + loai + "'";
		}
		 sql += " AND maKhuyenMai IS NULL";

		return ConnectionManager.getdata(sql);
	}

	public static ResultSet timKiem(String tukhoa, String loai, String gia, String mau, String chatLieu) {
		String sql = "select *from SanPham where tenSanPham like N'%" + tukhoa + "%'";
//				+ "%' or MaSanPham like N'%" + tukhoa
//				+ "%' or TenSanPham like N'%" + tukhoa + "%'";
		
		//Loại
		String loaiCondition = "";
		if (!loai.trim().equalsIgnoreCase("Tất cả")) {
			loaiCondition = "loai = " + "N'" + loai + "'";
		}
//		if (loai.trim().equalsIgnoreCase("Áo")) {
//			loaiCondition = "loai = Áo";
//		}else if (loai.trim().equalsIgnoreCase("Quần")) {
//			loaiCondition = "loai = Quần";
//		}else if (loai.trim().equalsIgnoreCase("Váy")) {
//			loaiCondition = "loai = Váy";
//		}else if (loai.trim().equalsIgnoreCase("Đầm")) {
//			loaiCondition = "loai = Đầm";
//		}
		if (!loaiCondition.isEmpty()) {
			sql += " AND " + loaiCondition;
		}
		//Gía
		String giaCondition = "";
		if (gia.trim().equals("Dưới 150,000đ")) {
			giaCondition = "giaNhap < 60000";
		}else if(gia.trim().equals("150,000đ - 300,000đ")){
			giaCondition = "giaNhap >= 60000 and giaNhap <= 120000";
		}else if(gia.trim().equals("300,000đ - 500,000đ")){
			giaCondition = "giaNhap >= 120000 and giaNhap <= 200000";
		}else if(gia.trim().equals("Trên 500,000đ")){
			giaCondition = "giaNhap > 200000";
		}
		if (!giaCondition.isEmpty()) {
			sql += " AND " + giaCondition;
		}
		
		//Màu
		String mauCondition = "";
		if (!mau.trim().equalsIgnoreCase("Tất cả")) {
			mauCondition = "mau = " + "N'" + mau + "'";
		}
		if (!mauCondition.isEmpty()) {
			sql += " AND " + mauCondition;
		}
		 
		//Chất liệu
		String clCondition = "";
		if (!chatLieu.trim().equalsIgnoreCase("Tất cả")) {
			clCondition = "chatLieu = " + "N'" + chatLieu + "'";
		}
		if (!clCondition.isEmpty()) {
			sql += " AND " + clCondition;
		}
		
		return ConnectionManager.getdata(sql);
	}
	
	
	
	
	
	
	
	public static ResultSet timKiemThongKe(String tukhoa, String loai) {
        String sql = "SELECT * FROM SanPham WHERE (tenSanPham LIKE N'%" + tukhoa
                + "%' OR maSanPham LIKE N'%" + tukhoa
                + "%' OR soluong LIKE N'%" + tukhoa
                + "%' OR giaNhap LIKE N'%" + tukhoa + "%')";

        if (!loai.trim().equalsIgnoreCase("Tất cả")) {
            // Nếu loại sản phẩm không phải "Tất cả", thêm điều kiện vào câu truy vấn
            sql += " AND loai = N'" + loai + "'";
        }
//        sql += " AND maKhuyenMai IS NULL";

        // Tính tổng giá trị của sản phẩm tồn kho
        sql = "SELECT SUM(giaNhap * soluong) AS tongTien FROM (" + sql + ") AS ThongKeTonKho";

        return ConnectionManager.getdata(sql);
    }
	
	
	public static void doDuLieuThongKe(JTable table) {
	    // Thực hiện kết nối với SQL Server
	    
		ConnectionManager connectionManager = new ConnectionManager();
		Connection conn = connectionManager.conn;
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
	    // Kiểm tra xem kết nối có thành công hay không
	    if (conn != null) {
	        try {
	            // Thực hiện truy vấn SQL để tính toán số lượng đã bán và tổng tiền
	            Statement statement = conn.createStatement();
	            
//	            String query = "SELECT c.maSanPham, p.tenSanPham, p.loai,SUM(c.soLuong) AS soLuongDaBan, SUM(c.soLuong * p.giaNhap) AS tongTien "
//	                        + "FROM ChiTietHoaDon c "
//	                        + "JOIN SanPham p ON c.maSanPham = p.maSanPham "
//	                        + "GROUP BY c.maSanPham, p.tenSanPham, p.loai";
	            String query = "SELECT c.maSanPham, p.tenSanPham, p.giaNhap, p.loai,SUM(c.soLuong) AS soLuongDaBan "
                        + "FROM ChiTietHoaDon c "
                        + "JOIN SanPham p ON c.maSanPham = p.maSanPham "
                        + "GROUP BY c.maSanPham, p.tenSanPham,p.giaNhap, p.loai";
	            ResultSet resultSet = statement.executeQuery(query);

	            DefaultTableModel model = (DefaultTableModel) table.getModel();

	            // Xóa dữ liệu cũ trong bảng (nếu có)
	            model.setRowCount(0);

	            // Lặp qua kết quả truy vấn và thêm dữ liệu vào bảng
	            while (resultSet.next()) {
	                String maSanPham = resultSet.getString("maSanPham");
	                SanPham sp = new SanPham(maSanPham);
	                sp.setGiaNhap(resultSet.getDouble("giaNhap"));
	                String tenSanPham = resultSet.getString("tenSanPham");
	                String loai = resultSet.getString("loai");
	                int soLuongDaBan = resultSet.getInt("soLuongDaBan");
	                double tongTien = sp.getGiaBan() * soLuongDaBan;

	                Object[] rowData = { maSanPham, tenSanPham, loai,soLuongDaBan, decimalFormat.format(tongTien) };
	                model.addRow(rowData);
	            }

	            // Đóng kết nối
	            statement.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public static void doDuLieuThongKeTonKho(JTable table) {
	    ConnectionManager connectionManager = new ConnectionManager();
	    Connection conn = connectionManager.conn;
	    DecimalFormat decimalFormat = new DecimalFormat("#,###");
	    if (conn != null) {
	        try {
	            Statement statement = conn.createStatement();
	            String query = "SELECT maSanPham, tenSanPham, loai, giaNhap, soLuong FROM SanPham GROUP BY maSanPham, tenSanPham, loai, giaNhap, soLuong";
	            ResultSet resultSet = statement.executeQuery(query);

	            DefaultTableModel model = (DefaultTableModel) table.getModel();
	            model.setRowCount(0);

	            while (resultSet.next()) {
	                String maSanPham = resultSet.getString("maSanPham");
	                SanPham sp = new SanPham(maSanPham);
	                sp.setGiaNhap(resultSet.getDouble("giaNhap"));
	                String tenSanPham = resultSet.getString("tenSanPham");
	                String loai = resultSet.getString("loai");
	                int soLuong = resultSet.getInt("soLuong");
	                double tongTien = sp.getGiaBan() * soLuong;

	                Object[] rowData = { maSanPham, tenSanPham,loai, soLuong, decimalFormat.format(tongTien) };
	                model.addRow(rowData);
	            }

	            statement.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	public static double getTongTienTatCaSanPhamDaBan() {
	    double totalPrice = 0;
	    ConnectionManager connectionManager = new ConnectionManager();
	    Connection conn = connectionManager.conn;
	    try {
	        String query = "SELECT SUM(c.soLuong * p.giaNhap) AS TotalPrice "
	                     + "FROM ChiTietHoaDon c "
	                     + "JOIN SanPham p ON c.maSanPham = p.maSanPham";
	        PreparedStatement statement = conn.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            totalPrice = resultSet.getDouble("TotalPrice");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return totalPrice;
	}
	
	public static double getTongTienTatCaSanPhamConTonKho() {
	    double totalPrice = 0;
	    ConnectionManager connectionManager = new ConnectionManager();
	    Connection conn = connectionManager.conn;
	    try {
	        String query = "SELECT SUM(soLuong * giaNhap) AS TotalPrice FROM SanPham WHERE soLuong > 0";
	        PreparedStatement statement = conn.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            totalPrice = resultSet.getDouble("TotalPrice");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return totalPrice;
	}
	
	public static int getTongSoSPDaBan() {
	    int totalProducts = 0;
	    ConnectionManager connectionManager = new ConnectionManager();
	    Connection conn = connectionManager.conn;
	    try {
	        String query = "SELECT COUNT(DISTINCT maSanPham) AS TotalProducts FROM ChiTietHoaDon";
	        PreparedStatement statement = conn.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            totalProducts = resultSet.getInt("TotalProducts");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return totalProducts;
	}
	
	public static int getTongSoSPConTonKho() {
	    int totalProducts = 0;
	    ConnectionManager connectionManager = new ConnectionManager();
	    Connection conn = connectionManager.conn;
	    try {
	        String query = "SELECT COUNT(DISTINCT maSanPham) AS TotalProducts FROM SanPham WHERE soLuong > 0";
	        PreparedStatement statement = conn.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            totalProducts = resultSet.getInt("TotalProducts");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return totalProducts;
	}
	
	public static int getSoLuongSanPhamDaBan() {
		int totalQuantity = 0;
		ConnectionManager connectionManager = new ConnectionManager();
		Connection conn = connectionManager.conn;
		// Thực hiện kết nối đến cơ sở dữ liệu
		try {

			String query = "SELECT SUM(soLuong) AS TotalQuantity FROM ChiTietHoaDon";
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				totalQuantity = resultSet.getInt("TotalQuantity");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return totalQuantity;
	}
	
	public static int getSoLuongSanPhamConTonKho() {
	    int totalQuantity = 0;
	    ConnectionManager connectionManager = new ConnectionManager();
	    Connection conn = connectionManager.conn;
	    // Thực hiện kết nối đến cơ sở dữ liệu
	    try {
	        String query = "SELECT SUM(soLuong) AS TotalQuantity FROM SanPham WHERE soLuong > 0";
	        PreparedStatement statement = conn.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            totalQuantity = resultSet.getInt("TotalQuantity");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return totalQuantity;
	}
	

	
	
	
	
	
}
