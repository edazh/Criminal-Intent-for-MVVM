package com.edazh.criminalintent.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by edazh on 2018/1/12 0012.
 * e-mail:edazh@qq.com
 */
@Entity(tableName = "crimes")
public class Crime {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private Date date;

    private boolean solved;

    public Crime(String title, Date date, boolean solved) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.solved = solved;
    }

    @Ignore
    public Crime(String title, boolean solved) {
        this(title, new Date(System.currentTimeMillis()), solved);
    }

    @Ignore
    public Crime() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }
}
