package movierama.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "disp")
public class Disposition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String username;
	@Column
	private String movietitle;
	@Column
	private String disposition;

	public Disposition(String username, String movietitle, String disposition) {
		super();
		this.username = username;
		this.movietitle = movietitle;
		this.disposition = disposition;
	}

	public Disposition() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMovietitle() {
		return movietitle;
	}

	public void setMovietitle(String movietitle) {
		this.movietitle = movietitle;
	}

	public String getDisposition() {
		return disposition;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}



}
