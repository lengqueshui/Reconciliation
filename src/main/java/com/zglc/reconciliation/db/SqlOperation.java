package com.zglc.reconciliation.db;



import com.zglc.reconciliation.model.HuifuRechargeModel;
import com.zglc.reconciliation.model.WechatModel;
import com.zglc.reconciliation.model.YeepayModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class SqlOperation {
	
	private static Logger logger = LoggerFactory.getLogger(SqlOperation.class);
	

	private static String classname = "com.mysql.cj.jdbc.Driver";
	

	//private static String url = "jdbc:mysql://192.168.2.39:3306/reconciliation?useUnicode=true&characterEncoding=utf8&useSSL=false";

	private static String url = "jdbc:mysql://120.26.107.216:3306/zglc_zjcg?useUnicode=true&characterEncoding=utf8&useSSL=false";


	private static String username = "zglc_zjcg";


	private static String password = "zglc_zjcg!2017";

	public static void main(String[] args) {
		System.out.println("begin");
		Connection conn = getConnection();
		System.out.println("conn = " + conn);
	}
	
	public static Connection getConnection() {
		try {
			Class.forName(classname);
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			//logger.error(e.getMessage());
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
				ps.setString(2, model.getDate());
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

	public static void batchWechatPayRechargeInsert(Connection con, List<WechatModel> list) {
		try {
			String sql = "INSERT INTO wechatPay_recharge ( oper_date, appId, merId, childMerId, deviceId, " +
					"wxTransId, merTransId, wxUserNo, type, status, " +
					"bankNo, ccyNo, amount, hongbaoAmount, wxRefundTransId, " +
					"merRefundTransId, refundAmount, refundHongbaoAmount, refundType, refundStatus, " +
					"productName, merDataPackage, fee, feePercent, file_name, create_time)" +
					"VALUES (?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, NOW())";
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			con.setAutoCommit(false);

			for (int i = 0; i < list.size(); i++) {
				WechatModel model = list.get(i);
				ps.setTimestamp(1, new java.sql.Timestamp(model.getDate().getTime()));
				ps.setString(2, model.getAppId());
				ps.setString(3, model.getMerId());
				ps.setString(4, model.getChildMerId());
				ps.setString(5, model.getDeviceId());

				ps.setString(6, model.getWxTransId());
				ps.setString(7, model.getMerTransId());
				ps.setString(8, model.getWxUserNo());
				ps.setString(9, model.getType());
				ps.setString(10, model.getStatus());

				ps.setString(11, model.getBankNo());
				ps.setString(12, model.getCcyNo());
				ps.setLong(13, model.getAmount());
				ps.setLong(14, model.getHongbaoAmount());
				ps.setString(15, model.getWxRefundTransId());

				ps.setString(16, model.getMerRefundTransId());
				ps.setLong(17, model.getRefundAmount());
				ps.setLong(18, model.getRefundHongbaoAmount());
				ps.setString(19, model.getRefundType());
				ps.setString(20, model.getRefundStatus());

				ps.setString(21, model.getProductName());
				ps.setString(22, model.getMerDataPackage());
				ps.setLong(23, model.getFee());
				ps.setString(24, model.getFeePercent());
				ps.setString(25, model.getFileName());
				ps.addBatch();
			}

			ps.executeBatch();
			con.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
