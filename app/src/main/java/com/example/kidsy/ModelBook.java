package com.example.kidsy;

public class ModelBook {

    private  String bookid,bookimage,bookcategory,bookname,bookprice,bookship,bookuploaddate,timestamp,uid;

    public ModelBook() {
    }

    public ModelBook(String bookid, String bookimage, String bookcategory, String bookname,
                     String bookprice, String bookship, String bookuploaddate, String timestamp, String uid) {
        this.bookid = bookid;
        this.bookimage = bookimage;
        this.bookcategory = bookcategory;
        this.bookname = bookname;
        this.bookprice = bookprice;
        this.bookship = bookship;
        this.bookuploaddate = bookuploaddate;
        this.timestamp = timestamp;
        this.uid = uid;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getBookimage() {
        return bookimage;
    }

    public void setBookimage(String bookimage) {
        this.bookimage = bookimage;
    }

    public String getBookcategory() {
        return bookcategory;
    }

    public void setBookcategory(String bookcategory) {
        this.bookcategory = bookcategory;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookprice() {
        return bookprice;
    }

    public void setBookprice(String bookprice) {
        this.bookprice = bookprice;
    }

    public String getBookship() {
        return bookship;
    }

    public void setBookship(String bookship) {
        this.bookship = bookship;
    }

    public String getBookuploaddate() {
        return bookuploaddate;
    }

    public void setBookuploaddate(String bookuploaddate) {
        this.bookuploaddate = bookuploaddate;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
