public class Series {
    private String seriesName;
    private Chapter[] chaptersList;
    private Chapter lastChapter;

    public Series(String seriesName, Chapter[] chaptersList) {
        this.seriesName = seriesName;
        this.chaptersList = chaptersList;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public Chapter[] getChaptersList() {
        return chaptersList;
    }

    public void setChaptersList(Chapter[] chaptersList) {
        this.chaptersList = chaptersList;
    }

    public Chapter getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(Chapter lastChapter) {
        this.lastChapter = lastChapter;
    }
    public void print() {
        System.out.println("The series name is: " + this.seriesName);
        if (chaptersList == null) {
            System.out.println("There is no chapters at that series!");
        } else {
            System.out.println("The chapters at that series are: ");
            for (int i = 0; i < chaptersList.length; i++) {
                if (chaptersList[i] != null) {
                    Chapter c = chaptersList[i];
                    c.print();
                }
            }
        }

        System.out.println();
    }
}
