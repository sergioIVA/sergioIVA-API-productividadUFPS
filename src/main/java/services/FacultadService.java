package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Dao.FacultadDao;
import conexion.Conexion;
import model.Facultad;

public class FacultadService {
	
	
      static final FacultadDao facultadDao=new FacultadDao();
      static final Gson gson=new Gson();

	public FacultadService() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Object createFacultad(int codigo,String nombre) throws SQLException{
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("response-date",new Date());
	    map.put("msg","peticion correcta");
		map.put("facultad",this.facultadDao.createFacultad(codigo,nombre));
		  return map; 
	}
	
	public Object getFacultades()throws SQLException{
		
        //ejemplos https://jarroba.com/gson-json-java-ejemplos/
	    
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("facultad",this.facultadDao.getFacultades());
	
	
		   return map;	   
	} 
	
	public Facultad getFacultad(int id) throws SQLException{
		return this.facultadDao.getFacultad(id);
	}
	
	
	public Facultad updateFacultad(Facultad f,String nombre,int codigo)throws SQLException {
		 return this.facultadDao.updateFacultad(f,nombre,codigo);
	}
	
	public boolean deleteFacultad(int id)throws SQLException {
		   return this.facultadDao.deleteFacultad(id);
		
	}
	

}
