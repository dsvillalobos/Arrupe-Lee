package sv.edu.arrupelee.arrupelee.model.admin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import java.util.Date;

@Entity
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "CARNET")
    private String carnet;
    
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "APELLIDO")
    private String apellido;
    
    @Column(name = "CONTRASENA")
    private String contrasena;
    
    @Column(name = "CORREO")
    private String correo;
    
    @Column(name = "NUMERO")
    private String numero;
    
    @Column(name = "ESTADO")
    private String estado;
    
    @Column(name = "FECHA_REGISTRO")
    private Date fechaRegistro;
    
    @Column(name = "ID_ROL")
    private Long idRol;
    
    @Column(name = "ID_GRADO")
    private Long idGrado;
    
    @Column(name = "ID_SECCION")
    private Long idSeccion;
    
    @Column(name = "ID_NIVEL_EDUCATIVO")
    private Long idNivelEducativo;
    
    /*
    
    Esto es MUY IMPORTANTE.
    
    Los "Transient" NO seran persistidos en la base de datos
    
    */
    
    @Transient
    private String rolNombre;

    @Transient
    private String gradoNombre;

    @Transient
    private String seccionNombre;

    @Transient
    private String nivelEducativoNombre;
    
    
    public Usuario() {
        
    }

    public Usuario(Long id, String carnet, String nombre, String apellido, String contrasena, String correo, String numero, String estado, Date fechaRegistro, Long idRol, Long idGrado, Long idSeccion, Long idNivelEducativo, String rolNombre, String gradoNombre, String seccionNombre, String nivelEducativoNombre) {
        this.id = id;
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.correo = correo;
        this.numero = numero;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.idRol = idRol;
        this.idGrado = idGrado;
        this.idSeccion = idSeccion;
        this.idNivelEducativo = idNivelEducativo;
        this.rolNombre = rolNombre;
        this.gradoNombre = gradoNombre;
        this.seccionNombre = seccionNombre;
        this.nivelEducativoNombre = nivelEducativoNombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public Long getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Long idGrado) {
        this.idGrado = idGrado;
    }

    public Long getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Long idSeccion) {
        this.idSeccion = idSeccion;
    }

    public Long getIdNivelEducativo() {
        return idNivelEducativo;
    }

    public void setIdNivelEducativo(Long idNivelEducativo) {
        this.idNivelEducativo = idNivelEducativo;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public String getGradoNombre() {
        return gradoNombre;
    }

    public void setGradoNombre(String gradoNombre) {
        this.gradoNombre = gradoNombre;
    }

    public String getSeccionNombre() {
        return seccionNombre;
    }

    public void setSeccionNombre(String seccionNombre) {
        this.seccionNombre = seccionNombre;
    }

    public String getNivelEducativoNombre() {
        return nivelEducativoNombre;
    }

    public void setNivelEducativoNombre(String nivelEducativoNombre) {
        this.nivelEducativoNombre = nivelEducativoNombre;
    }
    
}
