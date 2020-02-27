package no.fint.p360.data.testutils;

import no.p360.model.CaseService.*;

import java.util.ArrayList;
import java.util.List;

public class P360ObjectFactory {

    public Case newP360Case() {
        Case caseResult = new Case();
        caseResult.setCaseNumber("19/12345");
        caseResult.setRecno(12345);
        caseResult.setDate("2020-01-22T14:07:14");
        caseResult.setCreatedDate("2020-01-22T14:07:14");
        caseResult.setTitle("Tilskudd - AWQR - Ternen - 232291-0 - 35 - FINT Test #1");
        caseResult.setUnofficialTitle("Tilskudd - AWQR - Ternen - 232291-0 - 35 - FINT Test #1");
        caseResult.setNotes("notes");
        caseResult.setArchiveCodes(new ArrayList<ArchiveCode__1>());
        ExternalId__2 externalId__2 = new ExternalId__2();
        externalId__2.setId("35");
        caseResult.setExternalId(externalId__2);


        caseResult.setDocuments(newListOfDocuments());
        caseResult.setStatus("S");
        caseResult.setContacts(newListOfContacts());
        caseResult.setResponsibleEnterprise(newResponsibleEnterprise());
        caseResult.setResponsiblePerson(newResponsiblePerson());

        return caseResult;
    }

    private List<Contact__1> newListOfContacts() {
        ArrayList<Contact__1> contacts = new ArrayList<>();
        contacts.add(newCaseContact());
        return contacts;
    }

    private Contact__1 newCaseContact() {
        return new Contact__1();
    }

        private ResponsiblePerson newResponsiblePerson() {
            ResponsiblePerson responsiblePerson = new ResponsiblePerson();
            responsiblePerson.setRecno(23456);
            responsiblePerson.setName("Arkivaren");
            return responsiblePerson;
        }

        private ResponsibleEnterprise newResponsibleEnterprise() {
            ResponsibleEnterprise responsibleEnterprise = new ResponsibleEnterprise();
            responsibleEnterprise.setRecno(123456);
            responsibleEnterprise.setName("Arkivet");
            return responsibleEnterprise;
        }

        public List<Case> newP360CaseList() {
            List<Case> cases = new ArrayList<>();
            cases.add(newP360Case());
            cases.add(newP360Case());

            return cases;
        }

    public Document newCaseDocument() {
        Document caseDocument = new Document();
        caseDocument.setRecno(123);

        return caseDocument;
    }

    public List<Document> newListOfDocuments() {
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(newCaseDocument());

        return documents;
    }
}
