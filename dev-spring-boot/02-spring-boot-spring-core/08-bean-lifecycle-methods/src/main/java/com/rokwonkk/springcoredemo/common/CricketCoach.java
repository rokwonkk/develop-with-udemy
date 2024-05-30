package com.rokwonkk.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {

    public CricketCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    //define our init method 앱실행되면 여기 메서드가 실행이 되고
    @PostConstruct
    public void doMyStartupStuff() {
        System.out.println("In doMyStartupStuff: " + getClass().getSimpleName());
    }

    //define our destroy method 앱을 종료시키면 여기의 메서드가 실행됨
    @PreDestroy
    public void doMyCleanUpStuff() {
        System.out.println("In doMyCleanUpStuff: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes :-)";
    }
}
