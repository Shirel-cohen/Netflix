import java.sql.Date;
import java.time.LocalDate;

public class User {
    private String userName;
    private String password;
    private Date creation;
    private Date runOut;
    private Series[] userSeriesList;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.creation = Date.valueOf(LocalDate.now());
        this.runOut =Date.valueOf(LocalDate.now().plusYears(Definition.SUBSCRIPTION_ONE_YEAR));;
    //    this.userSeriesList = userSeriesList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getRunOut() {
        return runOut;
    }

    public void setRunOut(Date runOut) {
        this.runOut = runOut;
    }

    public Series[] getUserSeriesList() {
        return userSeriesList;
    }

    public void setUserSeriesList(Series[] userSeriesList) {
        this.userSeriesList = userSeriesList;
    }
    public void seriesWatchedIncludingLastEpisode() {
        System.out.println();
        if (isSeriesListAnEmpty()) {
            System.out.println("You didn't Watched any Series yet ❗❗❗\n");

        } else {
            System.out.println("Series You started watching including last episode where you watched each series ❮ ❯");
            for (int i = 0; i < userSeriesList.length; i++) {
                Series currentSeries = userSeriesList[i];
                if (currentSeries != null) {
                    System.out.println("❮" + currentSeries.getSeriesName() + "❯");
                    System.out.println(currentSeries.getLastChapter().getChapterName());
                }
            }
        }
    }
    private boolean isSeriesListAnEmpty() {
        return userSeriesList == null;

    }
    public void print (){
            System.out.println("The user name is: " + this.userName);
            System.out.println("The password of the user is: " + this.password);
            System.out.println("Date of creation account: " + this.creation);
            System.out.println("Date of expiration account: " + this.runOut);
            System.out.println("----------------------------------");

    }

    public void listOfLastWatchedSeriesAndChapter() {
        System.out.println();
        if (isSeriesListAnEmpty()) {
            System.out.println("You didn't Watched any Series yet! \n");

        } else {
            System.out.println("The series you started to watch:  ");
            for (int i = 0; i < userSeriesList.length; i++) {
                Series currentSeries = userSeriesList[i];
                if (currentSeries != null) {
                    System.out.println("Series name: " + currentSeries.getSeriesName());
                    System.out.println("Last chapter: " + currentSeries.getLastChapter().getNumOfChapter());
                    System.out.println("-----------------------------");
                }
            }
        }
    }



  }
