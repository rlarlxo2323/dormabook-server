package com.dormabook.service.post;

import com.dormabook.domain.post.Post;
import com.dormabook.domain.post.PostFileStorageProperties;
import com.dormabook.domain.post.PostRepository;
import com.dormabook.security.JwtTokenProvider;
import com.dormabook.web.dto.post.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final Path fileStorageLocation;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public PostService(PostFileStorageProperties postFileStorageProperties, PostRepository postRepository, JwtTokenProvider jwtTokenProvider) {
        this.postRepository = postRepository;
        this.fileStorageLocation = Paths.get(postFileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        this.jwtTokenProvider = jwtTokenProvider;

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public PostResponseDto save(PostSaveRequestDto dto){
        return new PostResponseDto(postRepository.save(dto.toEntity()));
    }

    public Optional<Post> getPostNo(Long no) { return postRepository.findById(no);}

    public String mentoPostUpload(MultipartFile file) {

        String ImageName = StringUtils.cleanPath(file.getOriginalFilename()); // origin
        UUID uuid = UUID.randomUUID();
        String bookSaveImageName = uuid + "_" + ImageName;      //이미지 이름에 UUId 더하기

        try {
            // Check if the file's name contains invalid characters
            if (ImageName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + bookSaveImageName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(bookSaveImageName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);


            return bookSaveImageName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + bookSaveImageName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = (Resource) new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    public List<PostByCommunityResponseDto> findByPostList(String postRule) {
        return postRepository.findByPostList(postRule)
                .stream()
                .map(PostByCommunityResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<PostListResponseDto> findByAllPostList() {
        return postRepository.findByAllPostList()
                .stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    //postNo
    public GetPostClassResponse findByPostClass(Long postNo){
        return postRepository.findByPostClass(postNo);
    }


    public List<PostListResponseDto> findByRolePostList(String postRule) {
        return postRepository.findByRolePostList(postRule)
                .stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    public Post findByMenteePost(Long postNo) {
        return postRepository.findByMenteePost(postNo);
    }


    public List<PostListResponseDto> findByIdPostList(String userId){

        return postRepository.findByIdPostList(userId)
                .stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }


    public String findByIdJwt(String jwt){
        return jwtTokenProvider.getAuthentication(jwt).getName();
    }

    public List<CommunityProfileResDto> getProfile(String userId) {
        return postRepository.countPost(userId);
    }
}
