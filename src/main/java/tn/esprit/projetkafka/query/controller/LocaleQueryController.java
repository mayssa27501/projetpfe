package tn.esprit.projetkafka.query.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.projetkafka.query.entity.LocaleView;
import tn.esprit.projetkafka.query.service.LocaleQueryService;

import java.util.List;

@RestController
@RequestMapping("/query/locales")
@CrossOrigin(origins = "http://localhost:4200")
public class LocaleQueryController {
    private final LocaleQueryService queryService;

    @Autowired
    public LocaleQueryController(LocaleQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public List<LocaleView> getAllLocales() {
        return queryService.getAllLocales();
    }
}