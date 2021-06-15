package com.example.onlineshopping;


import android.os.Parcel;
import android.os.Parcelable;

public class Items implements Parcelable {


    String name,category,description;
   String price,id;
    Items()
    {
    }

    Items(String i,String n,String p,String c,String d)
    {
        id=i;
        name=n;
        price=p;
        category=c;
        description=d;
    }

    protected Items(Parcel in) {
        name = in.readString();
        category = in.readString();
        description = in.readString();
        price = in.readString();
        id = in.readString();
    }

    public static final Creator<Items> CREATOR = new Creator<Items>() {
        @Override
        public Items createFromParcel(Parcel in) {
            return new Items(in);
        }

        @Override
        public Items[] newArray(int size) {
            return new Items[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName()
    {
        return name;
    }
    public String getCatory()
    {
        return category;
    }
    public String getDescription()
    {
        return description;
    }
    public String getPrice()
    {
        return price;
    }
    public void setName(String x)
    {
        name=x;
    }
    public void setCategory(String x)
    {
        category=x;
    }
    public void setDescription(String x)
    {
        description=x;
    }
    public void setPrice(String x)
    {
        price=x;
    }
    public void setId(String i)
    {
        id=i; }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(category);
        dest.writeString(description);
        dest.writeString(price);
        dest.writeString(id);
    }
}
