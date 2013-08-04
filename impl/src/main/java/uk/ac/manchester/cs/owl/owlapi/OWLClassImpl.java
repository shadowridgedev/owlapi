/*
 * This file is part of the OWL API.
 *
 * The contents of this file are subject to the LGPL License, Version 3.0.
 *
 * Copyright (C) 2011, The University of Manchester
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 *
 * Alternatively, the contents of this file may be used under the terms of the Apache License, Version 2.0
 * in which case, the provisions of the Apache License Version 2.0 are applicable instead of those above.
 *
 * Copyright 2011, University of Manchester
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.manchester.cs.owl.owlapi;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.annotation.Nonnull;

import org.semanticweb.owlapi.model.ClassExpressionType;
import org.semanticweb.owlapi.model.EntityType;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLClassExpressionVisitor;
import org.semanticweb.owlapi.model.OWLClassExpressionVisitorEx;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLEntityVisitor;
import org.semanticweb.owlapi.model.OWLEntityVisitorEx;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLNamedObjectVisitor;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectVisitor;
import org.semanticweb.owlapi.model.OWLObjectVisitorEx;
import org.semanticweb.owlapi.model.OWLRuntimeException;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

/** Author: Matthew Horridge<br>
 * The University Of Manchester<br>
 * Bio-Health Informatics Group<br>
 * Date: 25-Oct-2006<br>
 * <br> */
@SuppressWarnings("javadoc")
public class OWLClassImpl extends OWLClassExpressionImpl implements OWLClass,
        Serializable {
    private static final long serialVersionUID = 40000L;
    private final IRI iri;
    private final boolean isThing;
    private final boolean isNothing;

    public OWLClassImpl(@Nonnull IRI iri) {
        this.iri = checkNotNull(iri);
        isThing = getIRI().equals(OWLRDFVocabulary.OWL_THING.getIRI());
        isNothing = getIRI().equals(OWLRDFVocabulary.OWL_NOTHING.getIRI());
    }

    @Override
    public boolean isTopEntity() {
        return isOWLThing();
    }

    @Override
    public boolean isBottomEntity() {
        return isOWLNothing();
    }

    @Override
    public ClassExpressionType getClassExpressionType() {
        return ClassExpressionType.OWL_CLASS;
    }

    @Override
    public OWLClassExpression getObjectComplementOf() {
        return df.getOWLObjectComplementOf(this);
    }

    @Override
    public EntityType<?> getEntityType() {
        return EntityType.CLASS;
    }

    @Override
    public boolean isType(EntityType<?> entityType) {
        return getEntityType().equals(entityType);
    }

    @Override
    public String toStringID() {
        return iri.toString();
    }

    @Override
    public IRI getIRI() {
        return iri;
    }

    @Override
    public boolean isBuiltIn() {
        return isOWLThing() || isOWLNothing();
    }

    @Override
    public boolean isAnonymous() {
        return false;
    }

    @Override
    public boolean isClassExpressionLiteral() {
        return true;
    }

    @Override
    public OWLClass asOWLClass() {
        return this;
    }

    @Override
    public boolean isOWLThing() {
        return isThing;
    }

    @Override
    public boolean isOWLNothing() {
        return isNothing;
    }

    @Override
    public OWLClassExpression getNNF() {
        return this;
    }

    @Override
    public Set<OWLClassExpression> asConjunctSet() {
        return Collections.singleton((OWLClassExpression) this);
    }

    @Override
    public boolean containsConjunct(OWLClassExpression ce) {
        return ce.equals(this);
    }

    @Override
    public Set<OWLClassExpression> asDisjunctSet() {
        return Collections.singleton((OWLClassExpression) this);
    }

    @Override
    public OWLClassExpression getComplementNNF() {
        return df.getOWLObjectComplementOf(this);
    }

    @Override
    public OWLDataProperty asOWLDataProperty() {
        throw new OWLRuntimeException("Not a data property!");
    }

    @Override
    public OWLDatatype asOWLDatatype() {
        throw new OWLRuntimeException("Not a data type!");
    }

    @Override
    public OWLNamedIndividual asOWLNamedIndividual() {
        throw new OWLRuntimeException("Not an individual!");
    }

    @Override
    public OWLObjectProperty asOWLObjectProperty() {
        throw new OWLRuntimeException("Not an object property");
    }

    @Override
    public boolean isOWLClass() {
        return true;
    }

    @Override
    public boolean isOWLDataProperty() {
        return false;
    }

    @Override
    public boolean isOWLDatatype() {
        return false;
    }

    @Override
    public boolean isOWLNamedIndividual() {
        return false;
    }

    @Override
    public boolean isOWLObjectProperty() {
        return false;
    }

    @Override
    public OWLAnnotationProperty asOWLAnnotationProperty() {
        throw new OWLRuntimeException("Not an annotation property");
    }

    @Override
    public boolean isOWLAnnotationProperty() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            if (!(obj instanceof OWLClass)) {
                return false;
            }
            IRI otherIRI = ((OWLClass) obj).getIRI();
            return otherIRI.equals(iri);
        }
        return false;
    }

    @Override
    public void accept(OWLClassExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(OWLEntityVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(OWLObjectVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(OWLNamedObjectVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <O> O accept(OWLEntityVisitorEx<O> visitor) {
        return visitor.visit(this);
    }

    @Override
    public <O> O accept(OWLClassExpressionVisitorEx<O> visitor) {
        return visitor.visit(this);
    }

    @Override
    public <O> O accept(OWLObjectVisitorEx<O> visitor) {
        return visitor.visit(this);
    }

    @Override
    protected int compareObjectOfSameType(OWLObject object) {
        OWLClass other = (OWLClass) object;
        return iri.compareTo(other.getIRI());
    }
}
