package com.does.biz.dao;

import com.does.biz.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface UserDAO {
	Optional<User> findByEmail(@Param("email") String email, @Param("provider") String provider);
	int save(User user);
	int update(User user);
}
