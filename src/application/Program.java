package application;

import java.util.Date;
import model.dao.DAOFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program
{
	public static void main(String[] args0)
	{
		Department department = new Department(1, "Books");

		Seller seller = new Seller(21, "Bob", "bob@gmail.com",  new Date(), 3000.0, department);

		SellerDAO sellerDAO = DAOFactory.createSellerDAO();

		System.out.println(seller);
	}
}
