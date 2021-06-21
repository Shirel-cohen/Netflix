import sun.security.krb5.internal.PAData;

import java.util.Scanner;
public class Netflix {
    private User [] usersList;
    private Series [] seriesList;
    private int lastAccountIndexLoggedIn;

    public int getLastAccountIndexLoggedIn() {
        return lastAccountIndexLoggedIn;
    }

    public void setLastAccountIndexLoggedIn(int lastAccountIndexLoggedIn) {
        this.lastAccountIndexLoggedIn = lastAccountIndexLoggedIn;
    }

    public Netflix(Series[] seriesList) {
        this.seriesList = seriesList;
    }

    public User[] getUsersList() {
        return usersList;
    }

    public void setUsersList(User[] usersList) {
        this.usersList = usersList;
    }

    public Series[] getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(Series[] seriesList) {
        this.seriesList = seriesList;
    }

    public void openAccount () {
        Scanner scanner = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        if (this.usersList == null) {
            System.out.println("Enter your username:");
            String userName = scanner.nextLine();
            System.out.println("Enter your password (The password must contain at least: 6 characters + one letter in English + one number)");
            String password = scanner.nextLine();
            while (!strongPassword(password)){
                System.out.println("You have to chose strong password!!");
                password = scanner.nextLine();
            }
            User newUser = new User(userName, password);
            addUser(newUser);
        } else {
            System.out.println("Enter your username:");
            String userName = scanner.nextLine();
            while (checkIfUserNameIsExisted(userName)) {
                System.out.println("There is user in that name, enter a new name");
                userName = scanner.nextLine();
            }
            System.out.println("Enter your password (The password must contain at least: 6 characters + one letter in English + one number)");
            String password = scanner.nextLine();
            while (!strongPassword(password)){
                System.out.println("You have to chose strong password!!");
                password = scanner.nextLine();
            }
            User newUser = new User(userName, password);
            addUser(newUser);
        }
    }
    public boolean strongPassword(String newPassword){
        if (newPassword.length() < Definition.MINIMUM_CHARS_FOR_PASSWORD) {
            return false;
        }
        char[] passwordChar = newPassword.toCharArray();
        int countDig = 0;
        int countChar = 0;
        for (int i = 0; i < newPassword.length(); i++) {
            if (Character.isDigit(passwordChar[i])) {
                countDig++;
            }
            if (Character.isAlphabetic(passwordChar[i])) {
                countChar++;
            }
        }
        if (countDig > 0 && countChar > 0){
            return true; }
        return false;
    }
    private boolean checkIfUserNameIsExisted(String userName) {
        for (int i = 0; i < usersList.length; i++) {
            String currentUserName = usersList[i].getUserName();
            if (currentUserName.equals(userName)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkIfTheUserExists (String userName, String password) {
        if (usersList ==null){
            return false;
        } else {
            for (int i = 0; i < usersList.length; i++) {
                String currentUserName = usersList[i].getUserName();
                String currentPassword = usersList[i].getPassword();
                if ((currentUserName.equals(userName)) && (currentPassword.equals(password))) {
                    this.lastAccountIndexLoggedIn =i;
                    return true;
                }
            }
        }
        return false;
    }
    public void addUser (User newUser){
        if (this.usersList == null) {
            User[] biggerList = new User[1];
            biggerList [0] = newUser;
            this.usersList=biggerList;
        } else {
            User[] biggerList = new User[this.usersList.length + 1];
            for (int i = 0; i < this.usersList.length; i++) {
                if (usersList[i] == null) {
                    usersList[i] = newUser;
                }
                biggerList[i] = this.usersList[i];
            }
            biggerList[this.usersList.length] = newUser;
            this.usersList = biggerList;
        }
    }
    public void userDetails(String userName, String password) {
        for (int i = 0; i < usersList.length; i++) {
            String currentUserName = usersList[i].getUserName();
            String currentPassword = usersList[i].getPassword();
            if ((currentUserName.equals(userName)) && (currentPassword.equals(password))) {
                User user = new User(userName, password);
                user.print();
                System.out.println("Series you started watching:" + howManySeriesStartedWatching());
                System.out.println("Series you finished watching: " + howManySeriesFinished());
                System.out.println("Episodes have been watched so far :" + howManyChatersWatchedSoFar());
                System.out.println("******************************");

            }
        }
    }
    public int howManySeriesFinished() {
        int counterOfFinishedSeries = 0;
        int checkIfUserWatchedAllTheEpisode;
        if (usersList[lastAccountIndexLoggedIn].getUserSeriesList() == null) {
            return counterOfFinishedSeries;
        } else {
            for (int i = 0; i < usersList[lastAccountIndexLoggedIn].getUserSeriesList().length; i++) {
                if (usersList[lastAccountIndexLoggedIn].getUserSeriesList()[i] != null) {
                    Chapter[] episodes = usersList[lastAccountIndexLoggedIn].getUserSeriesList()[i].getChaptersList();
                    checkIfUserWatchedAllTheEpisode = 0;
                    for (int j = 0; j < episodes.length; j++) {
                        if (episodes[j] != null) {
                            checkIfUserWatchedAllTheEpisode++;

                        }
                    }
                    if (checkIfUserWatchedAllTheEpisode == episodes.length) {
                        counterOfFinishedSeries++;
                    }
                }
            }
        }

        return counterOfFinishedSeries;
    }
    public int howManySeriesStartedWatching (){
         int countSeriesUserStartWatching = 0;
         if (usersList[lastAccountIndexLoggedIn].getUserSeriesList()!=null) {
             for (int i = 0; i < usersList[lastAccountIndexLoggedIn].getUserSeriesList().length; i++) {
                 if (usersList[lastAccountIndexLoggedIn].getUserSeriesList()[i] != null) {
                     countSeriesUserStartWatching++;
                 }
             }
         }
         return countSeriesUserStartWatching;

     }
    public int howManyChatersWatchedSoFar(){
        int countEpisodeHaveBeenWatched = 0;
        if (usersList[lastAccountIndexLoggedIn].getUserSeriesList()!=null) {
            for (int i = 0; i < usersList[lastAccountIndexLoggedIn].getUserSeriesList().length; i++) {
                if (usersList[lastAccountIndexLoggedIn].getUserSeriesList()[i] != null) {
                    Chapter[] seriesEpisodes = usersList[lastAccountIndexLoggedIn].getUserSeriesList()[i].getChaptersList();
                    for (int j = 0; j < seriesEpisodes.length; j++) {
                        if (seriesEpisodes[j] != null) {
                            countEpisodeHaveBeenWatched++;
                        }
                    }
                }
            }
        }
       return countEpisodeHaveBeenWatched;
    }
    public void printListOfSeries(){

        if (seriesList==null){
            System.out.println("There is no series!");
        } else{
            System.out.println("The series are: ");
            for (int i=0; i<seriesList.length;i++){
                if (seriesList[i]!=null){
                    System.out.println(seriesList[i].getSeriesName());
                }
            }
        }
        System.out.println();
    }
    public int askForTheNextChapter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which chapter do you want to watch? ");
        int userChoiceOfChapter;
        do {
             userChoiceOfChapter = scanner.nextInt();
            if (userChoiceOfChapter < Definition.MIN_NUMBER_OF_EPISODES || userChoiceOfChapter > Definition.TOTAL_NUMBER_OF_EPISODES) {
                System.out.println("There is no chapter that index");
            }
        } while (userChoiceOfChapter<Definition.MIN_NUMBER_OF_EPISODES || userChoiceOfChapter>Definition.TOTAL_NUMBER_OF_EPISODES);
        return userChoiceOfChapter;
    }
    public void addNewSeriesToUserList(String seriesUserWouldWatch) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < seriesList.length; i++) {
            String currentSeriesName = seriesList[i].getSeriesName();
            if (currentSeriesName.equals(seriesUserWouldWatch)) {
                System.out.println("Which chapter do you wan to watch? ");
                Series seriesChosen = seriesList[i];
                //seriesList[i].getChaptersList()[i].print();
                int episodeIndex = scanner.nextInt();
                boolean episodeNotExisted = episodeIndex < 0 || episodeIndex > seriesList[i].getChaptersList().length;
                if (episodeNotExisted) {
                    System.out.println("There is not chapter at that index");
                } else {
                    User onlineAccount = usersList[lastAccountIndexLoggedIn];
                    Series[] userSEriesList = onlineAccount.getUserSeriesList();
                    for (int j = 0; j <userSEriesList.length ; j++) {
                        if (userSEriesList[i] == null) {
                            Chapter[] episodesList = new Chapter[seriesChosen.getChaptersList().length];
                            episodesList[0] = seriesChosen.getChaptersList()[episodeIndex - 1];
                            userSEriesList[i] = new Series(currentSeriesName, episodesList);
                            userSEriesList[i].setLastChapter(episodesList[0]);

                            break;
                        }
                    }
                }
                break;
            }
        }
    }
    public  void addFirstSeriesToUserList() {
        Scanner in = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which series do you want to watch? ");
        String seriesUserWouldWatch = scanner.nextLine();
        if (checkIfSeriesExisted(seriesUserWouldWatch)) {
            for (int i = 0; i < seriesList.length; i++) {
                String currentSeriesName = seriesList[i].getSeriesName();
                if (currentSeriesName.equals(seriesUserWouldWatch)) {
                    System.out.println("Which chapter do you want to watch? ");
                  //  seriesList[i].getChaptersList()[i].print();
                    int episodeIndex = in.nextInt();
                    episodeIndex -= 1;
                    boolean episodeNotExisted = episodeIndex < 0 || episodeIndex >=seriesList[i].getChaptersList().length;
                    if (episodeNotExisted) {
                        System.out.println("There is no chapter at that index! ");
                    } else {
                        User onlineAccount = usersList[lastAccountIndexLoggedIn];
                        Chapter[] openNewEpisodeArray = new Chapter[seriesList[i].getChaptersList().length];
                        Series[] userSeriesList = onlineAccount.getUserSeriesList();
                        openNewEpisodeArray[getEpisodeIndexFromNetflixList(i , episodeIndex)] = seriesList[i].getChaptersList()[episodeIndex];
                        Series seriesUserStartWatch = new Series(seriesUserWouldWatch, openNewEpisodeArray);
                        seriesUserStartWatch.setLastChapter(openNewEpisodeArray[getEpisodeIndexFromNetflixList(i , episodeIndex)]);
                        userSeriesList[i] = seriesUserStartWatch;
                        break;
                    }
                    break;
                }
            }
        }

    }
    public void addEpisodeOrNewSeries() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which series do you want to watch? ");
        String seriesUserWouldWatch = scanner.nextLine();
        if (checkIfSeriesExisted(seriesUserWouldWatch)) {
            int seriesIndex = isUserStartWatchThisSeries(seriesUserWouldWatch);
            if (seriesIndex != -1) {
                User onlineAccount = usersList[lastAccountIndexLoggedIn];
                Series seriesSelectedByUser = onlineAccount.getUserSeriesList()[seriesIndex];
                remindUserLastEpisodeHeWatched(seriesUserWouldWatch);
                int episodeIndex = askForTheNextChapter();
                episodeIndex -= 1;
                boolean episodeNotExisted = episodeIndex < 0 || episodeIndex >= seriesSelectedByUser.getChaptersList().length;
                if (episodeNotExisted) {
                    System.out.println("There is no chapter at that index! ");
                } else {
                    Chapter[] userEpisodesList = seriesSelectedByUser.getChaptersList();
                    Chapter episodeSelectedByUser = seriesList[seriesIndex].getChaptersList()[episodeIndex];
                    int netFLixEpisodeIndex =  getEpisodeIndexFromNetflixList(seriesIndex ,episodeIndex);
                    userEpisodesList[netFLixEpisodeIndex] = episodeSelectedByUser;
                    seriesSelectedByUser.setLastChapter(userEpisodesList[netFLixEpisodeIndex]);
                }
            } else {
                addNewSeriesToUserList(seriesUserWouldWatch);
            }
        }
    }
    public void remindUserLastEpisodeHeWatched(String userSeriesWanted) {
        User onlineAccount = usersList[lastAccountIndexLoggedIn];
        Series[] userSeriesList = onlineAccount.getUserSeriesList();
        for (int i = 0; i < usersList[lastAccountIndexLoggedIn].getUserSeriesList().length ; i++) {
            if (usersList[lastAccountIndexLoggedIn].getUserSeriesList()[i] != null) {
                String currentSeriesName = usersList[lastAccountIndexLoggedIn].getUserSeriesList()[i].getSeriesName();
                if (currentSeriesName.equals(userSeriesWanted)) {
                    System.out.println();
                    System.out.println("You already Started watch this Series. Welcome back! ");
                    System.out.println("The last chapter you watched: "+ usersList[lastAccountIndexLoggedIn].getUserSeriesList()[i].getLastChapter().getNumOfChapter() + "\n");
                    break;
                }
            }

        }
    }
    public int isUserStartWatchThisSeries(String seriesUserWouldWatch) {
    int seriesIndex = -1;
    User onlineAccount = usersList[lastAccountIndexLoggedIn];
    Series[] userSeriesList = onlineAccount.getUserSeriesList();
    for (int i = 0; i < userSeriesList.length; i++) {
        if (userSeriesList[i] != null ) {
            String currentSeriesName = userSeriesList[i].getSeriesName();
            if (currentSeriesName.equals(seriesUserWouldWatch)) {
                seriesIndex = i;
                return seriesIndex;
            }
        }
    }
    return seriesIndex;
}
    public int getEpisodeIndexFromNetflixList(int seriesIndex , int userEpisodeIndex) {
        Series seriesSelected = seriesList[seriesIndex];
        Chapter[] seriesEpisodes = seriesSelected.getChaptersList();
        for (int i = 0; i < seriesEpisodes.length; i++) {
            if (i == userEpisodeIndex) {
                return i;
            }
        }
        return -1 ;
    }
    public void printLastWatched(){
        usersList[lastAccountIndexLoggedIn].listOfLastWatchedSeriesAndChapter();
    }
    public void openAnArraySeriesList() {
        usersList[lastAccountIndexLoggedIn].setUserSeriesList(new Series[this.seriesList.length]);
    }
    public boolean checkIfSeriesExisted(String seriesUserWouldWatch) {
        boolean isSeriesExisted = false;
        for (int i = 0; i < seriesList.length ; i++) {
            String currentSeriesName = seriesList[i].getSeriesName();
            if (currentSeriesName.equals(seriesUserWouldWatch)) {
                isSeriesExisted = true;
                break;
            }
        }
        if (!isSeriesExisted)
            System.out.println("There is no series called: '" +seriesUserWouldWatch + "'");

        return isSeriesExisted;
    }
    public boolean isUserStartWatchAnySeries() {
        User onlineAccount = usersList[lastAccountIndexLoggedIn];
        if (onlineAccount.getUserSeriesList() != null){
            return true;
        }
        return false;


    }




}
