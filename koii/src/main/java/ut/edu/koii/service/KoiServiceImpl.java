package ut.edu.koii.service;

import jakarta.persistence.EntityNotFoundException;
import ut.edu.koii.models.Koi;
import ut.edu.koii.repository.KoiRepository;
import ut.edu.koii.service.KoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KoiServiceImpl implements KoiService {
    @Autowired private KoiRepository koiRepo;

    @Override
    public Koi create(Koi koi) {
        return koiRepo.save(koi);
    }

    @Override
    public List<Koi> list() {
        return koiRepo.findAll();
    }

    @Override
    public Koi get(Long id) {
        return koiRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Koi not found"));
    }

    @Override
    public Koi update(Long id, Koi updated) {
        Koi koi = get(id);
        koi.setName(updated.getName());
        koi.setImageUrl(updated.getImageUrl());
        koi.setBodyType(updated.getBodyType());
        koi.setAge(updated.getAge());
        koi.setSize(updated.getSize());
        koi.setWeight(updated.getWeight());
        koi.setGender(updated.getGender());
        koi.setBreed(updated.getBreed());
        koi.setOrigin(updated.getOrigin());
        koi.setPrice(updated.getPrice());
        return koiRepo.save(koi);
    }

    @Override
    public void delete(Long id) {
        koiRepo.deleteById(id);
    }
}
