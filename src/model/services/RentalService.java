package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

import java.time.Duration;

public class RentalService {
    private double pricePerHour;
    private double pricePerDay;
    private Invoice invoice;
    private TaxService taxService;

    public RentalService(double pricePerHour, double pricePerDay, BrazilTaxService taxService) {
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
        this.taxService = taxService;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public TaxService getTaxService() {
        return taxService;
    }

    public void setBts(TaxService taxService) {
        this.taxService = taxService;
    }
    public void processInvoice(CarRental carRental) {
        double minutos  = Duration.between(carRental.getStart(),carRental.getFinish()).toMinutes();
        double basicPayment;
        double hours = minutos / 60;
        if (hours <= 12.0) {
            basicPayment = pricePerHour * Math.ceil(hours);
        }
        else {
            basicPayment = pricePerDay * Math.ceil(hours / 24);
        }

        double tax = taxService.tax(basicPayment);

        carRental.setInvoice(new Invoice(basicPayment, tax));
    }
}
