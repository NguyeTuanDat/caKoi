package ut.edu.koii.models;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "ponds")
public class Pond {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String imageUrl;
    private double length;
    private double width;
    private double depth;
    private double volume;
    private int drainCount;
    private double pumpCapacity;
    @Transient
    private MultipartFile imageFile;
    public MultipartFile getImageFile() { return imageFile; }
    public void setImageFile(MultipartFile imageFile) { this.imageFile = imageFile; }
    public Pond() {}

    public Pond(Long id, String name, String imageUrl, double length, double width, double depth, double volume, int drainCount, double pumpCapacity) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.length = length;
        this.width = width;
        this.depth = depth;
        this.volume = volume;
        this.drainCount = drainCount;
        this.pumpCapacity = pumpCapacity;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public double getLength() { return length; }
    public void setLength(double length) { this.length = length; }
    public double getWidth() { return width; }
    public void setWidth(double width) { this.width = width; }
    public double getDepth() { return depth; }
    public void setDepth(double depth) { this.depth = depth; }
    public double getVolume() { return volume; }
    public void setVolume(double volume) { this.volume = volume; }
    public int getDrainCount() { return drainCount; }
    public void setDrainCount(int drainCount) { this.drainCount = drainCount; }
    public double getPumpCapacity() { return pumpCapacity; }
    public void setPumpCapacity(double pumpCapacity) { this.pumpCapacity = pumpCapacity; }

    public double getWaterVolume() {
        return 0;
    }
}
