package ut.edu.koii.service;

import ut.edu.koii.models.WaterParameter;

import java.util.List;

public interface WaterParameterService {
    List<WaterParameter> listByPond(Long pondId);
    WaterParameter save(WaterParameter param);
    void delete(Long id);
}
