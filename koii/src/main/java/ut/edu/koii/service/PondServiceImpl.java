package ut.edu.koii.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ut.edu.koii.models.Pond;
import ut.edu.koii.repository.PondRepository;
import ut.edu.koii.service.PondService;

import java.util.List;

@Service
public class PondServiceImpl implements PondService {

    @Autowired
    private PondRepository pondRepository;

    @Override
    public Pond create(Pond pond) {
        return pondRepository.save(pond);
    }

    @Override
    public Pond update(Long id, Pond pond) {
        if (pondRepository.existsById(id)) {
            pond.setId(id);
            return pondRepository.save(pond);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        pondRepository.deleteById(id);
    }

    @Override
    public Pond getById(Long id) {
        return pondRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pond> getAll() {
        return pondRepository.findAll();
    }

    @Override
    public List<Pond> getAllPonds() {
        return pondRepository.findAll();  // Trả về danh sách tất cả các hồ cá
    }
}
