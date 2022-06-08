package com.dormabook.web.dto.application;

import java.sql.Timestamp;

public interface GetApplicationResponse {
    Long getPostNo();
    String getPostRule();
    String getPostTitle();
    Timestamp getNoticecreateAt();
    Long getApplicationNo();
}
