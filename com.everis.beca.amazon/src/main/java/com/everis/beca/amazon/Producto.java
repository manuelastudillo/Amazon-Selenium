package com.everis.beca.amazon;

public class Producto {

	private String nombre;
	private String precio;
	private String link;
	private String descripcion;
	
	public Producto(String nombre, String precio, String link, String descripcion) {
	
		this.nombre = nombre;
		this.precio = precio;
		this.link = link;
		this.descripcion = descripcion;
	}

	public Producto(String nombre, String link, String descripcion) {
		super();

		this.nombre = nombre;
		this.link = link;
		this.descripcion = descripcion;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}