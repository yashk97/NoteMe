package com.example.yash.noteme;


public class MyData {
    String Title;
    String Content;
    String date;

    public MyData(String Title, String Content, String date){
        this.Title = Title;
        this.Content = Content;
        this.date = date;
    }
    public String get_data(){
        String str = this.Title+" : "+this.Content;
        return str;
    }
}
