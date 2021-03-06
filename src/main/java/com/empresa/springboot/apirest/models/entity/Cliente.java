package com.empresa.springboot.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Clase de persistencia mapeado a una tabla, cada variable a un campo de la tabla
@Entity
@Table(name="clientes")
public class Cliente implements Serializable{ 

	//ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Auto-increment MySQL
	private Long id;
	
	@Column(nullable = false)
	@NotEmpty (message = "no puede estar vacio, mensaje desde Spring") //depedencia Spring validation - formulario
	@Size (min = 3, max = 12, message = "tiene que estar entre 4 y 12 caracteres, mensaje desde spring") //depedencia Spring validation - formulario
	private String nombre;
	
	@NotEmpty (message = "no puede estar vacio, mensaje desde Spring")//depedencia Spring validation - formulario
	private String apellido;
	@Column(nullable = false, unique = false)
	@NotEmpty (message = "no puede estar vacio, mensaje desde Spring")//depedencia Spring validation - formulario
	@Email (message = "no es una dirección válida de correo, mensaje desde spring")//depedencia Spring validation - formulario
	private String email;
	
	@NotNull(message="no puede estar vacio")
	@Column(name="create_at")
	@Temporal(TemporalType.DATE) //Tipo equivalente en la BD
	private Date createAt;
	
	private String foto;
	
	//Mapeo con cliente - MUCHOS A 1
	@ManyToOne(fetch = FetchType.LAZY) //Tipo: carga perezosa JPA
	@JoinColumn(name="region_id") // Llave foraneo JPA
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"}) //Propio de Hibernate, se usa porque se implementó FetchType.LAZY
	@NotNull(message = "La región no puede ser vacia")
	private Region region; 
	
	@JsonIgnoreProperties(value={"cliente","hibernateLazyInitializer","handler"},allowSetters = true) //Ignorar relación inversa | evitar loop | allowSetters: evitar recursión
	@OneToMany(fetch = FetchType.LAZY, mappedBy ="cliente",cascade = CascadeType.ALL) // mappedBy: relación en ambos sentidos | cascade: al eliminar un cliente, elimina facturas hijas - si se guarda un cliente con factura, primero se inserta el cliente, luego hijos | BD: Integridad referencial
	private List<Factura> facturas;
	
	
	//Constructor - Asignar facturas
	public Cliente() {
		this.facturas = new ArrayList<>();
	}
	/*@PrePersist //antes de que se inserte en la BD, asigna en la BD
	public void prePersist() {
		createAt = new Date();
	}*/
	//GETTERS AND SETTERS
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	public List<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}



	//ATRIBUTO ESTÁTICO CUANDO SE IMPLEMENTA SERIALIZABLE
	private static final long serialVersionUID = 1L;
	
	
}
