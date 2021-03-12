package com.example.springclaserest.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name="profesor")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Clave primaria tipo  long")
    @Column(name="id")
    private Long id;
    @ApiModelProperty("Nombre  varchar con mas 40 caracteres")
    @Column(name="nombre")
    private String nombre;
    @ApiModelProperty("Apellido varchar con mas 40 caracteres")
    @Column(name="primerapellido")
    private String primerapellido;
    @ApiModelProperty("Apellido varchar con mas 40 caracteres")
    @Column(name="segundoapellido")
    private String segundoapellido;
    @ApiModelProperty("Edad  integer ")
    @Column(name="edad")
    private int edad ;

    @Column(name="curso")
    private String curso;

    @Column(name="suplente")
    private Boolean suplente;


    @Column(name="mail")
    private String mail;

    @Column(name="yearsincompany")
    private Integer yearsInCompany;

    @Column(name="salario")
    private Double salario;

    public Profesor() {
    }

    public Profesor(String nombre, String primerapellido, String segundoapellido, int edad, String curso, Boolean suplente, String mail, Double salario) {
        this.nombre = nombre;
        this.primerapellido = primerapellido;
        this.segundoapellido = segundoapellido;
        this.edad = edad;
        this.curso = curso;
        this.suplente = suplente;
        this.mail = mail;
        this.salario = salario;
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

    public String getPrimerapellido() {
        return primerapellido;
    }

    public void setPrimerapellido(String primerapellido) {
        this.primerapellido = primerapellido;
    }

    public String getSegundoapellido() {
        return segundoapellido;
    }

    public void setSegundoapellido(String segundoapellido) {
        this.segundoapellido = segundoapellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Boolean getSuplente() {
        return suplente;
    }

    public void setSuplente(Boolean suplente) {
        this.suplente = suplente;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Integer getYearsInCompany() {
        return yearsInCompany;
    }

    public void setYearsInCompany(Integer yearsInCompany) {
        this.yearsInCompany = yearsInCompany;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", primerapellido='" + primerapellido + '\'' +
                ", segundoapellido='" + segundoapellido + '\'' +
                ", edad=" + edad +
                ", curso='" + curso + '\'' +
                ", suplente=" + suplente +
                ", mail='" + mail + '\'' +
                ", yearsInCompany=" + yearsInCompany +
                ", salario=" + salario +
                '}';
    }
}
