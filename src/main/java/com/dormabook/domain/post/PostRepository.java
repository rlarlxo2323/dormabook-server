package com.dormabook.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    //게시글 5개만 조회 (리스트 반환 - 게시글 역할로 조회)
    @Query(nativeQuery = true,value = "select * from post where post_role =:postRole order by post_created_at desc limit 5")
    List<Post> findByPostList(@Param("postRole")String postRole);


    //모든 게시글 조회
    @Query(nativeQuery = true,value = "select * from post order by post_created_at desc")
    List<Post> findByAllPostList();

    //멘티 멘토 게시글만 조회
    @Query(nativeQuery = true,value = "select * from post where post_role =:postRole order by post_created_at desc")
    List<Post> findByRolePostList(@Param("postRole")String postRole);
}
