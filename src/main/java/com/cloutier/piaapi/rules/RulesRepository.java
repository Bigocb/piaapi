package com.cloutier.piaapi.rules;

import com.cloutier.piaapi.news.OutboundNewsResponse;
import org.apache.tomcat.util.digester.Rules;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

@Repository
public class RulesRepository {

    private final DSLContext jooq;

    @Autowired
    public RulesRepository(DSLContext jooq) {
        this.jooq = jooq;
    }

    public RulesResponse getUserRulesRepo(String user, String category) {
        RulesResponse rulesResponse =
                jooq.selectFrom(table("rules"))
                        .where(field("user").eq(user)).and(field("category").eq(category)
                ).fetchOneInto(RulesResponse.class);
        return rulesResponse;
    }

    public RulesResponse insertNewRule(Rule ruleObject) {
        RulesResponse rulesResponse = new RulesResponse();
        return rulesResponse;
    }
}
