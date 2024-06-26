package pe.edu.unfv.besttraveludemy.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import pe.edu.unfv.besttraveludemy.util.AeroLine;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "fly")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FlyEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double originLat;
    private Double originLng;
    private Double destinyLat;
    private Double destinyLng;
    private BigDecimal price;
    @Column(length = 20)
    private String originName;
    @Column(length = 20)
    private String destinyName;
    @Enumerated(EnumType.STRING)
    private AeroLine aeroLine;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            mappedBy = "fly"
    )
    private Set<TicketEntity> tickets;
}
