package com.app.osca.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralVO {
    private Long memberId;
    private String generalUniversity;
    private String generalCompany;
    private String generalWorkDateBegin;
    private String generalWorkDateEnd;
    private String generalIntroduction;
    private String generalCareer;
    private String generalSalary;
}
