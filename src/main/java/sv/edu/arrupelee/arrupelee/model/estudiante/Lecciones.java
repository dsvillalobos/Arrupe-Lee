package sv.edu.arrupelee.arrupelee.model.estudiante;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;

@Entity
@Table(name = "lecciones")
public class Lecciones {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "CONTENIDO", columnDefinition = "TEXT")
    private String contenido;
    
    @Column(name = "ESTADO")
    private String estado;
    
    @Column(name = "NIVEL_LITERARIO")
    private String nivelLiterario;
    
    @Column(name = "ID_NIVEL")
    private Long idNivel;
    
    @Transient
    private String nombreNivelEducativo;
    
    public Lecciones() {
        
    }

    public Lecciones(Long id, String nombre, String contenido, String estado, String nivelLiterario, Long idNivel, String nombreNivelEducativo) {
        this.id = id;
        this.nombre = nombre;
        this.contenido = contenido;
        this.estado = estado;
        this.nivelLiterario = nivelLiterario;
        this.idNivel = idNivel;
        this.nombreNivelEducativo = nombreNivelEducativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNivelLiterario() {
        return nivelLiterario;
    }

    public void setNivelLiterario(String nivelLiterario) {
        this.nivelLiterario = nivelLiterario;
    }

    public Long getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Long idNivel) {
        this.idNivel = idNivel;
    }

    public String getNombreNivelEducativo() {
        return nombreNivelEducativo;
    }

    public void setNombreNivelEducativo(String nombreNivelEducativo) {
        this.nombreNivelEducativo = nombreNivelEducativo;
    }
    
}
