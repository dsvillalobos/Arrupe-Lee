package sv.edu.arrupelee.arrupelee.repositories;

import sv.edu.arrupelee.arrupelee.model.admin.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository <Usuario, Long> {
    Usuario findByCarnet(final String carnet);
}