package ut.edu.koii.models;

import jakarta.persistence.*;


@Entity
@Table(name = "koi_fish")
public class Koi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String imageUrl;
    private String bodyType;
    private int age;
    private double size;
    private double weight;
    private String gender;
    private String breed;
    private String origin;
    private double price;

    public Koi() {}
    public Koi(String name, String imageUrl, String bodyType, int age, double size, double weight,
               String gender, String breed, String origin, double price) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.bodyType = bodyType;
        this.age = age;
        this.size = size;
        this.weight = weight;
        this.gender = gender;
        this.breed = breed;
        this.origin = origin;
        this.price = price;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pond_id", nullable = false)
    private Pond pond;
    public Pond getPond() { return pond; }
    public void setPond(Pond pond) { this.pond = pond; }



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getBodyType() {
        return bodyType;
    }

    public int getAge() {
        return age;
    }

    public double getSize() {
        return size;
    }

    public double getWeight() {
        return weight;
    }

    public String getGender() {
        return gender;
    }

    public String getBreed() {
        return breed;
    }

    public String getOrigin() {
        return origin;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAgeInMonths() {
        return 0;
    }

// getters and setters for all fields
}





