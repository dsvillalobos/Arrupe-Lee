package sv.edu.arrupelee.arrupelee.repositories.admin;

import sv.edu.arrupelee.arrupelee.model.admin.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    Usuario findByNombre(final String nombre);
}
