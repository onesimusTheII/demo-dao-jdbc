package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.dao.DAOFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program
{
	public static void main(String[] args0)
	{
		Scanner sc = new Scanner(System.in);

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

		System.out.println();

		System.out.println("==== TEST5: seller update ====");
		seller = sellerDAO.findById(1);
		seller.setName("Martha Wayne");
		sellerDAO.update(seller);
		System.out.println("Update Complete! Seller = " + seller);


		System.out.println();

		System.out.println("==== TEST5: seller update ====");
		System.out.print("Digite o Id do usuário que gostaria de deletar: ");
		sellerDAO.deleteById(sc.nextInt());
		sc.nextLine();
		System.out.println("Delete Complete!");
	}
}
