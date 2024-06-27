package model.dao.impl;

import db.DB;
import db.DbException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	public List<Seller> findByDepartment(Department department)
	{
		PreparedStatement st = null;
		ResultSet rs = null;

		try
		{
			st = conn.prepareStatement(
				"SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.id "
					+ "WHERE department.id = ? "
					+ "ORDER BY Name");
			st.setInt(1, department.getId());
			rs = st.executeQuery();

			List<Seller> sellerList = new ArrayList<>();
			Map<Integer, Department> departmentMap = new HashMap<>();

			while (rs.next())
			{
				Department newDepartment = departmentMap.get(rs.getInt("DepartmentId"));

				if(newDepartment == null)
				{
					newDepartment = instantiateDepartment(rs);
					departmentMap.put(rs.getInt("DepartmentId"), newDepartment);
				}

				sellerList.add(instantiateSeller(rs, newDepartment));
			}
			return sellerList;
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

	@Override
	public List<Seller> findAll()
	{
		PreparedStatement st = null;
		ResultSet rs = null;

		try
		{
			st = conn.prepareStatement(
				"SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.id "
					+ "ORDER BY Name");

			rs = st.executeQuery();

			List<Seller> sellerList = new ArrayList<>();
			Map<Integer, Department> departmentMap = new HashMap<>();

			while (rs.next())
			{
				Department newDepartment = departmentMap.get(rs.getInt("DepartmentId"));

				if(newDepartment == null)
				{
					newDepartment = instantiateDepartment(rs);
					departmentMap.put(rs.getInt("DepartmentId"), newDepartment);
				}

				sellerList.add(instantiateSeller(rs, newDepartment));
			}
			return sellerList;
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
}
