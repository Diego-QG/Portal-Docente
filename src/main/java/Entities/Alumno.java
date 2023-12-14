package Entities;

import java.util.Date;

public class Alumno {
    private String alumno_id;
    private String nombre;
    private String apellido;
    private int edad;
    private String direccion;
    private String ciudad;
    private String estado;
    private String codigoPostal;
    private String telefono;
    private String correoElectronico;

    public Alumno() {
    }

    public Alumno(String alumno_id, String nombre, String apellido, int edad, String direccion, String ciudad, String estado, String codigoPostal, String telefono, String correoElectronico) {
        this.alumno_id = alumno_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.estado = estado;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    public Alumno(String studentID, String firstName, String lastName, int age, String phone) {
        this.alumno_id = studentID;
        this.nombre = firstName;
        this.apellido = lastName;
        this.edad = age;
        this.telefono = phone;
    }

    public String getAlumno_id() {
        return alumno_id;
    }

    public void setAlumno_id(String alumno_id) {
        this.alumno_id = alumno_id;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    
    
}
