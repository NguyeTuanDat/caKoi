package ut.edu.koii.service;

import ut.edu.koii.models.Pond;
import java.util.List;

public interface PondService {
    Pond create(Pond pond);
    Pond update(Long id, Pond pond);
    void delete(Long id);
    Pond getById(Long id);
    List<Pond> getAll();

    List<Pond> getAllPonds();  // Sửa lại kiểu trả về từ Object thành List<Pond>
}
