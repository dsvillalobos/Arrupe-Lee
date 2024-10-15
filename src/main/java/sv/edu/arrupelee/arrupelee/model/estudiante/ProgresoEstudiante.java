package sv.edu.arrupelee.arrupelee.model.estudiante;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "progreso_estudiante")
public class ProgresoEstudiante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "ID_USUARIO")
    private Long idUsuario;
    
    @Column(name = "ID_LECCION")
    private Long idLeccion;
    
    @Column(name = "PORCENTAJE_COMPLETADO")
    private Double porcentajeCompletado;
    
    public ProgresoEstudiante() {
        
    }

    public ProgresoEstudiante(Long id, Long idUsuario, Long idLeccion, Double porcentajeCompletado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idLeccion = idLeccion;
        this.porcentajeCompletado = porcentajeCompletado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdLeccion() {
        return idLeccion;
    }

    public void setIdLeccion(Long idLeccion) {
        this.idLeccion = idLeccion;
    }

    public Double getPorcentajeCompletado() {
        return porcentajeCompletado;
    }

    public void setPorcentajeCompletado(Double porcentajeCompletado) {
        this.porcentajeCompletado = porcentajeCompletado;
    }
    
}
