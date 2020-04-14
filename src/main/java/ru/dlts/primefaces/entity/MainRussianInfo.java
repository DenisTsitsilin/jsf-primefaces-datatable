package ru.dlts.primefaces.entity;

import lombok.Data;

@Data
public class MainRussianInfo {
    String date;
    Long infected;
    Long infectedPlus;
    Long healed;
    Long healedPlus;
    Long die;
    Long diePlus;
    Long active;
    String testsCount;
    String testsPlus;
}
