package springblog.persistence.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import springblog.bl.dto.UserDTO;
import springblog.web.form.UserForm;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
    
    @CreationTimestamp
    private Timestamp created_at;
    
    @UpdateTimestamp
    private Timestamp updated_at;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> posts;

    public User() {
    	super();
    }
   
    public User(UserForm userForm) {
    	this.id = userForm.getId();
    	this.name = userForm.getName();
    	this.email = userForm.getEmail();
    	this.password = userForm.getPassword();
    }
    
    public User(UserDTO userDto) {
    	this.id = userDto.getId();
    	this.name = userDto.getName();
    	this.email = userDto.getEmail();
    	this.password = userDto.getPassword();
    }
}