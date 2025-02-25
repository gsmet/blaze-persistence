/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright Blazebit
 */

package com.blazebit.persistence.spring.data.testsuite.webflux.controller;

import com.blazebit.persistence.spring.data.testsuite.webflux.repository.DocumentRepository;
import com.blazebit.persistence.spring.data.testsuite.webflux.repository.ReadOnlyDocumentViewRepository;
import com.blazebit.persistence.spring.data.testsuite.webflux.view.DocumentUpdateView;
import com.blazebit.persistence.spring.data.testsuite.webflux.view.DocumentView;
import com.blazebit.persistence.spring.data.webflux.EntityViewId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author Moritz Becker
 * @since 1.5.0
 */
@RestController
public class DocumentController {

    private final ReadOnlyDocumentViewRepository readOnlyDocumentViewRepository;
    private final DocumentRepository documentRepository;

    public DocumentController(ReadOnlyDocumentViewRepository readOnlyDocumentViewRepository, DocumentRepository documentRepository) {
        this.readOnlyDocumentViewRepository = readOnlyDocumentViewRepository;
        this.documentRepository = documentRepository;
    }

    @PutMapping(
            value = "/documents/{id1}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/vnd.blazebit.update1+json"
    )
    public ResponseEntity<DocumentView> updateDocument1(@EntityViewId("id1") @RequestBody DocumentUpdateView documentUpdate) {
        return updateDocument0(documentUpdate);
    }

    @PutMapping(
            value = "/documents/{id2}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/vnd.blazebit.update2+json"
    )
    public ResponseEntity<DocumentView> updateDocument2(@PathVariable(value = "id2") Long idParam, @EntityViewId("id2") @RequestBody DocumentUpdateView documentUpdate) {
        Objects.requireNonNull(idParam);
        return updateDocument0(documentUpdate);
    }

    @PutMapping(
            value = "/documents",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DocumentView> updateDocument3(@RequestBody DocumentUpdateView documentUpdate) {
        return updateDocument0(documentUpdate);
    }

    private ResponseEntity<DocumentView> updateDocument0(DocumentUpdateView documentUpdate) {
        documentRepository.updateDocument(documentUpdate);
        DocumentView documentView = readOnlyDocumentViewRepository.findOne(documentUpdate.getId());
        return ResponseEntity.ok(documentView);
    }
}
