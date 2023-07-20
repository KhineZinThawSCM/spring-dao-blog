package springblog.persistence.dao.userProfile;

import springblog.persistence.entity.UserProfile;

public interface UserProfileDao {
    void save(UserProfile userProfile);
    
    void update(UserProfile userProfile);
    
    UserProfile findByUserId(Long id);
}
