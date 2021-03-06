package com.dormabook.domain.post;

import com.dormabook.web.dto.post.GetPostClassResponse;
import com.dormabook.web.dto.post.CommunityProfileResDto;
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

    //유저 아이디로 작성된 게시글 조회
    @Query(nativeQuery = true,value = "select * from post where member_id =:userId")
    List<Post> findByIdPostList(@Param("userId")String userId);

    //모든 게시글 조회
    @Query(nativeQuery = true,value = "select * from post order by post_created_at desc")
    List<Post> findByAllPostList();

    //멘티 멘토 게시글만 조회
    @Query(nativeQuery = true,value = "select * from post where post_role =:postRole order by post_created_at desc")
    List<Post> findByRolePostList(@Param("postRole")String postRole);

    //멘티 게시글 단건 조회
    @Query("select p from Post p join fetch p.member where p.postNo =:postNo")
    Post findByMenteePost(@Param("postNo")Long postNo);


    //멘토 게시글 단건 조회
    @Query(nativeQuery = true,value = "select p.post_title as postTitle, p.post_content as postContent, p.post_created_at as postCreatedAt, p.post_match_state as postMatchState,\n" +
            "       m.member_name as memberName, b.book_saveimagename as bookSaveimagename, b.bookimage_route as bookimageRoute\n" +
            "from post as p\n" +
            "    join member m on p.member_id = m.member_id\n" +
            "    left outer join book_image b on p.post_no = b.post_no\n" +
            "where p.post_no=:postNo")
    GetPostClassResponse findByPostClass(@Param("postNo")Long postNo);

    @Query(nativeQuery = true,value = "(select count(*) as 'count' from post where member_id =:userId)\n" +
                                    "    UNION\n" +
                                    "    (select count(*) as 'count'\n" +
                                    "     from application a\n" +
                                    "              join post p on a.post_no = p.post_no\n" +
                                    "     where member_id =:userId\n" +
                                    "       and a.application_acpt = 0)")
    List<CommunityProfileResDto> countPost(@Param("userId")String userId);

}
