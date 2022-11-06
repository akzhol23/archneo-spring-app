package iitu.labs.archneospringapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Entity
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String first_name, last_name, bio, birth, university, photo;
    private int views;
    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        last_name = last_name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        bio = bio;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getBirth() {
        return birth;
    }

    public int getAge() {
        String strDate = getBirth();
        String[] subStr;
        String delimeter = "-";
        LocalDate now = LocalDate.now();
        subStr = strDate.split(delimeter);
        LocalDate oldDate = LocalDate.of(Integer.parseInt(subStr[0]),
            Integer.parseInt(subStr[1]), Integer.parseInt(subStr[2]));
        Period diff = Period.between(oldDate, now);

        return diff.getYears();
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPrice() {
        return "$" + price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
