package com.example.bluedragon.service.email;

import com.example.bluedragon.domain.User;
import com.example.bluedragon.dto.response.SearchResponse;
import com.example.bluedragon.dto.response.SearchResponses;
import com.example.bluedragon.service.ScholarshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailSender emailSender;
    private final ScholarshipService scholarshipService;

    public void sendEmail(User user) {
        SearchResponses searchResponses = scholarshipService.searchByUser(user);

        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());

        for (SearchResponse searchResponse : searchResponses.scholarships()) {
            stringJoiner.add(resolveMessage(searchResponse.name(), searchResponse.money(), searchResponse.redirectUrl()));
        }
        emailSender.sendEmailToMember(user.getEmail(),
                "지원가능한 장학금 정보가 도착했어요!",
                        stringJoiner.toString()
                );
    }

    private String resolveMessage(String name, long money, String url) {
        return "- " + name + " - 지원금액 : " + money + "만원 [바로가기: " + url + "]";
    }
}
