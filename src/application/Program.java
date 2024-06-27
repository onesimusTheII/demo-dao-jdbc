package application;

import java.util.Date;
import java.util.List;
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

		System.out.println();

		System.out.println("==== TEST2: seller findByDepartment ====");
		Department department = new Department(2, null);
		List<Seller> sellerListByDepartment = sellerDAO.findByDepartment(department);
		sellerListByDepartment.forEach(System.out::println);

		System.out.println();

		System.out.println("==== TEST3: seller findAll ====");
		List<Seller> sellerListAll = sellerDAO.findAll();
		sellerListAll.forEach(System.out::println);

		System.out.println();

		System.out.println("==== TEST4: seller insert ====");
		Seller newSeller = new Seller(null, "Greg",
			"greg@gmail.com", new Date(), 4000.0, department);
		sellerDAO.insert(newSeller);
		System.out.println("Inserted! New Id = " + newSeller.getId());
	}
}
