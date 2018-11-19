package services;

import Dao.ProcesoEspecificoDao;

public class ProcesoEspecificoService {

	final ProcesoEspecificoDao procesoEspecificoDao=new ProcesoEspecificoDao();
	
	public ProcesoEspecificoService() {
		// TODO Auto-generated constructor stub
	}

	
	public Object getPanelPrincipal(int idGrupo) throws Exception {
		 return this.procesoEspecificoDao.getPanelPrincipal(idGrupo);
	}
	
	public Object getGrupoCategoriaDirector()throws Exception {
		return this.procesoEspecificoDao.getGrupoCategoriaDirector();
		
	}
	
}
