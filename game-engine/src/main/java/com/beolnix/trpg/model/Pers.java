package com.beolnix.trpg.model;

import java.io.Serializable;

/**
 * Created by beolnix on 29/08/15.
 */
public class Pers implements Serializable {
    private static final long serialVersionUID = -1811670629467388557L;

    private String name;
    private int image;
    private int position;

    public Pers() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pers pers = (Pers) o;

        if (image != pers.image) return false;
        if (position != pers.position) return false;
        if (name != null ? !name.equals(pers.name) : pers.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + image;
        result = 31 * result + position;
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
