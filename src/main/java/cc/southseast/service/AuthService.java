package cc.southseast.service;

/**
 * @Author: Southseast
 * @Date: 2019-06-02 22:44
 * @Version 1.0
 */
public interface AuthService {
    boolean send(String to, String subject, String html);
}
