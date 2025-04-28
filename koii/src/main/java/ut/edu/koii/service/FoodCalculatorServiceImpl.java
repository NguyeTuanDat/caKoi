package ut.edu.koii.service;

import org.springframework.stereotype.Service;
import ut.edu.koii.models.Koi;

@Service
public class FoodCalculatorServiceImpl implements FoodCalculatorService {

    @Override
    public double calculateFood(Koi koi, double temperature) {
        double ratio;

        // Tính tỷ lệ % khối lượng cơ thể dựa trên độ tuổi và nhiệt độ
        if (temperature < 15) {
            ratio = 0.5; // Mùa lạnh
        } else {
            ratio = 2.0; // Cá trưởng thành
        }

        return koi.getWeight() * (ratio / 100); // Lượng thức ăn tính bằng gram
    }
}
