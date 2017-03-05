/*
 * Copyright 2017  Valentin Raduti.
 * This source code file is the property of Valentin Raduti.
 * You are not allowed to view, edit, copy, re-use or re-engineer
 * this source code file unless specifically permissioned by Valentin Raduti.
 */
package com.deroude.lawdebate.repository;

import com.deroude.lawdebate.domain.LawArticle;
import com.deroude.lawdebate.dto.LawArticleSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author valentin.raduti
 */
@Repository
public interface LawArticleRepository extends JpaRepository<LawArticle, Long> {
//where (:search is null or l.agency like :search or v.title like :search)

    @Query("select new com.deroude.lawdebate.dto.LawArticleSummary (l,v,count(y),count(n),count(a)) from ArticleVersion v "
	    + "join v.article l on v.last=1 "
	    + "left join l.responses yr on yr.position = 'YAY' left join yr.votes y "
	    + "left join l.responses nr on nr.position='NAY' left join nr.votes n "
	    + "left join l.responses ar on ar.position='ABSTAIN' left join ar.votes a "
	    + "where l.agency like %:search% or v.title like %:search% group by l.id")
    public Page<LawArticleSummary> getArticles(@Param("search") String search, Pageable preq);

    @Query("select new com.deroude.lawdebate.dto.LawArticleSummary (l,v,count(y),count(n),count(a)) from ArticleVersion v "
	    + "join v.article l on v.last=1 "
	    + "left join l.responses yr on yr.position = 'YAY' left join yr.votes y "
	    + "left join l.responses nr on nr.position='NAY' left join nr.votes n "
	    + "left join l.responses ar on ar.position='ABSTAIN' left join ar.votes a "
	    + "where l.id=:id group by l.id")
    public LawArticleSummary getArticle(@Param("id") Long id);
}
