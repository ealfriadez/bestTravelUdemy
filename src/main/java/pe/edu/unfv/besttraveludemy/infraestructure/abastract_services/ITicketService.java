package pe.edu.unfv.besttraveludemy.infraestructure.abastract_services;

import java.util.UUID;

import pe.edu.unfv.besttraveludemy.api.models.request.TicketRequest;
import pe.edu.unfv.besttraveludemy.api.models.response.TicketResponse;

public interface ITicketService extends CrudService<TicketRequest, TicketResponse, UUID>{

}
