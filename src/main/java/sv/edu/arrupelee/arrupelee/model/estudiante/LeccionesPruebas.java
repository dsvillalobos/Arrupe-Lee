package sv.edu.arrupelee.arrupelee.model.estudiante;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "lecciones_pruebas")
public class LeccionesPruebas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "ID_LECCION")
    private Long idLeccion;
    
    @Column(name = "ID_PRUEBA")
    private Long idPrueba;
    
    @Column(name = "ESTADO")
    private String estado;
    
    public LeccionesPruebas() {
        
    }

    public LeccionesPruebas(Long id, Long idLeccion, Long idPrueba, String estado) {
        this.id = id;
        this.idLeccion = idLeccion;
        this.idPrueba = idPrueba;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdLeccion() {
        return idLeccion;
    }

    public void setIdLeccion(Long idLeccion) {
        this.idLeccion = idLeccion;
    }

    public Long getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(Long idPrueba) {
        this.idPrueba = idPrueba;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
