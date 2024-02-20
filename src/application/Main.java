package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.PaypalService;

public class Main {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter contract data: ");
		System.out.print("Number: ");
		int number = sc.nextInt();
		
		System.out.print("Date (dd/MM/yyyy): ");
		sc.nextLine();
		String date = sc.nextLine();
		
		System.out.print("Contract value: ");
		double value = sc.nextDouble();
		
		System.out.print("Number of Installments: ");
		int n = sc.nextInt();
		
		Contract cont = new Contract(number, LocalDate.parse(date, fmt), value);
		ContractService service = new ContractService(new PaypalService());
		
		service.processContract(cont, n);
		
		List<Installment> installments = cont.getInstallments();
		
		for (Installment i: installments) {
			System.out.println(i);
		}
		
		sc.close();
		
	}
}
