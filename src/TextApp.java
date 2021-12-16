import java.util.Calendar;
import java.util.Scanner;

/**
 * <b>TextApp</b>
 * <p>
 * The TextApp class contains all prompts of user interactions, 
 * which include a choice of how the user wants to interact with 
 * the application, a prompt to enter their information as 
 * pertaining to the Factor effects, and a prompt to read more 
 * information about the Factor effects on cycle length.
 * </p>
 *
 * @authors Inara Lalani, Akansha Malik, Amanda Nixon
 * @version 1.0, 12-Dec-21
 **/
public class TextApp {
    private Person aPerson;
    private Scanner userInput = new Scanner(System.in);

    /**
     * <p>
     * Calls the run method to start the application
     * </p>
     */
    public static void main(String[] args) {
        TextApp test = new TextApp();
        test.run();
    }

    /**
     * <p>
     * Prompts the user to select how they want to 
     * interact with the application by choosing to run a 
     * visualization, enter a factor to learn more about, or quit the application.
     * The application can exit here.
     * </p>
     */
    public void run() {

        System.out.println(
                "Welcome to cycleBuddy! cycleBuddy calculates how lifestyle factors \nmay affect an individual's menstrual cycle. cycleBuddy makes these calculations \nusing values derived from scientific literature. \n\ncycleBuddy can tell you more about how it makes its calculations \nand can visually represent an individual's next period after calculating their \ncycle length using inputted or random information");
        System.out.println("----------------------------");
        System.out.println(
                "What would you like to do? You can exit, \nrun the visualizer, or learn more about the factors \ncycleBuddy uses to make its calculations.");
        System.out.println("----------------------------");
        System.out.println("Options: Quit, Visualize, Factors \n(you can input the first letter of the option if preferred)");
        System.out.println("----------------------------");
        String toDo = userInput.nextLine();

        while (!toDo.equalsIgnoreCase("Quit") && !toDo.equalsIgnoreCase("Q")) {

            if (toDo.equalsIgnoreCase("Visualize") || toDo.equalsIgnoreCase("V"))
                runVisualizer();

            if (toDo.equalsIgnoreCase("Factors") || toDo.equalsIgnoreCase("F"))
                runFactorInfo();

            System.out.println("----------------------------");
            System.out.println("What would you like to do next?");
            System.out.println("Options: Quit, Visualize, Factors");
            toDo = userInput.nextLine();

        }
        System.out.println("Goodbye! Thank you for using cycleBuddy.");
        userInput.close();
        System.exit(0);
    }
    
    /**
     * <p>
     * Prompts the user to enter information for their
     * age, alcohol consumption, physical activity, and the
     * number of days since their last period.
     * </p>
     */
    private void enterPersonInfo() {
        System.out.println("What is your age? (Enter between 18-45)");
        int age = userInput.nextInt();
        userInput.nextLine();

        System.out.println("Do you consume more than 1 drink of alcohol per week? (yes or no)");
        String alcoholConsumption = userInput.nextLine();
        boolean alcoholBoolean = alcoholConsumption.equalsIgnoreCase("yes");

        System.out.println("How many hours of physical activity do you get in a week?");
        int physicalActivity = userInput.nextInt();
        userInput.nextLine();

        System.out.println("How many days has it been since your last period?");
        int daysSincePeriod = userInput.nextInt();
        userInput.nextLine();

        aPerson = new Person(age, alcoholBoolean, physicalActivity, daysSincePeriod);
    }

    /**
     * <p>
     * Prompts the user to select how they want to 
     * run the visualization, by either using randomly 
     * generated factors or by manually entering their
     * own factors. Elements from the periodDays and 
     * nextMonthPeriodDays arrayLists are removed after the 
     * visualization is printed.
     * </p>
     */
    private void runVisualizer() {
        System.out.println("Would you like to create a random person or enter info manually? \n(you can input the first letter of the option if preferred)");
        System.out.println("Options: Random, Manual");
        String typeOfPerson = userInput.nextLine();

        if (typeOfPerson.equalsIgnoreCase("Random") || typeOfPerson.equalsIgnoreCase("R")) {
            aPerson = Factory.createRandomPerson();
        }
        if (typeOfPerson.equalsIgnoreCase("Manual") || typeOfPerson.equalsIgnoreCase("M")) {
            enterPersonInfo();
        }

        System.out.println("----------------------------");
        System.out.println("~Let's visualize your next period~");
        System.out.println();
        Visualization.periodDayArray(aPerson, Calendar.getInstance());
        Visualization.printCalendar(Calendar.getInstance(), Visualization.periodDays);
        System.out.println();
        if (Visualization.nextMonthPeriodDays.size() >= 1)
            Visualization.printCalendar(Visualization.getNextMonthCalendar(Calendar.getInstance()), Visualization.nextMonthPeriodDays);

        System.out.println();
        System.out.println(aPerson.toString());
        Visualization.resetArrays();
    }
    
    /**
     * <p>
     * Prompts the user to select which factor they
     * would like to read general information about.
     * </p>
     */
    private void runFactorInfo() {
        System.out.println(
                "\ncycleBuddy currently uses age, alcohol consumption, and physical activity to calculate \ncycle length. Choose from any to learn more.");
        System.out.println("----------------------------");
        System.out.println("Options: Age, Alcohol, Activity");
        String factorTyped = userInput.nextLine();
        if (factorTyped.equalsIgnoreCase("Age"))
            System.out.println(Factor.ageStub().getGeneralInfo());
        if (factorTyped.equalsIgnoreCase("Alcohol"))
            System.out.println(Factor.alcoholConsumption().getGeneralInfo());
        if (factorTyped.equalsIgnoreCase("Activity"))
            System.out.println(Factor.physicalActivity(0).getGeneralInfo());

    }

}

