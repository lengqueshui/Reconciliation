package com.zglc.reconciliation.db;



import com.zglc.reconciliation.model.HuifuRechargeModel;
import com.zglc.reconciliation.model.YeepayModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class SqlOperation {
	
	private static Logger logger = LoggerFactory.getLogger(SqlOperation.class);
	

	private static String classname = "com.mysql.jdbc.Driver";
	

	private static String url = "jdbc:mysql://39.106.5.215:3306/reconciliation?useUnicode=true&amp;characterEncoding=UTF-8";


	private static String username = "root";


	private static String password = "mah123456";
	
	public static Connection getConnection() {
		try {
			Class.forName(classname);
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public static void close(Connection conn) {
		if (conn != null) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}
	
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection conn, ResultSet rs) {
		close(rs);
		close(conn);
	}

	public static ResultSet query(Connection con, String querySql) {
		try {
			return con.prepareStatement(querySql).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ResultSet query(Connection con, String querySql, Object[] obj) {
		try {
			PreparedStatement pst = con.prepareStatement(querySql);
			for (int i = 0; i < obj.length; i++) {
				pst.setObject(i + 1, obj[i]);
			}
			return pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void insert(Connection con, String sql, Object[] obj) {
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void batchHuifuRechargeInsert(Connection con, List<HuifuRechargeModel> list) {
		try {
			String sql = "INSERT INTO huifu_recharge (mer_trans_id, oper_date, amount, mer_user_id, mer_user_name, " +
					"bank_name, type, fee, mer_fee_id, actual_amount, " +
					"status, status_remark, fin_date, file_name, create_time) VALUES (?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, NOW())";

			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			con.setAutoCommit(false);

			for (int i = 0; i < list.size(); i++) {
				HuifuRechargeModel model = list.get(i);
				ps.setString(1, model.getMerTransId());
				ps.setDate(2, new java.sql.Date(model.getOperDate().getTime()));
				ps.setLong(3, model.getAmount());
				ps.setString(4, model.getMerUserId());
				ps.setString(5, model.getMerUserName());

				ps.setString(6, model.getBankName());
				ps.setString(7, model.getType());
				ps.setLong(8, model.getFee());
				ps.setString(9, model.getMerFeeId());
				ps.setLong(10, model.getActualAmount());
				ps.setString(11, model.getStatus());
				ps.setString(12, model.getStatusRemark());
				ps.setString(13, model.getFinDate());
				ps.setString(14, model.getFileName());
				ps.addBatch();
			}

			ps.executeBatch();
			con.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void batchYeePayRechargeInsert(Connection con, List<YeepayModel> list) {
		try {
			String sql = "INSERT INTO yeepay_recharge (idx, oper_date, account_type, busi_type, mer_trans_id, " +
					"amount, out_amount, fee, balance, file_name,  remark, create_time) VALUES (?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, NOW())";

			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			con.setAutoCommit(false);

			for (int i = 0; i < list.size(); i++) {
				YeepayModel model = list.get(i);
				ps.setInt(1, model.getIndex());
				ps.setTimestamp(2, new java.sql.Timestamp(model.getDate().getTime()));
				ps.setString(3, model.getAccountType());
				ps.setString(4, model.getBusiType());
				ps.setString(5, model.getMerTransId());

				ps.setLong(6, model.getAmount());
				ps.setLong(7, model.getOutAmount());
				ps.setLong(8, model.getFee());
				ps.setLong(9, model.getBalance());
				ps.setString(10, model.getFileName());

				ps.setString(11, model.getRemark());
				ps.addBatch();
			}

			ps.executeBatch();
			con.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
