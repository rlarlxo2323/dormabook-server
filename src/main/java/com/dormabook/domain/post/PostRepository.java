package com.dormabook.domain.post;

import com.dormabook.web.dto.post.GetClassListResponse;
import com.dormabook.web.dto.post.GetClassListResponseDto;
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

    @Query(nativeQuery = true,value = "select * from post where member_id =:userId")
    List<Post> findByIdPostList(@Param("userId")String userId);

}
