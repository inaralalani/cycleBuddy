public class Main {

    // Main should probably go here or in TextApplication
    public static void main(String[] args) {

        // Generate 10 random people and print them
        for (int i = 0; i < 10; i++) {
            System.out.println(Factory.createRandomPerson());
        }
    }

}
