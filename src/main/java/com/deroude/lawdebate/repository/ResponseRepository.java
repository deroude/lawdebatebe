/*
 * Copyright 2017  Valentin Raduti.
 * This source code file is the property of Valentin Raduti.
 * You are not allowed to view, edit, copy, re-use or re-engineer
 * this source code file unless specifically permissioned by Valentin Raduti.
 */
package com.deroude.lawdebate.repository;

import com.deroude.lawdebate.domain.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author valentin.raduti
 */
@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {

    public Page<Response> findByArticleId(Long articleId, Pageable preq);

}
