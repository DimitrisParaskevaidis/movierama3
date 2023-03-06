package movierama.dto;

public class MovieDto {

	private String title;
	private String description;
	private String username;
	private String publicationdate;
	private String addeddate;
	private long likes;
	private long hates;

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

	public String getPublicationdate() {
		return publicationdate;
	}

	public void setPublicationdate(String publicationdate) {
		this.publicationdate = publicationdate;
	}

	public String getAddeddate() {
		return addeddate;
	}

	public void setAddeddate(String addeddate) {
		this.addeddate = addeddate;
	}

	public long getLikes() {
		return likes;
	}

	public void setLikes(long likes) {
		this.likes = likes;
	}

	public long getHates() {
		return hates;
	}

	public void setHates(long hates) {
		this.hates = hates;
	}

}
