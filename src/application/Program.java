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
		SellerDAO sellerDAO = DAOFactory.createSellerDAO();

		System.out.println("==== TEST1: seller findById ====");
		Seller seller = sellerDAO.findById(3);

		System.out.println(seller);
	}
}
