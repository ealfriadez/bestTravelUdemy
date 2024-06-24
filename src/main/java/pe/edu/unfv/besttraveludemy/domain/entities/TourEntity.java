package pe.edu.unfv.besttraveludemy.domain.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "tour")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TourEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            mappedBy = "tour"
    )
    private Set<ReservationEntity> reservations;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            mappedBy = "tour"
    )
    private Set<TicketEntity> tickets;
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private CustomerEntity customer;
    
    public void addTicket(TicketEntity ticket) {
    	if (Objects.isNull(this.tickets)) this.tickets = new HashSet<>();
    	this.tickets.add(ticket);
    }
    
    public void removeTicket(UUID id) {
    	if (Objects.isNull(this.tickets)) this.tickets = new HashSet<>();
    	this.tickets.removeIf(ticket -> ticket.getId().equals(id));
    }
    
    public void updateTickets() {
    	this.tickets.forEach(ticket -> ticket.setTour(this));
    }
    
    public void addReservations(ReservationEntity reservationEntity) {
    	if (Objects.isNull(this.reservations)) this.reservations = new HashSet<>();
    	this.reservations.add(reservationEntity);
    }
    
    public void removeReservations(UUID idReservation) {
    	if (Objects.isNull(this.reservations)) this.reservations = new HashSet<>();
    	this.reservations.removeIf(ticket -> ticket.getId().equals(idReservation));
    }
    
    public void updateReservations() {
    	this.reservations.forEach(ticket -> ticket.setTour(this));
    }
}
