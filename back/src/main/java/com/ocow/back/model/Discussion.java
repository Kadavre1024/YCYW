package com.ocow.back.model;

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
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "DISCUSSIONS")
public class Discussion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@NonNull
	@ManyToOne
	@JoinColumn(name = "client_user_id", referencedColumnName = "id")
	private ClientUser clientUser;
	
	@NonNull
	private String subject;
	
	@NonNull
	@ManyToOne
	@JoinColumn(name = "rental_id", referencedColumnName = "id")
	public Rental rental;
	
	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
    @Column(name = "updated_at")
	private LocalDateTime updatedAt;
}
