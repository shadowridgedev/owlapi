package org.semanticweb.owlapi.api.test;

import org.junit.Test;
import org.semanticweb.owlapi.io.StringDocumentSource;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.UnloadableImportException;

@SuppressWarnings("javadoc")
public class MissingImportTestCase {
    @Test(expected = UnloadableImportException.class)
    public void shouldThrowExceptionWithDefaultImportsconfig()
            throws OWLOntologyCreationException {
        String input = "<?xml version=\"1.0\"?>\n"
                + "<rdf:RDF xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"\n"
                + "     xmlns:owl=\"http://www.w3.org/2002/07/owl#\"\n"
                + "     xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"\n"
                + "     xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">\n"
                + "    <owl:Ontology rdf:about=\"http://www.semanticweb.org/fake/ontologies/2012/8/1\">\n"
                + "        <owl:imports rdf:resource=\"http://www.a.org/\"/>\n"
                + "    </owl:Ontology>\n" + "</rdf:RDF>";
        Factory.getManager().loadOntologyFromOntologyDocument(
                new StringDocumentSource(input));
    }
}
