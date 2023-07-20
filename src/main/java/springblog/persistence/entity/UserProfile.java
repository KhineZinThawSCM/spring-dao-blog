package springblog.persistence.entity;

import java.sql.Timestamp;

import javax.annotation.Nullable;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_profile")
@Getter
@Setter
@AllArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "photo")
    @Nullable
    private String photo;
    
    @OneToOne
    private User user;
    
    @CreationTimestamp
    private Timestamp created_at;
    
    @UpdateTimestamp
    private Timestamp updated_at;
    
    public UserProfile() {
    	super();
    }
}
