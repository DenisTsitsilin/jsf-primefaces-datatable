package ru.dlts.primefaces.entity;

import lombok.Data;

import java.util.List;

@Data
public class Info {
    MainRussianInfo mainRussianInfo;
    MainWorldInfo mainWorldInfo;
    List<RussianStat> russianStat;
    List<WorldStat> worldStat;
}
