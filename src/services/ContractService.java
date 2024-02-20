package services;

import java.util.ArrayList;
import java.util.List;

import entities.Contract;
import entities.Installment;

public class ContractService {
	private PaymentService paymentService;
	
	public ContractService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public void processContract(Contract contract, Integer months) {
		List<Installment> processedInstallments = new ArrayList<>();
		
		final double grossInstallmentValue = contract.getTotalValue()/months;
		
		for (int i = 1; i<months+1; i++) {
			double interest = paymentService.interest(grossInstallmentValue, i);
			double fee = paymentService.paymentFee(grossInstallmentValue+interest);
			double netInstallmentValue = grossInstallmentValue + interest + fee;
			
			Installment inst = new Installment(contract.getDate().plusMonths(i), netInstallmentValue);
			processedInstallments.add(inst);
		}
		
		contract.setInstallments(processedInstallments);
	}
}
