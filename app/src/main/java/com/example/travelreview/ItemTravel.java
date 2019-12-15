package com.example.travelreview;

public class ItemTravel {
    private int Image;
    private String Name;
    private String content;

    public ItemTravel(int image, String name, String content) {
        Image = image;
        Name = name;
        this.content = content;
    }
    public void setContent(String text){
        this.content = text;
    }
    public String getContent(){
        return this.content;
    }
    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
