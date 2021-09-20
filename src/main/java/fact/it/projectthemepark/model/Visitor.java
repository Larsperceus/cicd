package fact.it.projectthemepark.model;

import java.util.ArrayList;
import java.util.Locale;

// Lars Kammeijer r0831083
public class Visitor extends Person {
    private int yearOfBirth;
    private int themeParkCode;
    private ArrayList <String> wishList = new ArrayList();
    public Visitor(String firstName, String surName) {
        super(firstName, surName);
        themeParkCode = -1;
    }
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getThemeParkCode() {
        return themeParkCode;
    }

    public void setThemeParkCode(int themeParkCode) {
        this.themeParkCode = themeParkCode;
    }

    public ArrayList<String> getWishList() {
        return this.wishList;
    }

    public void setWishList(ArrayList<String> wishList) {
        this.wishList = wishList;
    }
    public boolean addToWishList(String attractionName){
        if (this.wishList.size() < 5){
            this.wishList.add(attractionName);
            return true;
        }
        else{
            return false;
        }
    }
    public int getNumberOfWishes(){
        return this.wishList.size();
    }
    public String toString(){
        return "Visitor " + super.toString() + " with theme park code " +this.themeParkCode;
    }
}
