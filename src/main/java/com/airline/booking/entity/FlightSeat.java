package com.airline.booking.entity;

import com.airline.booking.util.SeatClass;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "flight_seates", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"flight_id", "seat_class"})
})
@Getter @Setter
public class FlightSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_class", nullable = false)
    private SeatClass seatClass;

    @Column(name = "total_seats", nullable = false)
    private int totalSeats;

    @Column(name = "booked_seats", columnDefinition = "INT DEFAULT 0")
    private int bookedSeats = 0;

    @Version // For optimistic locking
    private int version;
}
