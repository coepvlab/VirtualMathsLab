package in.ac.coep.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
 
import in.ac.coep.dao.UserDao;
import in.ac.coep.entity.User;
 
@Service
public class CustomUserService implements UserDetailsService {
 
     @Autowired
     private UserDao userDao;
     
     
    public User loadUserByUsername(String username) throws UsernameNotFoundException{
    	
        try {
			return (User) userDao.getUserByEmailId(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
    }


//	public User loadUserByLoginEmailId(String username) throws UsernameNotFoundException {
//		try {
//			return (User) userDao.getUserByLoginEmailId(username);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//	}


	public User loadUserByUserId(long userId) throws UsernameNotFoundException {
		try {
			return (User) userDao.getUserByLoginUserId(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
 
}