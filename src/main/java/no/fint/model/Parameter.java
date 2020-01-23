package no.fint.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ADContextUser",
        "Recno",
        "ContactReferenceNumber",
        "CaseNumber",
        "ExternalId",
        "Title",
        "MaxReturnedCases",
        "ArchiveCode",
        "ProjectNumber",
        "CategoryCode",
        "IncludeReferringCases",
        "IncludeReferringDocuments",
        "IncludeCaseContacts",
        "IncludeCaseEstates",
        "IncludeAccessMatrixRowPermissions",
        "AdditionalFields",
        "Page",
        "LastDate",
        "OnlyPublicInfo",
        "SortCriterion",
        "IncludeCustomFields",
        "ContactExternalId",
        "DateCriteria",
        "ContactRecnos",
        "CaseType",
        "SubArchive",
        "UnofficialTitle",
        "AdditionalListFields",
        "ProgressPlanId",
        "IncludeDocuments",
        "IncludeFiles",
        "ArchiveCodes",
        "IncludeProgressPlan",
        "IncludeSubjectSpecificMetaData"
})
public class Parameter {

    @JsonProperty("ADContextUser")
    public String aDContextUser;
    @JsonProperty("Recno")
    public Integer recno;
    @JsonProperty("ContactReferenceNumber")
    public String contactReferenceNumber;
    @JsonProperty("CaseNumber")
    public String caseNumber;
    @JsonProperty("ExternalId")
    public ExternalId externalId;
    @JsonProperty("Title")
    public String title;
    @JsonProperty("MaxReturnedCases")
    public Integer maxReturnedCases;
    @JsonProperty("ArchiveCode")
    public String archiveCode;
    @JsonProperty("ProjectNumber")
    public String projectNumber;
    @JsonProperty("CategoryCode")
    public String categoryCode;
    @JsonProperty("IncludeReferringCases")
    public Boolean includeReferringCases;
    @JsonProperty("IncludeReferringDocuments")
    public Boolean includeReferringDocuments;
    @JsonProperty("IncludeCaseContacts")
    public Boolean includeCaseContacts;
    @JsonProperty("IncludeCaseEstates")
    public Boolean includeCaseEstates;
    @JsonProperty("IncludeAccessMatrixRowPermissions")
    public Boolean includeAccessMatrixRowPermissions;
    @JsonProperty("AdditionalFields")
    public List<AdditionalField> additionalFields = null;
    @JsonProperty("Page")
    public Integer page;
    @JsonProperty("LastDate")
    public String lastDate;
    @JsonProperty("OnlyPublicInfo")
    public Boolean onlyPublicInfo;
    @JsonProperty("SortCriterion")
    public String sortCriterion;
    @JsonProperty("IncludeCustomFields")
    public Boolean includeCustomFields;
    @JsonProperty("ContactExternalId")
    public String contactExternalId;
    @JsonProperty("DateCriteria")
    public List<DateCriterium> dateCriteria = null;
    @JsonProperty("ContactRecnos")
    public List<Integer> contactRecnos = null;
    @JsonProperty("CaseType")
    public String caseType;
    @JsonProperty("SubArchive")
    public String subArchive;
    @JsonProperty("UnofficialTitle")
    public String unofficialTitle;
    @JsonProperty("AdditionalListFields")
    public List<AdditionalListField> additionalListFields = null;
    @JsonProperty("ProgressPlanId")
    public String progressPlanId;
    @JsonProperty("IncludeDocuments")
    public Boolean includeDocuments;
    @JsonProperty("IncludeFiles")
    public Boolean includeFiles;
    @JsonProperty("ArchiveCodes")
    public List<String> archiveCodes = null;
    @JsonProperty("IncludeProgressPlan")
    public Boolean includeProgressPlan;
    @JsonProperty("IncludeSubjectSpecificMetaData")
    public Boolean includeSubjectSpecificMetaData;

}