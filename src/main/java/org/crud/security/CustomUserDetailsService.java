package org.crud.security;

import org.crud.entities.User;
import org.crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName + " not found"));
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                getAuthorities(user));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] userRoles = user.getRoles().stream().map((role) -> role.getRole()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }

//    @Transactional
//    public void addUser(User user) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userRepository.saveAndFlush(user);
//    }

//    @Transactional
//    public void updateUser(User user) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//
//    }
//
//    @Transactional
//    public void removeUser(int id) {
//
//    }
//
//    @Transactional
//    public User getUserById(int id) {
//        return null; //userDao.getUserById(id);
//    }
//
//    @Transactional
//    public List<User> listUser() {
//        return null;
//    }
//
//    @Override
//    @Transactional
//    public List<Role> listRole() {
//        return null;
//    }
//
//    @Override
//    public Role getRoleById(Long id) {
//        return null;
//    }
}
