public class Chapter {
    private int numOfChapter;
    private String chapterName;
    private String chapterSummery;
    private Date dateOfAir;

    public Chapter(int numOfChapter, String chapterName, String chapterSummery, Date dateOfAir) {
        this.numOfChapter = numOfChapter;
        this.chapterName = chapterName;
        this.chapterSummery = chapterSummery;
        this.dateOfAir = dateOfAir;
    }

    public int getNumOfChapter() {
        return numOfChapter;
    }

    public void setNumOfChapter(int numOfChapter) {
        this.numOfChapter = numOfChapter;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterSummery() {
        return chapterSummery;
    }

    public void setChapterSummery(String chapterSummery) {
        this.chapterSummery = chapterSummery;
    }

    public Date getDateOfAir() {
        return dateOfAir;
    }

    public void setDateOfAir(Date dateOfAir) {
        this.dateOfAir = dateOfAir;
    }
    public void print (){
        System.out.println("The name of the chapter is: " + this.chapterName );
        System.out.println("The summery of the chapter is: " + this.chapterSummery);
        this.dateOfAir.print();

    }
}
