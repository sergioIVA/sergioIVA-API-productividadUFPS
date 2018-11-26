package services;

import java.util.LinkedHashMap;

import Dao.ProductoDao;

public class ProductoService {

	final ProductoDao dao = new ProductoDao();
	
	public Object createProductoEmpresarial(String nombre, String descripcion, int id_proyecto, int id_tipo_producto, String valor_contrato, String numero_contrato, String nit, String certificado_camara, 
			String certificado_institucional, String autores, String certificacion_implementacion, String documento_ley, String certificacion_producto, String nombre_empresa, String fecha, 
			String titulo, String nombre_innovacion) throws Exception {
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("producto", this.dao.createProductoEmpresarial(nombre, descripcion, id_proyecto, id_tipo_producto, valor_contrato, numero_contrato, nit, certificado_camara, certificado_institucional, autores, certificacion_implementacion, documento_ley, certificacion_producto, nombre_empresa, fecha, titulo, nombre_innovacion));
		return map;
	}
	
	public Object createProductoTecnologico(String nombre, String descripcion, int id_proyecto, int id_tipo_producto, String numero, String certificado, String titular, String anio_obtencion, 
			String paises_obtencion, String gaceta_publica, int contrato_explotacion_licen_id, int id_solicitud, int estado_patente) throws Exception {
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("producto", this.dao.createProductoTecnologicoPatentado(nombre, descripcion, id_proyecto, id_tipo_producto, numero, certificado, titular, anio_obtencion, paises_obtencion, gaceta_publica, contrato_explotacion_licen_id, id_solicitud, estado_patente));
		return map;
	}
	
	public Object createProductoTecnologicoCertificado(String nombre, String descripcion, int id_proyecto, int id_tipo_producto, String numero_registro, String titulo, String nombre_titular, String anio_obtencion, String pais_obtencion, 
			String gaceta_publicacion, String descripcion_analisis, String descripcion_diseno, String descripcion_implementacion, String descripcion_validacion, 
			String lugar_elaboracion, String institucion_financiadora, String copia_contratos, String mes, String fecha_elaboracion, String tecnologicos_certificadoscol) throws Exception {
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("producto", this.dao.createProductoTecnologicoCertificado(nombre, descripcion, id_proyecto, id_tipo_producto, numero_registro, nombre, nombre_titular, anio_obtencion, pais_obtencion, gaceta_publicacion, descripcion_analisis, descripcion_diseno, descripcion_implementacion, descripcion_validacion, lugar_elaboracion, institucion_financiadora, copia_contratos, mes, fecha_elaboracion, tecnologicos_certificadoscol));
		return map;
	}
	
	public Object getProductosEmpresariales() throws Exception {
		return this.dao.getProductosEmpresariales();
	}
	
	public Object getProductoEmpresarial(int id) throws Exception {
		return this.dao.getProductoEmpresarial(id);
	}
	
	public Object getProductosTecnologicos() throws Exception {
		return this.dao.getProductosTecnologicos();
	}
	
	public Object getProductoTecnologico(int id) throws Exception {
		return this.dao.getProductoTecnologico(id);
	}
	
	public Object getProductosTecnologicosCertificados() throws Exception {
		return this.dao.getProductosTecnologicosCertificados();
	}
	
	public Object getProductoTecnologicoCertificado(int id) throws Exception {
		return this.dao.getProductoTecnologicoCertificado(id);
	}
}
