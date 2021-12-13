import java.util.Calendar;
import java.util.Scanner;

public class TextApp {
    private Person aPerson;
    private Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        TextApp test = new TextApp();
        test.run();
    }

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
