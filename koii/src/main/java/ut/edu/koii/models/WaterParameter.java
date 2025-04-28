package ut.edu.koii.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "water_parameters")
public class WaterParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pond_id", nullable = false)
    @NotNull(message = "Hồ không được để trống")
    private Pond pond;

    @NotNull(message = "Thời gian đo không được để trống")
    @PastOrPresent(message = "Thời gian đo phải là hiện tại hoặc quá khứ")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "measured_at")
    private LocalDateTime measuredAt;

    @Min(value = 0, message = "Nhiệt độ phải lớn hơn hoặc bằng 0°C")
    @Max(value = 100, message = "Nhiệt độ phải nhỏ hơn hoặc bằng 100°C")
    @Column(name = "temperature")
    private double temperature;

    @Min(value = 0, message = "Độ mặn phải lớn hơn hoặc bằng 0")
    @Max(value = 100, message = "Độ mặn phải nhỏ hơn hoặc bằng 100")
    @Column(name = "salinity")
    private double salinity;

    @Min(value = 0, message = "pH phải lớn hơn hoặc bằng 0")
    @Max(value = 14, message = "pH phải nhỏ hơn hoặc bằng 14")
    @Column(name = "ph")
    private double pH;

    @Min(value = 0, message = "Oxy phải lớn hơn hoặc bằng 0")
    @Max(value = 20, message = "Oxy phải nhỏ hơn hoặc bằng 20")
    @Column(name = "oxygen")
    private double oxygen;

    @Min(value = 0, message = "NO2 phải lớn hơn hoặc bằng 0")
    @Column(name = "no2")
    private double no2;

    @Min(value = 0, message = "NO3 phải lớn hơn hoặc bằng 0")
    @Column(name = "no3")
    private double no3;

    @Min(value = 0, message = "PO4 phải lớn hơn hoặc bằng 0")
    @Column(name = "po4")
    private double po4;

    public WaterParameter() {
        this.measuredAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Pond getPond() { return pond; }
    public void setPond(Pond pond) { this.pond = pond; }
    public LocalDateTime getMeasuredAt() { return measuredAt; }
    public void setMeasuredAt(LocalDateTime measuredAt) { this.measuredAt = measuredAt; }
    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    public double getSalinity() { return salinity; }
    public void setSalinity(double salinity) { this.salinity = salinity; }
    public double getpH() { return pH; }
    public void setpH(double pH) { this.pH = pH; }
    public double getOxygen() { return oxygen; }
    public void setOxygen(double oxygen) { this.oxygen = oxygen; }
    public double getNo2() { return no2; }
    public void setNo2(double no2) { this.no2 = no2; }
    public double getNo3() { return no3; }
    public void setNo3(double no3) { this.no3 = no3; }
    public double getPo4() { return po4; }
    public void setPo4(double po4) { this.po4 = po4; }

    @Override
    public String toString() {
        return "WaterParameter{" +
                "id=" + id +
                ", pond=" + (pond != null ? pond.getId() : null) +
                ", measuredAt=" + measuredAt +
                ", temperature=" + temperature +
                ", salinity=" + salinity +
                ", pH=" + pH +
                ", oxygen=" + oxygen +
                ", no2=" + no2 +
                ", no3=" + no3 +
                ", po4=" + po4 +
                '}';
    }
}