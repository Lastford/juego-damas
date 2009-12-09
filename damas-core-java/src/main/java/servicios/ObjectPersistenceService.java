package servicios;

import java.util.List;

import persistence.HibernateProxyHelperService;
import persistence.daos.CasilleroDao;
import persistence.daos.EntityDao;
import persistence.daos.FichaDao;
import persistence.daos.TableroDao;
import dominio.CasilleroNegro;
import dominio.Entidad;
import dominio.Ficha;
import dominio.Posicion;
import dominio.Tablero;

public class ObjectPersistenceService {
	EntityDao entityDao = new EntityDao();
	FichaDao fichaDao = new FichaDao();
	TableroDao tableroDao = new TableroDao();
	CasilleroDao casilleroDao = new CasilleroDao();
	HibernateProxyHelperService helperService = new HibernateProxyHelperService();
	public void guarda(Entidad entidad) {
		if(entidad.getId() != null){
			entityDao.update(entidad);
			return;
		}
		
		entityDao.create(entidad);
		return;
	}
	public Ficha dameFicha(Class clazz, String idEntity) {
		
		return (Ficha) fichaDao.findByIdEntity(clazz,idEntity);
	}
	public Ficha obtenerFicha(CasilleroNegro casilleroNegro) {
		Ficha ficha = null;
		ficha = fichaDao.findBlanca(casilleroNegro);
		if(ficha == null){
			ficha = fichaDao.findNegra(casilleroNegro);
		}
		return ficha;
	}
	public Tablero obtenerTablero() {
		return tableroDao.findAll().iterator().next();
	}
	public List<CasilleroNegro> obtenerCasillerosDisponibles(Posicion pos,Class clazz) {
		List<CasilleroNegro> listResult = casilleroDao.findDesocupado( pos.getX(),pos.getY(),clazz);
		
		return listResult;
	}
	public List<CasilleroNegro> obtenerCasillerosOcupadosOponente(Posicion pos,String color) {
		List<CasilleroNegro> listResult = casilleroDao.findOcupadoOponente(pos.getX(),pos.getY(),color);
		return listResult;
	}
	public CasilleroNegro obtenerCasillero(String casilleroStr) {
		return (CasilleroNegro) casilleroDao.findByIdEntity(CasilleroNegro.class, casilleroStr);
	}
	public List<CasilleroNegro> dameFichaCasillerosOcupados(Ficha ficha) {
//		return (CasilleroNegro) casilleroDao.
		return null;
	}
}
