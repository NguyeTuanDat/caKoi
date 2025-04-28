package ut.edu.koii.service;

import org.springframework.stereotype.Service;
import ut.edu.koii.models.Pond;
import ut.edu.koii.service.SaltCalculatorService;

@Service
public class SaltCalculatorServiceImpl implements SaltCalculatorService {

    @Override
    public double calculateSalt(Pond pond, double desiredSaltPercent) {
        return pond.getVolume() * desiredSaltPercent / 100;
    }
}
