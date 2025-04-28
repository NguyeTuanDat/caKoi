package ut.edu.koii.service;

import ut.edu.koii.models.Pond;

public interface SaltCalculatorService {
    double calculateSalt(Pond pond, double desiredSaltPercent);
}
