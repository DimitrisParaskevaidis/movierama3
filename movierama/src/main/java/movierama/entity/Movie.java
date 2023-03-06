package movierama.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MOVIE")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column
	private String title;
	@Column
	private String description;
	@Column
	private String username;
	@Column
	private Date publicationdate;
	@Column
	private Date addeddate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getPublicationdate() {
		return publicationdate;
	}

	public void setPublicationdate(Date publicationdate) {
		this.publicationdate = publicationdate;
	}

	public Date getAddeddate() {
		return addeddate;
	}

	public void setAddeddate(Date addeddate) {
		this.addeddate = addeddate;
	}

}
