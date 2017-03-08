package org.semanticweb.owlapi.profiles;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.semanticweb.owlapi.formats.FunctionalSyntaxDocumentFormat;
import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;

@SuppressWarnings("javadoc")
@RunWith(Parameterized.class)
public class ProfileNoQLTestCase extends ProfileBase {

    private final String premise;

    public ProfileNoQLTestCase(String premise) {
        this.premise = premise;
    }

    @Test
    public void testNoQL() {
        test(premise.startsWith("<rdf:RDF") ? new RDFXMLDocumentFormat()
                        : new FunctionalSyntaxDocumentFormat(), premise, true, false, true, true);
    }

    @Parameters
    public static List<String> getData() {
        return Arrays.asList(
                        "<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\" xmlns:owl=\"http://www.w3.org/2002/07/owl#\"  xmlns:first=\"urn:test#\" xml:base=\"urn:test\"><owl:Ontology/><owl:Class rdf:about=\"urn:test#c1\"><owl:sameAs><owl:Class rdf:about=\"urn:test#c2\"/></owl:sameAs><first:annotate>description of c1</first:annotate></owl:Class><owl:AnnotationProperty rdf:about=\"urn:test#annotate\" /></rdf:RDF>",
                        "<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" xmlns:owl=\"http://www.w3.org/2002/07/owl#\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\" xmlns:ex=\"http://www.example.org#\" xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"><owl:TransitiveProperty rdf:about=\"http://www.example.org#p\"/><rdf:Description rdf:about=\"http://www.example.org#x\"><ex:p><rdf:Description rdf:about=\"http://www.example.org#y\"><ex:p rdf:resource=\"http://www.example.org#z\"/></rdf:Description></ex:p></rdf:Description></rdf:RDF>",
                        "<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" xmlns:owl=\"http://www.w3.org/2002/07/owl#\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\" xmlns:ex=\"http://www.example.org#\" xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"><owl:AllDifferent rdf:about=\"http://www.example.org#z\"><owl:members rdf:parseType=\"Collection\"><rdf:Description rdf:about=\"http://www.example.org#w1\"><owl:sameAs rdf:resource=\"http://www.example.org#w2\"/></rdf:Description><rdf:Description rdf:about=\"http://www.example.org#w2\"/><rdf:Description rdf:about=\"http://www.example.org#w3\"/></owl:members></owl:AllDifferent></rdf:RDF>",
                        "<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" xmlns:owl=\"http://www.w3.org/2002/07/owl#\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\" xmlns:ex=\"http://www.example.org#\" xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"><rdf:Description rdf:about=\"http://www.example.org#x\"><owl:sameAs rdf:resource=\"http://www.example.org#y\"/></rdf:Description></rdf:RDF>",
                        "<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" xmlns:owl=\"http://www.w3.org/2002/07/owl#\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\" xmlns:ex=\"http://www.example.org#\" xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"><rdf:Description rdf:about=\"http://www.example.org#x\"><owl:sameAs><rdf:Description rdf:about=\"http://www.example.org#y\"><owl:sameAs rdf:resource=\"http://www.example.org#z\"/></rdf:Description></owl:sameAs></rdf:Description></rdf:RDF>",
                        "<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" xmlns:owl=\"http://www.w3.org/2002/07/owl#\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\" xmlns:ex=\"http://www.example.org#\" xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"><owl:AllDifferent rdf:about=\"http://www.example.org#z\"><owl:distinctMembers rdf:parseType=\"Collection\"><rdf:Description rdf:about=\"http://www.example.org#w1\"><owl:sameAs rdf:resource=\"http://www.example.org#w2\"/></rdf:Description><rdf:Description rdf:about=\"http://www.example.org#w2\"/><rdf:Description rdf:about=\"http://www.example.org#w3\"/></owl:distinctMembers></owl:AllDifferent></rdf:RDF>",
                        "Prefix(:=<http://example.org/>) Prefix(xsd:=<http://www.w3.org/2001/XMLSchema#>) Ontology( Declaration(NamedIndividual(:a)) Declaration(DataProperty(:hasAge)) DataPropertyRange(:hasAge xsd:integer) ClassAssertion(DataHasValue(:hasAge \"aString\"^^xsd:string) :a))",
                        "Prefix(:=<http://example.org/>) Prefix(xsd:=<http://www.w3.org/2001/XMLSchema#>) Ontology( Declaration(NamedIndividual(:a)) Declaration(DataProperty(:hasAge)) FunctionalDataProperty(:hasAge) ClassAssertion(DataHasValue(:hasAge \"18\"^^xsd:integer) :a) ClassAssertion(DataHasValue(:hasAge \"19\"^^xsd:integer) :a))",
                        "<rdf:RDF xml:base=\"urn:test\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\" xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\" xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" xmlns:owl=\"http://www.w3.org/2002/07/owl#\"><owl:Ontology rdf:about=\"\"/><owl:ObjectProperty rdf:about=\"urn:test#p\"/><rdf:Description rdf:about=\"urn:test#p\"><owl:propertyChainAxiom rdf:parseType=\"Collection\"><rdf:Description rdf:about=\"urn:test#p\"/><rdf:Description rdf:about=\"urn:test#p\"/></owl:propertyChainAxiom></rdf:Description></rdf:RDF>",
                        "<rdf:RDF xml:base=\"http://example.org/\" xmlns=\"http://example.org/\" xmlns:owl=\"http://www.w3.org/2002/07/owl#\" xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"><owl:Ontology/><owl:Class rdf:about=\"GriffinFamilyMember\" /><owl:DatatypeProperty rdf:about=\"hasName\" /><rdf:Description rdf:about=\"GriffinFamilyMember\"><owl:hasKey rdf:parseType=\"Collection\"><rdf:Description rdf:about=\"hasName\" /></owl:hasKey></rdf:Description><rdf:Description rdf:about=\"Peter\"><hasName>Peter</hasName><hasName>Kichwa-Tembo</hasName><rdf:type rdf:resource=\"GriffinFamilyMember\" /></rdf:Description></rdf:RDF>",
                        "Prefix( :=<http://example.org/> ) Ontology( Declaration( Class( :GriffinFamilyMember ) ) Declaration( DataProperty( :hasName ) ) HasKey( :GriffinFamilyMember () ( :hasName ) ) DataPropertyAssertion( :hasName :Peter \"Peter\" ) DataPropertyAssertion( :hasName :Peter \"Kichwa-Tembo\" ) ClassAssertion( :GriffinFamilyMember :Peter ))",
                        "<rdf:RDF xml:base=\"http://example.org/\" xmlns=\"http://example.org/\" xmlns:owl=\"http://www.w3.org/2002/07/owl#\" xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"><owl:Ontology/><owl:Class rdf:about=\"GriffinFamilyMember\" /><owl:DatatypeProperty rdf:about=\"hasName\" /><rdf:Description rdf:about=\"GriffinFamilyMember\"><owl:hasKey rdf:parseType=\"Collection\"><rdf:Description rdf:about=\"hasName\" /></owl:hasKey></rdf:Description><rdf:Description rdf:about=\"Peter\"><hasName>Peter</hasName><rdf:type rdf:resource=\"GriffinFamilyMember\" /></rdf:Description><rdf:Description rdf:about=\"Peter_Griffin\"><hasName>Peter</hasName><rdf:type rdf:resource=\"GriffinFamilyMember\" /></rdf:Description><rdf:Description rdf:about=\"StPeter\"><hasName>Peter</hasName></rdf:Description></rdf:RDF>",
                        "Prefix( :=<http://example.org/> ) Ontology( Declaration( Class( :GriffinFamilyMember ) ) Declaration( DataProperty( :hasName ) ) HasKey( :GriffinFamilyMember () ( :hasName ) ) DataPropertyAssertion( :hasName :Peter \"Peter\" ) ClassAssertion( :GriffinFamilyMember :Peter ) DataPropertyAssertion( :hasName :Peter_Griffin \"Peter\" ) ClassAssertion( :GriffinFamilyMember :Peter_Griffin ) DataPropertyAssertion( :hasName :StPeter \"Peter\" ))",
                        "<rdf:RDF xml:base=\"http://example.org/\" xmlns=\"http://example.org/\" xmlns:owl=\"http://www.w3.org/2002/07/owl#\" xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"><owl:Ontology/><owl:Class rdf:about=\"GriffinFamilyMember\" /><owl:DatatypeProperty rdf:about=\"hasName\" /><rdf:Description rdf:about=\"GriffinFamilyMember\"><owl:hasKey rdf:parseType=\"Collection\"><rdf:Description rdf:about=\"hasName\" /></owl:hasKey></rdf:Description><rdf:Description rdf:about=\"Peter\"><hasName>Peter</hasName><rdf:type rdf:resource=\"GriffinFamilyMember\" /></rdf:Description><rdf:Description rdf:about=\"Peter_Griffin\"><hasName>Peter</hasName><rdf:type rdf:resource=\"GriffinFamilyMember\" /></rdf:Description><rdf:Description rdf:about=\"StPeter\"><hasName>Peter</hasName></rdf:Description></rdf:RDF>",
                        "Prefix( :=<http://example.org/> ) Ontology( Declaration( Class( :GriffinFamilyMember ) ) Declaration( DataProperty( :hasName ) ) HasKey( :GriffinFamilyMember () ( :hasName ) ) DataPropertyAssertion( :hasName :Peter \"Peter\" ) ClassAssertion( :GriffinFamilyMember :Peter ) DataPropertyAssertion( :hasName :Peter_Griffin \"Peter\" ) ClassAssertion( :GriffinFamilyMember :Peter_Griffin ) DataPropertyAssertion( :hasName :StPeter \"Peter\" ))",
                        "<rdf:RDF xml:base=\"http://example.org/\" xmlns=\"http://example.org/\" xmlns:owl=\"http://www.w3.org/2002/07/owl#\" xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"><owl:Ontology/><owl:Class rdf:about=\"GriffinFamilyMember\" /><owl:DatatypeProperty rdf:about=\"hasName\" /><rdf:Description rdf:about=\"GriffinFamilyMember\"><owl:hasKey rdf:parseType=\"Collection\"><rdf:Description rdf:about=\"hasName\" /></owl:hasKey></rdf:Description><rdf:Description rdf:about=\"Peter\"><hasName>Peter</hasName><hasName>Kichwa-Tembo</hasName><rdf:type rdf:resource=\"GriffinFamilyMember\" /></rdf:Description><rdf:Description rdf:about=\"hasName\"><rdf:type rdf:resource=\"http://www.w3.org/2002/07/owl#FunctionalProperty\" /></rdf:Description></rdf:RDF>",
                        "Prefix( :=<http://example.org/> ) Ontology( Declaration( Class( :GriffinFamilyMember ) ) Declaration( DataProperty( :hasName ) ) HasKey( :GriffinFamilyMember () ( :hasName ) ) DataPropertyAssertion( :hasName :Peter \"Peter\" ) DataPropertyAssertion( :hasName :Peter \"Kichwa-Tembo\" ) ClassAssertion( :GriffinFamilyMember :Peter ) FunctionalDataProperty( :hasName ))",
                        "Prefix( xsd:=<http://www.w3.org/2001/XMLSchema#> ) Prefix( :=<http://example.org/> ) Ontology( Declaration( DataProperty( :hasAge ) ) NegativeDataPropertyAssertion( :hasAge :Meg \"5\"^^xsd:integer ) DataPropertyAssertion( :hasAge :Meg \"5\"^^xsd:integer ))",
                        "Prefix( :=<http://example.org/> ) Ontology( Declaration( ObjectProperty( :hasSon ) ) NegativeObjectPropertyAssertion( :hasSon :Peter :Meg ) ObjectPropertyAssertion( :hasSon :Peter :Meg ))",
                        "<rdf:RDF xml:base=\"http://example.org/\" xmlns=\"http://example.org/\" xmlns:owl=\"http://www.w3.org/2002/07/owl#\" xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"><owl:Ontology/><owl:ObjectProperty rdf:about=\"hasMother\" /><owl:ObjectProperty rdf:about=\"hasSister\" /><owl:ObjectProperty rdf:about=\"hasAunt\" /><rdf:Description rdf:about=\"hasAunt\"><owl:propertyChainAxiom rdf:parseType=\"Collection\"><rdf:Description rdf:about=\"hasMother\" /><rdf:Description rdf:about=\"hasSister\" /></owl:propertyChainAxiom></rdf:Description><rdf:Description rdf:about=\"Stewie\"><hasMother rdf:resource=\"Lois\" /></rdf:Description><rdf:Description rdf:about=\"Lois\"><hasSister rdf:resource=\"Carol\" /></rdf:Description></rdf:RDF>",
                        "Prefix( :=<http://example.org/> ) Ontology( Declaration( ObjectProperty( :hasMother ) ) Declaration( ObjectProperty( :hasSister ) ) Declaration( ObjectProperty( :hasAunt ) ) SubObjectPropertyOf( ObjectPropertyChain( :hasMother :hasSister ) :hasAunt ) ObjectPropertyAssertion( :hasMother :Stewie :Lois ) ObjectPropertyAssertion( :hasSister :Lois :Carol ))",
                        "<rdf:RDF xml:base=\"urn:test\" xmlns=\"urn:test#\" xmlns:owl=\"http://www.w3.org/2002/07/owl#\" xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\" xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"><owl:Ontology/><rdf:Description rdf:about=\"urn:test#p\"><owl:propertyChainAxiom rdf:parseType=\"Collection\"><owl:ObjectProperty rdf:about=\"urn:test#p\"/><owl:ObjectProperty rdf:about=\"urn:test#q\"/></owl:propertyChainAxiom></rdf:Description><rdf:Description rdf:about=\"urn:test#a\"><p rdf:resource=\"urn:test#b\"/></rdf:Description><rdf:Description rdf:about=\"urn:test#b\"><q rdf:resource=\"urn:test#c\"/></rdf:Description></rdf:RDF>",
                        "<rdf:RDF xml:base=\"urn:test\" xmlns=\"urn:test#\" xmlns:owl=\"http://www.w3.org/2002/07/owl#\" xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\" xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"><owl:Ontology/><rdf:Description rdf:about=\"urn:test#p\"><owl:propertyChainAxiom rdf:parseType=\"Collection\"><owl:ObjectProperty rdf:about=\"urn:test#p\"/><owl:ObjectProperty rdf:about=\"urn:test#q\"/></owl:propertyChainAxiom></rdf:Description><rdf:Description rdf:about=\"urn:test#a\"><q rdf:resource=\"urn:test#b\"/></rdf:Description><rdf:Description rdf:about=\"urn:test#b\"><q rdf:resource=\"urn:test#c\"/></rdf:Description></rdf:RDF>");
    }
}
