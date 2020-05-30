package com.gary.service;

import com.gary.mapper.UserAuthorityMapper;
import com.gary.mapper.UserMapper;
import com.gary.model.UserAuthorityEntity;
import com.gary.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAuthorityMapper userAuthorityMapper;

    /**
     * check username && password
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userMapper.findByUsername(username);

        if(userEntity != null){
            List<UserAuthorityEntity> userAuthorityEntitys = userAuthorityMapper.findByid(userEntity.getId());
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            for(UserAuthorityEntity userAuthorityEntity :userAuthorityEntitys){
                GrantedAuthority authority = new SimpleGrantedAuthority(userAuthorityEntity.getRole());
                authorities.add(authority);
            }
            userEntity.setAuthorities(authorities);
        }
        return new User(userEntity.getUsername(),userEntity.getPassword(),userEntity.getAuthorities());
    }

}
