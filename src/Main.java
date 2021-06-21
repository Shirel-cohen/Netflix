import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        Netflix netflix = initialSeries();

        while (true) {
            System.out.println("Welcome to Netflix! \n\n The operations are: \n 1: Open account \n 2: Login to an existing account\n ");
            System.out.println("Which operation do you choose? ");
            int userChoice =in.nextInt();
                userChoice = isValidChoice(userChoice);
                switch (userChoice) {
                    case Definition.CREATE_ACCOUNT:
                        netflix.openAccount();
                        break;
                    case Definition.LOGIN: {
                        System.out.println("Enter your user name");
                        String userName = scanner.next();
                        System.out.println("Enter your password");
                        String password = scanner.next();
                        User user = new User(userName, password);
                        boolean checkIfTheUserExists = netflix.checkIfTheUserExists(user.getUserName(), user.getPassword());
                        if (!checkIfTheUserExists) {
                            System.out.println("There is no user in this details \n ");
                        } else {
                            operationForLogin(netflix, user);
                        }
                        break;

                    }
                }
        }
    }
    public static int isValidChoice(int userChoice ){
        Scanner in = new Scanner (System.in);
        while (userChoice < Definition.CREATE_ACCOUNT || userChoice > Definition.LOGIN) {
                  System.out.println("Your choice dosen't exists, try again");
                    userChoice = in.nextInt();
                }
        return userChoice;
    }
    public static Netflix initialSeries() {
        User[] users = new User[0];
        Date dateOfChapter1Series1 = new Date(11, 02, 1999);
        Date dateOfChapter2Series1 = new Date(10, 03, 1999);
        Date dateOfChapter3Series1 = new Date(21, 04, 1999);
        Date dateOfChapter1Series2 = new Date(12, 02, 2000);
        Date dateOfChapter2Series2 = new Date(10, 04, 2000);
        Date dateOfChapter3Series2 = new Date(17, 05, 2000);
        Date dateOfChapter1Series3 = new Date(30, 02, 2010);
        Date dateOfChapter2Series3 = new Date(15, 03, 2010);
        Date dateOfChapter3Series3 = new Date(26, 04, 2010);
        Chapter[] BreakingBad = {
                new Chapter(1, "Chapter 1", "Walter White is a high school chemistry teacher.He lived his routine and monotonous life until one day he was informed that he had contracted a terminal illness. Knowing that his few days will free him from all traces, strengthens him and he decides to unload any burden", dateOfChapter1Series1),
                new Chapter(2, "Chapter 2", "Walter and his drug lab partner, Jesse, believe they killed the two drug dealers who threatened them. After it turns out that one of them is still alive, they decide that one of them will kill the rest and the other will dissolve the existing body in strong acid.", dateOfChapter2Series1),
                new Chapter(3, "Chapter 3", "Walter and his drug lab partner, Jesse, hide the remains of the drug dealer's body, but the problem of eliminating the other drug dealer, who is in the basement of the house, still remains. Walter is tormented by the question of whether to murder him or not, and tries to get to know him.", dateOfChapter3Series1)
        };
        Chapter[] Unorthodox = {
                new Chapter(1, "Chapter 1", " Esti was born and raised in the ultra-Orthodox community of New York. After a year of marriage in which she failed to conceive, she flees to Berlin and finds the freedom she wished", dateOfChapter1Series2),
                new Chapter(2, "Chapter 2", "Esti's Berlin friends challenge her old beliefs, but leave her a safe space to explore her new life. Meanwhile, Yankee and Moishe are looking for Esti.", dateOfChapter2Series2),
                new Chapter(3, "Chapter 3", " While Moishe secretly collects evidence against Esti, she enlists help to be tested for a scholarship she desperately needs.", dateOfChapter3Series2)
        };
        Chapter[] PrisonBreak = {
                new Chapter(1, "Chapter 1", "Building engineer Michael Scofield puts himself in jail to smuggle his brother away, sentenced to death and claims to have been indicted.", dateOfChapter1Series3),
                new Chapter(2, "Chapter 2", "Michael tries to get two items: a drug that resembles a diabetic and a large screw that will be used by him in the escape plan.", dateOfChapter2Series3),
                new Chapter(3, "Chapter 3", "Michael tries to attach Abruzzi and Sucre to his show and encounters problems. Lincoln is considering ordering a relative for his execution.", dateOfChapter3Series3)
        };
        Series[] series = {
                new Series("BreakingBad", BreakingBad),
                new Series("Unorthodox", Unorthodox),
                new Series("PrisonBreak", PrisonBreak)
        };
        return new Netflix(series);
    }
    public static void menuForLogin() {
        System.out.println("The operation you can do are: \n" +
                "1. View the list of all series \n" +
                "2. View the list of series you started watching \n" +
                "3. View subscription details \n" +
                "4. Select a series to watch \n" +
                "5. Log out");

    }
    public static void operationForLogin(Netflix netflix, User user) {
        Scanner in = new Scanner(System.in);
        boolean isRun = true;
        while (isRun) {
            System.out.println();
            menuForLogin();
            System.out.println("\nEnter your choice ");
            int userOp = in.nextInt();
            while (userOp < Definition.MIN_USER_OPERATION_IN_LOGIN || userOp > Definition.MAX_USER_OPERATION_IN_LOGIN) {
                System.out.println("Your choice is not exists, try again");
                userOp = in.nextInt();
            }
            boolean run = true;
            while (run) {
                switch (userOp) {
                    case Definition.PRINT_LIST_OF_SERIES: {
                        netflix.printListOfSeries();
                        run = false;
                        break;
                    }
                    case Definition.PRINT_LIST_OF_WATCHED_SERIES: {
                        netflix.printLastWatched();
                    }
                    run = false;
                    break;

                    case Definition.PRINT_DETAILS_OF_USER: {
                        netflix.userDetails(user.getUserName(), user.getPassword());
                        run = false;
                        break;
                    }
                    case Definition.CHOOSE_SERIES_TO_WATCH: {
                        if (!netflix.isUserStartWatchAnySeries()) {
                            System.out.println("This is your first time at Netflix! Have fan!!! \n");
                            netflix.openAnArraySeriesList();
                            netflix.addFirstSeriesToUserList();
                        } else {
                            netflix.addEpisodeOrNewSeries();
                        }
                        run = false;
                        break;
                    }
                    case Definition.LOG_OUT: {
                        run = false;
                        isRun = false;
                    }
                }
            }


        }
    }
 }


