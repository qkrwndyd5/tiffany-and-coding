package com.itwill.shop.product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.itwill.shop.common.DataSourceFactory;

public class ProductDao {
	/*
	 * data Source 멤버필드 선언
	 */
	private DataSource dataSource;
	/*
	 * productDao 생성자 선언 후 properties 받을 준비
	 */
	public ProductDao() throws Exception {
		dataSource=DataSourceFactory.getDataSource();
	}
	/*
	 * 상품 옵션 update
	 */
	public int update(int p_no, String p_option) throws SQLException {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_OPTION_UPDATE);
		pstmt.setString(1, p_option);
		pstmt.setInt(2, p_no);	
		int rowCount = pstmt.executeUpdate();
		pstmt.close();
		con.close();
		return rowCount;
	}
	
	
	/*
	 * 상품 번호로 검색
	 */
	public Product findByPrimaryKey(int p_no) throws Exception {
		Product product = null;
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_SELECT_BY_NO);
		pstmt.setInt(1, p_no);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			product = new Product(
							rs.getInt("p_no"),
							rs.getString("p_name"),
							rs.getInt("p_price"),
							rs.getString("p_desc"),
							rs.getString("p_image"),
							rs.getString("p_option"),
							rs.getInt("ca_no"));
		}
		pstmt.close();
		con.close();
		return product;
	}
	/*
	 * 상품 전체 리스트
	 */
	public List<Product> findAll() throws Exception{
		List<Product> productList = new ArrayList<Product>();
		
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_SELECT_ALL);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Product product = new Product(
									rs.getInt("p_no"),
									rs.getString("p_name"),
									rs.getInt("p_price"),
									rs.getString("p_desc"),
									rs.getString("p_image"),
									rs.getString("p_option"),
									rs.getInt("ca_no"));
			productList.add(product);
		}
		pstmt.close();
		con.close();
		return productList;
	}
	
	/*
	 * 카테고리 번호로 아이템 리스트 뽑기
	 */
	public List<Product> searchCaNo(int ca_no) throws Exception{
		List<Product> searchProductList = new ArrayList<Product>();
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_SEARCH_CATEGORY_NO);
		pstmt.setInt(1, ca_no);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Product product = new Product(
									rs.getInt("p_no"),
									rs.getString("p_name"),
									rs.getInt("p_price"),
									rs.getString("p_desc"),
									rs.getString("p_image"),
									rs.getString("p_option"),
									rs.getInt("ca_no"));
			searchProductList.add(product);
		}
		pstmt.close();
		con.close();
		return searchProductList;
	}
	/*
	 * 전체 리스트에서 카테고리 검색 시 해당 카테고리 아이템 전부 보여주기
	 */
	public List<Product> searchCategory(String keyword) throws Exception{
		List<Product> searchProductList = new ArrayList<Product>();
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_SEARCH_CATEGORY);
		pstmt.setString(1, keyword);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Product product = new Product(
									rs.getInt("p_no"),
									rs.getString("p_name"),
									rs.getInt("p_price"),
									rs.getString("p_desc"),
									rs.getString("p_image"),
									rs.getString("p_option"),
									rs.getInt("ca_no"));
			searchProductList.add(product);
		}
		pstmt.close();
		con.close();
		return searchProductList;
	}
	
	/*
	 * 상품 이름으로 검색
	 */
	public List<Product> searchProductName(String keyword) throws Exception{
		List<Product> searchProductList = new ArrayList<Product>();
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_SEARCH_P_NAME);
		pstmt.setString(1, "%"+keyword+"%");
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Product product = new Product(
									rs.getInt("p_no"),
									rs.getString("p_name"),
									rs.getInt("p_price"),
									rs.getString("p_desc"),
									rs.getString("p_image"),
									rs.getString("p_option"),
									rs.getInt("ca_no"));
			searchProductList.add(product);
		}
		pstmt.close();
		con.close();
		return searchProductList;
	}
	
	public List<Product> priceSortDesc() throws Exception{
		List<Product> categorySortList = new ArrayList<Product>();
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_PRICE_DESC);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Product product = new Product(
							rs.getInt("p_no"),
							rs.getString("p_name"),
							rs.getInt("p_price"),
							rs.getString("p_desc"),
							rs.getString("p_image"),
							rs.getString("p_option"),
							rs.getInt("ca_no"));
			categorySortList.add(product);
		}
		pstmt.close();
		con.close();
		return categorySortList;
	}
	
	public List<Product> priceSortAsc() throws Exception{
		List<Product> categorySortList = new ArrayList<Product>();
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_PRICE_ASC);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Product product = new Product(
							rs.getInt("p_no"),
							rs.getString("p_name"),
							rs.getInt("p_price"),
							rs.getString("p_desc"),
							rs.getString("p_image"),
							rs.getString("p_option"),
							rs.getInt("ca_no"));
			categorySortList.add(product);
		}
		pstmt.close();
		con.close();
		return categorySortList;
	}
	
	
	public List<Product> categorySortDesc(int ca_no) throws Exception{
		List<Product> categorySortList = new ArrayList<Product>();
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_CATEGORY_PRICE_DESC);
		pstmt.setInt(1, ca_no);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Product product = new Product(
							rs.getInt("p_no"),
							rs.getString("p_name"),
							rs.getInt("p_price"),
							rs.getString("p_desc"),
							rs.getString("p_image"),
							rs.getString("p_option"),
							rs.getInt("ca_no"));
			categorySortList.add(product);
		}
		pstmt.close();
		con.close();
		return categorySortList;
	}
	
	public List<Product> categorySortAsc(int ca_no) throws Exception{
		List<Product> categorySortList = new ArrayList<Product>();
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_CATEGORY_PRICE_ASC);
		pstmt.setInt(1, ca_no);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Product product = new Product(
							rs.getInt("p_no"),
							rs.getString("p_name"),
							rs.getInt("p_price"),
							rs.getString("p_desc"),
							rs.getString("p_image"),
							rs.getString("p_option"),
							rs.getInt("ca_no"));
			categorySortList.add(product);
		}
		pstmt.close();
		con.close();
		return categorySortList;
	}
	
	/*
	 * 키워드 내 정렬
	 */
	public List<Product> searchSortAsc(String keyword) throws Exception{
		List<Product> categorySortList = new ArrayList<Product>();
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_SEARCH_PRICE_ASC);
		pstmt.setString(1, "%"+keyword+"%");
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Product product = new Product(
							rs.getInt("p_no"),
							rs.getString("p_name"),
							rs.getInt("p_price"),
							rs.getString("p_desc"),
							rs.getString("p_image"),
							rs.getString("p_option"),
							rs.getInt("ca_no"));
			categorySortList.add(product);
		}
		pstmt.close();
		con.close();
		return categorySortList;
	}
	
	public List<Product> searchSortDesc(String keyword) throws Exception{
		List<Product> categorySortList = new ArrayList<Product>();
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_SEARCH_PRICE_DESC);
		pstmt.setString(1, "%"+keyword+"%");
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Product product = new Product(
							rs.getInt("p_no"),
							rs.getString("p_name"),
							rs.getInt("p_price"),
							rs.getString("p_desc"),
							rs.getString("p_image"),
							rs.getString("p_option"),
							rs.getInt("ca_no"));
			categorySortList.add(product);
		}
		pstmt.close();
		con.close();
		return categorySortList;
	}
}
