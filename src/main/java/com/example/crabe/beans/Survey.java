package com.example.crabe.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Survey {
    private @Id @GeneratedValue String id;
    private String name;
    private String dateDeb;
    private String dateEnd;

    public Survey(){}

    public Survey(String name, String dateDeb, String dateEnd) {
        this.name = name;
        this.dateDeb = dateDeb;
        this.dateEnd = dateEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(String dateDeb) {
        this.dateDeb = dateDeb;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Survey survey = (Survey) o;
        return Objects.equals(name, survey.name) && Objects.equals(dateDeb, survey.dateDeb) && Objects.equals(dateEnd, survey.dateEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateDeb, dateEnd);
    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateDeb='" + dateDeb + '\'' +
                ", dateEnd='" + dateEnd + '\'' +
                '}';
    }
}
