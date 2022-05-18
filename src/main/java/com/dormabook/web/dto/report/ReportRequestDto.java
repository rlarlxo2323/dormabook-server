package com.dormabook.web.dto.report;

import com.dormabook.domain.report.Report;
import com.dormabook.domain.studyroom.StudyRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportRequestDto {
    private Long reportNo;
    private StudyRoom studyroom;
    private String reportFilename;
    private String reportFileroute;
    private String reportSavefilename;

    @Builder
    public ReportRequestDto(Long reportNo, StudyRoom studyroom, String reportFilename, String reportFileroute, String reportSavefilename) {
        this.reportNo = reportNo;
        this.studyroom = studyroom;
        this.reportFilename = reportFilename;
        this.reportFileroute = reportFileroute;
        this.reportSavefilename = reportSavefilename;
    }

    public Report toEntity(){
        return Report.builder()
                .reportNo(reportNo)
                .studyroom(studyroom)
                .reportFilename(reportFilename)
                .reportFileroute(reportFileroute)
                .reportSavefilename(reportSavefilename)
                .build();
    }
}
