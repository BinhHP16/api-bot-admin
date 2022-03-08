package com.saltlux.botadmin.repository;

import com.saltlux.botadmin.dto.user.UserDto;
import com.saltlux.botadmin.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT s from UserEntity s")
    Page<UserEntity> search(Pageable pageable);

    @Query("SELECT s from UserEntity s where s.id=:userId")
    UserEntity findByUserId(@Param("userId") Integer userId);

    @Query(value = " SELECT * FROM data_bot_admin.user where  user.email=:email", nativeQuery = true)
    UserEntity findByEmailAndPassword(String email);

//    @Query("select u from UserEntity u where u.hoTen like '%thuy%'")
//    @Query(value = " SELECT * FROM data_bot_admin.user where ho_ten like '%userName%'", nativeQuery = true)
    @Query(value="select * from data_bot_admin.user u where u.ho_ten like %:userName%", nativeQuery=true)
    List<UserEntity> finByname(@Param("userName") String userName);
}
