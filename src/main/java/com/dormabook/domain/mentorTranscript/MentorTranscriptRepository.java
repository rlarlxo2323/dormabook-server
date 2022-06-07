package com.dormabook.domain.mentorTranscript;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorTranscriptRepository extends JpaRepository<MentorTranscript, Long> {

}
