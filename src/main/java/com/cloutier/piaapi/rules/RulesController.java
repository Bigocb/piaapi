package com.cloutier.piaapi.rules;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RulesController {

    private RulesService rulesService;

//    @GetMapping("/user/rules")
//    public RulesResponse getUserRules(@RequestBody String user, String category) {
//        return rulesService.getUserRules(user,category);
//    }
}
