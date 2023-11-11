package program;

import model.entities.CarRental;
import model.entities.Invoice;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner tec = new Scanner(System.in);
        Locale.setDefault(Locale.US);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Enter details rental");
        System.out.println("Car model: ");
        String carModel = tec.nextLine();
        System.out.println("Enter date withdrawl: dd/MM/yyyy HH:mm ");
        LocalDateTime withdrawl = LocalDateTime.parse(tec.nextLine(), dtf);
        System.out.println("Enter date return: dd/MM/yyyy HH:mm ");
        LocalDateTime dateReturn = LocalDateTime.parse(tec.nextLine(), dtf);
        CarRental cr = new CarRental(withdrawl, dateReturn, new Vehicle(carModel));
        System.out.println("Enter price hours:  ");
        double priceHours = tec.nextDouble();
        System.out.println("Enter price day: ");
        double priceDay = tec.nextDouble();
        RentalService rentalService = new RentalService(priceHours, priceDay,new BrazilTaxService());
        rentalService.processInvoice(cr);
        System.out.println("---- INVOICE ---- ");
        System.out.println(cr.getInvoice().getBasicPayment());
        System.out.println(cr.getInvoice().getTax());
        System.out.println(cr.getInvoice().getTotalPayment());
        System.out.println("----------------");




    }
}
