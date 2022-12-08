package com.university.universitymanagementapi.events;

import com.university.universitymanagementapi.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private User user;
    private String applicationUrl;

    public RegistrationCompleteEvent(User user, String applicatioUurl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicatioUurl;
    }

    public RegistrationCompleteEvent(Object source, Clock clock) {
        super(source, clock);
    }
}
