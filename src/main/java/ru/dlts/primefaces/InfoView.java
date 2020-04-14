package ru.dlts.primefaces;

import ru.dlts.primefaces.entity.Info;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class InfoView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private InfoService service;

    private Info info;

    @PostConstruct
    public void init() {
        info = service.searchInfo();
    }

    public Info getInfo() {
        return info;
    }
}
