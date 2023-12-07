/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Sale;
import facades.SaleFacade;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import tools.KeyboardInput;

/**
 *
 * @author nikit
 */
public class SaleManager {
     private final Scanner scanner; 
     private final SaleFacade saleFacade;

    public SaleManager(Scanner scanner) {
        this.scanner = scanner;
        this.saleFacade = new SaleFacade() {} ;
    }

    public List<Integer> whenSaleCompany() {
         System.out.println("-----List sales company ------");
         System.out.println("\n");
         List<Sale> sale = saleFacade.findAll();
         List<Integer> arraySaleId = new ArrayList<>();
         LocalDate currentDate = LocalDate.now(); // Получаем текущую дату
        for (int i = 0; i < sale.size(); i++) {
            System.out.println((i + 1) + ". " + sale.get(i).getName());
            System.out.println("   Start Date: " + sale.get(i).getDateStart());
            System.out.println("   End Date: " + sale.get(i).getDateEnd());
            
            // Преобразуем Date в LocalDate
            LocalDate startDate = sale.get(i).getDateStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = sale.get(i).getDateEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // Вычисляем разницу между текущей датой и датой начала акции
            long daysUntilSale = ChronoUnit.DAYS.between(currentDate, startDate);
            long monthsUntilSale = ChronoUnit.MONTHS.between(currentDate, startDate);
            long daysUntilSaleEnd = ChronoUnit.DAYS.between(currentDate, endDate);
            
            if (daysUntilSale<=31&&daysUntilSale>0) {
                System.out.println("   Days until sale: " + daysUntilSale + " days");
            }
            if(daysUntilSale<0){
                 System.out.println("   Days until end sale: " + daysUntilSaleEnd + " days");
            }
            if (daysUntilSale>31) {
                System.out.println("   Months until sale: " + monthsUntilSale + " months");
            }
                
                        
            
            arraySaleId.add(sale.get(i).getId().intValue());
        }
        System.out.println("\n");
         return arraySaleId;
    }

    public void addCompany() {
        Sale sale = new Sale();
        System.out.print("Введите название скидочной компании: ");
        String companyName = scanner.nextLine();
        sale.setName(companyName);

        System.out.print("Введите день начала компании: ");
        int startDay = (KeyboardInput.inputNumber(1, 31));
        System.out.print("Введите месяц начала компании: ");
        int startMonth = (KeyboardInput.inputNumber(1, 12));
        System.out.print("Введите год начала компании: ");
        int startYear = (KeyboardInput.inputNumber(1, 2080));
        sale.setDateStart(new Date(startYear - 1900, startMonth - 1, startDay)); // - 1900, потому что в Java год 1900 соответствует 0

        System.out.print("Введите день окончания компании: ");
        int endDay = (KeyboardInput.inputNumber(1, 31));
        System.out.print("Введите месяц окончания компании: ");
        int endMonth = (KeyboardInput.inputNumber(1, 12));
        System.out.print("Введите год окончания компании: ");
        int endYear = (KeyboardInput.inputNumber(1, 2080));
        sale.setDateEnd(new Date(endYear - 1900, endMonth - 1, endDay)); // Аналогично, вычитаем 1900

        saleFacade.create(sale); // Сохраняем объект скидочной компании в базу данных
        System.out.println("Sale company created successfully!");
    }

    public void editCompany() {
        System.out.println("Developing. try again tommorow");
    }
     
}
