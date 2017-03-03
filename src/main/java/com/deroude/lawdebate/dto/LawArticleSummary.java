/*
 * Copyright 2017  Valentin Raduti.
 * This source code file is the property of Valentin Raduti.
 * You are not allowed to view, edit, copy, re-use or re-engineer
 * this source code file unless specifically permissioned by Valentin Raduti.
 */
package com.deroude.lawdebate.dto;

import com.deroude.lawdebate.domain.ArticleVersion;
import com.deroude.lawdebate.domain.LawArticle;

/**
 *
 * @author valentin.raduti
 */
public class LawArticleSummary {

    private final LawArticle article;
    private final ArticleVersion currentVersion;
    private final long yayVotes, nayVotes, abstainVotes;

    public LawArticleSummary(LawArticle article, ArticleVersion currentVersion, long yayVotes, long nayVotes, long abstainVotes) {
	this.article = article;
	this.currentVersion = currentVersion;
	this.yayVotes = yayVotes;
	this.nayVotes = nayVotes;
	this.abstainVotes = abstainVotes;
    }

    public LawArticle getArticle() {
	return article;
    }

    public ArticleVersion getCurrentVersion() {
	return currentVersion;
    }

    public long getYayVotes() {
	return yayVotes;
    }

    public long getNayVotes() {
	return nayVotes;
    }

    public long getAbstainVotes() {
	return abstainVotes;
    }

}
