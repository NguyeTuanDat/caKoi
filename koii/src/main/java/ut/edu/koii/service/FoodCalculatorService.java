package ut.edu.koii.service;

import ut.edu.koii.models.Koi;

public interface FoodCalculatorService {
    double calculateFood(Koi koi, double temperature);
}
