package pe.edu.unfv.besttraveludemy.infraestructure.abastract_services;

import java.util.Set;

import pe.edu.unfv.besttraveludemy.api.models.response.FlyResponse;

public interface IFlyService extends CatalogoService<FlyResponse>{

	Set<FlyResponse> readByOriginDestiny(String origen, String destiny);
}
