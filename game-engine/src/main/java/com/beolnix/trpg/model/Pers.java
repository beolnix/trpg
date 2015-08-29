package com.beolnix.trpg.model;

import java.io.Serializable;

/**
 * Created by beolnix on 29/08/15.
 */
public class Pers implements Serializable {
    private static final long serialVersionUID = -1811670629467388557L;

    private String name;

    public Pers() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pers pers = (Pers) o;

        if (name != null ? !name.equals(pers.name) : pers.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
