package com.fatec.mogi.model.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fatec.mogi.util.ConstantsUtil;

@Entity
public class Disc extends DomainEntity {
	@Column(nullable = false)
	private boolean active;
	@Column(nullable = false)
	private double value;
	@Column(nullable = false, unique = true)
	private String code;
	@Column(nullable = false)
	private String imgLink;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	@DateTimeFormat(pattern = ConstantsUtil.DATE_FORMAT)
	@Temporal(TemporalType.DATE)
	private Date releaseDate;
	@Column(nullable = false)
	private String name;
	// TODO CHECK IF THIS ATRIBUTE IS REALLY NEEDED OR CAN BE CHANGED TO AN ENUM
	@Column(nullable = false)
	private String status;
	@OneToOne
	private ActivationDetails activationDetails;
	@ManyToMany
	private List<Artist> artists;
	@ManyToMany
	private List<Genre> genres;
	@ManyToOne
	private Pricing pricing;
	@ManyToOne
	private Recorder recorder;
	@OneToMany(mappedBy = "disc")
	@JsonIgnoreProperties("disc")
	private List<Stock> stock;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ActivationDetails getActivationDetails() {
		return activationDetails;
	}

	public void setActivationDetails(ActivationDetails activationDetails) {
		this.activationDetails = activationDetails;
	}

	public List<Artist> getArtists() {
		return artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public Pricing getPricing() {
		return pricing;
	}

	public void setPricing(Pricing pricing) {
		this.pricing = pricing;
	}

	public Recorder getRecorder() {
		return recorder;
	}

	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}

	public List<Stock> getStock() {
		return stock;
	}

	public void setStock(List<Stock> stock) {
		this.stock = stock;
	}

}
