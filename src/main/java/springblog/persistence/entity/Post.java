package springblog.persistence.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springblog.bl.dto.PostDTO;
import springblog.web.form.PostForm;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@CreationTimestamp
	private Timestamp created_at;

	@UpdateTimestamp
	private Timestamp updated_at;

	@ManyToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "user_id")
	private User user;
	
	public Post() {
	    super();
	}

	public Post(PostForm postForm) {
		this.title = postForm.getTitle();
		this.description = postForm.getDescription();
	}

	public Post(PostDTO postDTO) {
		this.title = postDTO.getTitle();
		this.description = postDTO.getDescription();
	}
}
