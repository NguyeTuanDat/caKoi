package ut.edu.koii.service;

import ut.edu.koii.models.Koi;
import java.util.List;

public interface KoiService {
    Koi create(Koi koi);
    List<Koi> list();
    Koi get(Long id);
    Koi update(Long id, Koi koi);
    void delete(Long id);
}

