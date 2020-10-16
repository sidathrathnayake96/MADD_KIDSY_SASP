package com.example.kidsy;

public class DeliverData {


    String delfirst;
    String delemail;
    String deladdress;
    String delbook;
    String delqty;
    String deldate;

    public DeliverData() {
    }

    public DeliverData(String delfirst,String delemail, String deladdress,String delbook,String delqty, String deldate) {


        this.delfirst = delfirst;
        this.delemail = delemail;
        this.deladdress = deladdress;
        this.delbook=delbook;
        this.delqty=delqty;
        this.deldate = deldate;
    }


    public String getDelfirst() {
        return delfirst;
    }

    public void setDelfirst(String delfirst) {
        this.delfirst = delfirst;
    }

    public String getDelemail() {
        return delemail;
    }

    public void setDelemail(String delemail) {
        this.delemail= delemail;
    }


    public String getDeladdress() {
        return deladdress;
    }

    public void setDeladdress(String deladdress) {
        this.deladdress = deladdress;
    }

    public String getDelbook() {
        return delbook;
    }

    public void setDelbook(String delbook) {
        this.delbook = delbook;
    }

    public String getDelqty() {
        return delqty;
    }

    public void setDelqty(String delqty) {
        this.delqty = delqty;
    }

    public String getDeldate() {
        return deldate;
    }

    public void setDeldate(String deldate) {
        this.deldate = deldate;
    }


}
