package services;

import java.util.HashMap;
import java.util.Map;

import Dao.ObjetivoDao;
import model.Objetivo;

public class ObjetivoService {

	final ObjetivoDao objetivoDao=new ObjetivoDao();
	
	public ObjetivoService() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Object createObjetivoEspecifico(String nombre,int idProyecto) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objetivo", this.objetivoDao.createObjetivoEspecifico(nombre, idProyecto));
		return map;
	}
	
	

}
