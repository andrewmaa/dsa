public class book {

    String author;
    int published, pages;

    public book(int year, String name, int num) {
        author = name;
        published = year;
        pages = num;
    }

    public String getAuthor() {
        return author;
    }

    public int getDate() {
        return published;
    }

    public int getPages() {
        return pages;
    }

    public static void main(String[] args) {
        book book1 = new book(2005, "Erin Morgenstern", 389);
        System.out.println(book1.author);
        

    }
    
}