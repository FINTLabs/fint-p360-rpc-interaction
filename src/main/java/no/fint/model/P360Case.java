package no.fint.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Recno",
        "CaseNumber",
        "ExternalId",
        "Title",
        "Date",
        "Status",
        "AccessCodeDescription",
        "AccessCodeCode",
        "AccessGroup",
        "Paragraph",
        "ResponsibleEnterprise",
        "ResponsibleEnterpriseName",
        "ResponsiblePerson",
        "ResponsiblePersonName",
        "ArchiveCodes",
        "Documents",
        "ReferringCases",
        "ReferringDocuments",
        "UnofficialTitle",
        "CreatedDate",
        "Notes",
        "CaseTypeCode",
        "CaseTypeDescription",
        "Contacts",
        "ProjectRecno",
        "ProjectName",
        "SubArchive",
        "SubArchiveCode",
        "CaseEstates",
        "CaseRowPermissions",
        "CustomFields",
        "LastChangedDate",
        "ProgressPlan",
        "SubjectSpecificMetaData",
        "SubjectSpecificMetaDataNamespace",
        "URL"
})
public class P360Case {

    @JsonProperty("Recno")
    public Integer recno;
    @JsonProperty("CaseNumber")
    public String caseNumber;
    @JsonProperty("ExternalId")
    public ExternalId externalId;
    @JsonProperty("Title")
    public String title;
    @JsonProperty("Date")
    public String date;
    @JsonProperty("Status")
    public String status;
    @JsonProperty("AccessCodeDescription")
    public String accessCodeDescription;
    @JsonProperty("AccessCodeCode")
    public String accessCodeCode;
    @JsonProperty("AccessGroup")
    public String accessGroup;
    @JsonProperty("Paragraph")
    public String paragraph;
    @JsonProperty("ResponsibleEnterprise")
    public ResponsibleEnterprise responsibleEnterprise;
    @JsonProperty("ResponsibleEnterpriseName")
    public String responsibleEnterpriseName;
    @JsonProperty("ResponsiblePerson")
    public ResponsiblePerson responsiblePerson;
    @JsonProperty("ResponsiblePersonName")
    public String responsiblePersonName;
    @JsonProperty("ArchiveCodes")
    public List<ArchiveCode> archiveCodes;
    @JsonProperty("Documents")
    public List<Document> documents;
    @JsonProperty("ReferringCases")
    public ReferringCases referringCases;
    @JsonProperty("ReferringDocuments")
    public ReferringDocuments referringDocuments;
    @JsonProperty("UnofficialTitle")
    public String unofficialTitle;
    @JsonProperty("CreatedDate")
    public String createdDate;
    @JsonProperty("Notes")
    public String notes;
    @JsonProperty("CaseTypeCode")
    public String caseTypeCode;
    @JsonProperty("CaseTypeDescription")
    public String caseTypeDescription;
    @JsonProperty("Contacts")
    public Contacts contacts;
    @JsonProperty("ProjectRecno")
    public String projectRecno;
    @JsonProperty("ProjectName")
    public String projectName;
    @JsonProperty("SubArchive")
    public String subArchive;
    @JsonProperty("SubArchiveCode")
    public String subArchiveCode;
    @JsonProperty("CaseEstates")
    public CaseEstates caseEstates;
    @JsonProperty("CaseRowPermissions")
    public String caseRowPermissions;
    @JsonProperty("CustomFields")
    public CustomFields customFields;
    @JsonProperty("LastChangedDate")
    public String lastChangedDate;
    @JsonProperty("ProgressPlan")
    public ProgressPlan progressPlan;
    @JsonProperty("SubjectSpecificMetaData")
    public String subjectSpecificMetaData;
    @JsonProperty("SubjectSpecificMetaDataNamespace")
    public String subjectSpecificMetaDataNamespace;
    @JsonProperty("URL")
    public String uRL;

}