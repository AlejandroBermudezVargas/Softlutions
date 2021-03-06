package com.cenfotec.dondeEs.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the service database table.
 * 
 */
@Entity
@Table(name="service")
@NamedQuery(name="Service.findAll", query="SELECT s FROM Service s")
public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="service_id")
	private int serviceId;

	private String description;

	private String name;

	private byte state;

	//bi-directional many-to-one association to AuctionService
	@OneToMany(mappedBy="service")
	private List<AuctionService> auctionServices;

	//bi-directional many-to-one association to ServiceCatalog
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="service_catalog_id")
	private ServiceCatalog serviceCatalog;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;

	//bi-directional many-to-one association to ServiceContact
	@OneToMany(mappedBy="service")
	private List<ServiceContact> serviceContacts;

	public Service() {
	}

	public int getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getState() {
		return this.state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public List<AuctionService> getAuctionServices() {
		return this.auctionServices;
	}

	public void setAuctionServices(List<AuctionService> auctionServices) {
		this.auctionServices = auctionServices;
	}

	public AuctionService addAuctionService(AuctionService auctionService) {
		getAuctionServices().add(auctionService);
		auctionService.setService(this);

		return auctionService;
	}

	public AuctionService removeAuctionService(AuctionService auctionService) {
		getAuctionServices().remove(auctionService);
		auctionService.setService(null);

		return auctionService;
	}

	public ServiceCatalog getServiceCatalog() {
		return this.serviceCatalog;
	}

	public void setServiceCatalog(ServiceCatalog serviceCatalog) {
		this.serviceCatalog = serviceCatalog;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ServiceContact> getServiceContacts() {
		return this.serviceContacts;
	}

	public void setServiceContacts(List<ServiceContact> serviceContacts) {
		this.serviceContacts = serviceContacts;
	}

	public ServiceContact addServiceContact(ServiceContact serviceContact) {
		getServiceContacts().add(serviceContact);
		serviceContact.setService(this);

		return serviceContact;
	}

	public ServiceContact removeServiceContact(ServiceContact serviceContact) {
		getServiceContacts().remove(serviceContact);
		serviceContact.setService(null);

		return serviceContact;
	}

}