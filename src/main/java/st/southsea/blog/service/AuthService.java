package st.southsea.blog.service;

/**
 * @Author: South
 * @Date: 2019-06-02 22:44
 */
public interface AuthService {
    boolean send(String to, String subject, String html);
}
