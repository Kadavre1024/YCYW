package com.ocow.back.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "RENTALS")
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column(name = "start_day")
	private LocalDate startDay;
	
	@Column(name = "end_day")
	private LocalDate endDay;
	
	@NonNull
	@OneToOne
	@JoinColumn(name = "vehicle_category_id", referencedColumnName = "id")
	private VehicleCategory vehicleCategory;
	
	@NonNull
	@ManyToOne
	@JoinColumn(name = "client_user_id", referencedColumnName = "id")
	private ClientUser clientUser;
	
	@NonNull
	@OneToOne
	@JoinColumn(name = "agency_start_id", referencedColumnName = "id")
	public Agency agencyStart;
	
	@NonNull
	@ManyToOne
	@JoinColumn(name = "agency_end_id", referencedColumnName = "id")
	public Agency agencyEnd;
	
	@Column(name = "rental_price")
	private Integer rentalPrice;
	
	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
    @Column(name = "updated_at")
	private LocalDateTime updatedAt;
}
