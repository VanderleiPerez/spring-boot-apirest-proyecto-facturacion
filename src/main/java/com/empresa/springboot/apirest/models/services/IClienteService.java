package com.empresa.springboot.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.empresa.springboot.apirest.models.entity.Cliente;
import com.empresa.springboot.apirest.models.entity.Factura;
import com.empresa.springboot.apirest.models.entity.Producto;
import com.empresa.springboot.apirest.models.entity.Region;

public interface IClienteService {
	/* ---------------------- LISTAR CLIENTE ----------------------*/ 
	public List<Cliente> findAll();
	/* ---------------------- PAGINAR CLIENTE ----------------------*/ 
	public Page<Cliente> findAll(Pageable pageable); //Pageable de springframework.data.domain.Pageable
	/* ---------------------- BUSCAR CLIENTE ----------------------*/ 
	public Cliente findById(Long id);
	/* ---------------------- CREAR CLIENTE ----------------------*/ 
	public Cliente save(Cliente cliente);
	/* ---------------------- ELIMINAR CLIENTE ----------------------*/ 
	public void delete(Long id);
	
	
	/* ---------------------- LISTAR REGIONES ----------------------*/ 
	public List<Region> findAllRegiones();
	
	
	/* ---------------------- BUSCAR FACTURAS ----------------------*/ //Service : DaoManager
	public Factura findFacturaById(Long id);
	
	/* ---------------------- GUARDAR FACTURAS ----------------------*/
	public Factura saveFactura(Factura factura);
	
	/* ---------------------- ELIMINAR FACTURAS ----------------------*/
	public void deleteFacturaById(Long id);

	
	/* ---------------------- BUSCAR PRODUCTO ----------------------*/
	public List<Producto> findProductoByNombre(String term);

}
