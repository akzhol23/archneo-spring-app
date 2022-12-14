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

@Entity
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_name, last_name, bio, university, photo, email, password;
    private Date birth;
    private int views, experience, price;

    public Cards(String first_name, String last_name, String bio, String birth, String university, String photo, String email, String password, int price, int experience) throws ParseException {
        this.first_name = first_name;
        this.last_name = last_name;
        this.bio = bio;
        this.birth = new SimpleDateFormat("y-MM-dd").parse(birth);
        this.university = university;
        this.photo = photo;
        this.price = price;
        this.experience = experience;
        this.email = email;
        this.password = password;
    }

    public Cards() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFIO() {
        return getFirstName() + " " + getLastName();
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
        this.last_name = last_name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Date getBirth() {
        return birth;
    }

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public int getAge() {
        LocalDate date = convertToLocalDateViaSqlDate(getBirth());
        LocalDate now = LocalDate.now();
        Period diff = Period.between(date, now);

        return diff.getYears();
    }

    public void setBirth(String birth) throws ParseException {
        this.birth = new SimpleDateFormat("y-MM-dd").parse(birth);
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

    public int getPriceDB() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
