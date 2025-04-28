package ut.edu.koii.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ut.edu.koii.models.WaterParameter;
import ut.edu.koii.repository.WaterParameterRepository;
import ut.edu.koii.service.WaterParameterService;

import java.util.List;

@Service
public class WaterParameterServiceImpl implements WaterParameterService {

    @Autowired
    private WaterParameterRepository repo;

    @Override
    public List<WaterParameter> listByPond(Long pondId) {
        return repo.findByPondIdOrderByMeasuredAtDesc(pondId);
    }

    @Override
    public WaterParameter save(WaterParameter param) {
        return repo.save(param);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}

