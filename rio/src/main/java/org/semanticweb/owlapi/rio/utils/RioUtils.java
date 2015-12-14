/*
 * This file is part of the OWL API.
 * 
 * The contents of this file are subject to the LGPL License, Version 3.0.
 * 
 * Copyright (C) 2011, The University of Queensland
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see http://www.gnu.org/licenses/.
 * 
 * 
 * Alternatively, the contents of this file may be used under the terms of the Apache License,
 * Version 2.0 in which case, the provisions of the Apache License Version 2.0 are applicable
 * instead of those above.
 * 
 * Copyright 2011, The University of Queensland
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.semanticweb.owlapi.rio.utils;

import static org.semanticweb.owlapi.util.OWLAPIStreamUtils.asList;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import org.openrdf.OpenRDFUtil;
import org.openrdf.model.BNode;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.SimpleValueFactory;
import org.openrdf.model.vocabulary.XMLSchema;
import org.semanticweb.owlapi.io.RDFLiteral;
import org.semanticweb.owlapi.io.RDFNode;
import org.semanticweb.owlapi.io.RDFResourceIRI;
import org.semanticweb.owlapi.io.RDFTriple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilities for translating between OWLAPI and Sesame Rio.
 * 
 * @author Peter Ansell p_ansell@yahoo.com
 * @since 4.0.0
 */
public final class RioUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(RioUtils.class);

    private RioUtils() {}

    /**
     * Create a Statement based on the given RDFTriple, with an empty context.
     * 
     * @param triple
     *        The OWLAPI {@link RDFTriple} to convert.
     * @return An OpenRDF {@link Statement} representing the given RDFTriple.
     */
    public static @Nullable Statement tripleAsStatement(final RDFTriple triple) {
        return tripleAsStatements(triple).stream().findFirst().orElse(null);
    }

    /**
     * Create a collection of Statements with the given contexts.
     * 
     * @param triple
     *        The OWLAPI {@link RDFTriple} to convert.
     * @param contexts
     *        If context is not null, it is used to create a context statement
     * @return A collection of OpenRDF {@link Statement}s representing the given
     *         RDFTriple in each of the given contexts.
     */
    public static Collection<Statement> tripleAsStatements(final RDFTriple triple, final Resource... contexts) {
        OpenRDFUtil.verifyContextNotNull(contexts);
        final ValueFactory vf = SimpleValueFactory.getInstance();
        Resource subject;
        org.openrdf.model.IRI predicate;
        Value object;
        if (triple.getSubject() instanceof RDFResourceIRI) {
            try {
                subject = vf.createIRI(triple.getSubject().getIRI().toString());
            } catch (@SuppressWarnings("unused") IllegalArgumentException iae) {
                LOGGER.error("Subject URI was invalid: {}", triple);
                return Collections.emptyList();
            }
        } else {
            // FIXME: When blank nodes are no longer represented as IRIs
            // internally, need to fix this
            subject = node(triple.getSubject(), vf);
        }
        try {
            predicate = vf.createIRI(triple.getPredicate().getIRI().toString());
        } catch (@SuppressWarnings("unused") IllegalArgumentException iae) {
            LOGGER.error("Predicate URI was invalid: {}", triple);
            return Collections.emptyList();
        }
        if (triple.getObject() instanceof RDFResourceIRI) {
            try {
                object = vf.createIRI(triple.getObject().getIRI().toString());
            } catch (@SuppressWarnings("unused") IllegalArgumentException iae) {
                LOGGER.error("Object URI was invalid: {}", triple);
                return Collections.emptyList();
            }
        } else if (triple.getObject() instanceof RDFLiteral) {
            object = literal(vf, (RDFLiteral) triple.getObject());
        } else {
            // FIXME: When blank nodes are no longer represented as IRIs
            // internally, need to fix
            // this
            object = node(triple.getObject(), vf);
        }
        if (contexts == null || contexts.length == 0) {
            return Collections.singletonList(vf.createStatement(subject, predicate, object));
        } else {
            return asList(Stream.of(contexts).map(x -> vf.createStatement(subject, predicate, object, x)));
        }
    }

    /**
     * @param vf
     *        value factory
     * @param literalObject
     *        literal
     * @return value
     */
    protected static Value literal(final ValueFactory vf, final RDFLiteral literalObject) {
        Value object;
        if (literalObject.hasLang()) {
            object = vf.createLiteral(literalObject.getLexicalValue(),
                literalObject.getLang());
        } else if (literalObject.isPlainLiteral()) {
            object = vf.createLiteral(literalObject.getLexicalValue(),
                XMLSchema.STRING);
        } else {
            object = vf.createLiteral(literalObject.getLexicalValue(),
                vf.createIRI(literalObject.getDatatype().toString()));
        }
        return object;
    }

    /**
     * @param node
     *        subject or object node
     * @param vf
     *        value factory
     * @return blank node
     */
    protected static BNode node(final RDFNode node, final ValueFactory vf) {
        if (node.getIRI().getNamespace().startsWith("_:")) {
            return vf.createBNode(node.getIRI().toString().substring(2));
        }
        return vf.createBNode(node.getIRI().toString());
    }
}
