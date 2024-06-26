package model.dao.impl;

import db.DB;
import db.DbException;
import java.sql.*;
import java.util.List;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDAO
{
	private Connection conn;

	public SellerDaoJDBC(Connection conn)
	{
		this.conn = conn;
	}
	@Override
	public void insert(Seller seller)
	{

	}

	@Override
	public void update(Seller seller)
	{

	}

	@Override
	public void deleteById(Integer id)
	{

	}

	@Override
	public Seller findById(Integer id)
	{
		PreparedStatement st = null;
		ResultSet rs = null;

		try
		{
			st = conn.prepareStatement(
				"SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.id "
					+ "WHERE seller.id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next())
			{
				Department department = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, department);
				return seller;
			}
			return null;

		}
		catch (SQLException e)
		{
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException
	{
		Department department = new Department();
		department.setId(rs.getInt("DepartmentId"));
		department.setName(rs.getString("DepName"));
		return department;
	}

	private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException
	{
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setDepartment(department);

		return seller;
	}

	@Override
	public List<Seller> findAll()
	{
		return null;
	}
}
