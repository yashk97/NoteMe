package com.example.yash.noteme;


public class MyData {
    String Title;
    String Content;
//    String Data_id;
    String date;

    public MyData(String Title, String Content,String date) {
        this.Title = Title;
        this.Content = Content;
//        this.Data_id = Data_id;
        this.date = date;
    }

    public String get_data() {
        String str = this.Title + " : " + this.Content;
        return str;
    }

    public MyData() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

//    public String getData_id() {
//        return Data_id;
//    }

//    public void setData_id(String Data_id) {
//        this.Data_id = Data_id;
//    }
}