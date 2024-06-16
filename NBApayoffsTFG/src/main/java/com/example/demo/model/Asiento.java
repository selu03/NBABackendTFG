package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Asiento {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private int NumeroAsiento;
	    private int Sector;
	    private double Precio;
	    public int getSector() {
			return Sector;
		}

		public void setSector(int i) {
			Sector = i;
		}

	
	    @ManyToOne
	    @JoinColumn(name = "evento_id")
	    private Evento evento;
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public int getNumeroAsiento() {
			return NumeroAsiento;
		}

		public void setNumeroAsiento(int i) {
			this.NumeroAsiento = i;
		}

		public Evento getEvent() {
			return evento;
		}

		public void setEvent(Evento event) {
			this.evento = event;
		}


		public double getPrecio() {
			return Precio;
		}

		public void setPrecio(double precio) {
			Precio = precio;
		}

		public Asiento(Long id, int numeroAsiento, int sector, double precio, Evento event) {
			super();
			this.id = id;
			NumeroAsiento = numeroAsiento;
			Sector = sector;
			Precio = precio;
			this.evento = event;
		}

		public Asiento() {
			// TODO Auto-generated constructor stub
		}

	    
	}


