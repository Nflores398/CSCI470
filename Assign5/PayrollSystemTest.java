
/***********************************************************
 *                                                         *
 *  CSCI 470/502       Assignment 5       Summer 2022      *
 *                                                         *
 * Class   Name: Programming in Java                       *
 *                                                         *
 * Developer(s): Seraphina Nelson, Noah Flores             *
 *                                                         *
 * Due date:  07/22/2022                                   *
 *  Purpose:  This program is a employee hierarchy         *
 *  test program. Which will calc the pay for 4 types of   *
 *  employees. The promgram will also determine if the     *
 *  employee will receive a $100 birthday bonus            *
 *  for the month                                          *
 *                                                         *
 ***********************************************************/
// Employee hierarchy test program.
import java.util.InputMismatchException;
import java.util.Scanner;

public class PayrollSystemTest {
   public static void main(String[] args) {
      Date salariedDate = new Date(6, 15, 1944);
      Date hourlyDate = new Date(12, 29, 1960);
      Date commissionDate = new Date(9, 8, 1954);
      Date basePlusCommissionDate = new Date(3, 2, 1965);

      Scanner scanner = new Scanner(System.in);
      int month = 0;
      // create subclass objects
      SalariedEmployee salariedEmployee = new SalariedEmployee("John", "Smith", "111-11-1111", 800.00, salariedDate);
      HourlyEmployee hourlyEmployee = new HourlyEmployee("Karen", "Price", "222-22-2222", 16.75, 40, hourlyDate);
      CommissionEmployee commissionEmployee = new CommissionEmployee(
            "Sue", "Jones", "333-33-3333", 10000, .06, commissionDate);
      BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee(
            "Bob", "Lewis", "444-44-4444", 5000, .04, 300, basePlusCommissionDate);

      System.out.println("Employees processed individually:");

      System.out.printf("%n%s%n%s: $%,.2f%n%n",
            salariedEmployee, "earned", salariedEmployee.earnings());
      System.out.printf("%s%n%s: $%,.2f%n%n",
            hourlyEmployee, "earned", hourlyEmployee.earnings());
      System.out.printf("%s%n%s: $%,.2f%n%n",
            commissionEmployee, "earned", commissionEmployee.earnings());
      System.out.printf("%s%n%s: $%,.2f%n%n",
            basePlusCommissionEmployee,
            "earned", basePlusCommissionEmployee.earnings());

      // create four-element Employee array
      Employee[] employees = new Employee[4];

      // initialize array with Employees
      employees[0] = salariedEmployee;
      employees[1] = hourlyEmployee;
      employees[2] = commissionEmployee;
      employees[3] = basePlusCommissionEmployee;
      // loop until correct month input
      while (true) {
         try {
            System.out.print("Enter the current month (1 - 12): ");
            month = scanner.nextInt();
            // checks if month enter is in range of 1-12
            if (month > 12 || month < 1) {
               throw new Exception("Month Out of Range");
            }
            break;
            // if input isnt a int
         } catch (InputMismatchException IME) {
            System.out.println("\nBad Input!");
            scanner.next();
            continue;
         }
         // if input is out of range
         catch (Exception OFR) {
            System.out.println("\n" + OFR.getMessage());
         }
      }
      scanner.close();

      System.out.printf("Employees processed polymorphically:%n%n");

      // generically process each element in array employees
      for (Employee currentEmployee : employees) {
         System.out.println(currentEmployee); // invokes toString

         // determine whether element is a BasePlusCommissionEmployee
         if (currentEmployee instanceof BasePlusCommissionEmployee) {
            // downcast Employee reference to
            // BasePlusCommissionEmployee reference
            BasePlusCommissionEmployee employee = (BasePlusCommissionEmployee) currentEmployee;

            employee.setBaseSalary(1.10 * employee.getBaseSalary());

            System.out.printf(
                  "new base salary with 10%% increase is: $%,.2f%n",
                  employee.getBaseSalary());
         }
         // if its the current employees birth month then apply $100 bonus to pay
         if (month == currentEmployee.getEmployeeBirhtMonth()) {
            System.out.printf(
                  "earned $%,.2f plus $100.00 birthday bonus\n\n", currentEmployee.earnings());
         } else {
            System.out.printf(
                  "earned $%,.2f%n%n", currentEmployee.earnings());
         }

      }
      // get type name of each object in employees array
      for (int j = 0; j < employees.length; j++)
         System.out.printf("Employee %d is a %s%n", j,
               employees[j].getClass().getName());
   } // end main
} // end class PayrollSystemTest