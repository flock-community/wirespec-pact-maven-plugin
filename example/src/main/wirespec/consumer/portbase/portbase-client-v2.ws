type Date /^((19|20)[0-9]{2}-[0-9]{2}-[0-9]{2})$/g

type PortbaseMutation{
    index: Integer,
    timestamp: String,
    triggeredByRecipient: Boolean,
    before: PortbaseDeclaration,
    after: PortbaseDeclaration
}

type PortbaseDeclaration {
     crn: String,
     declarationStatuses: {DeclarationStatus},
     vessel: PcsVessel,
     owner: PcsOwner,
     declarant: PcsDeclarant,
     healthDeclaration: PcsHealthDeclaration,
     cancelled: Boolean
}

enum DeclarationStatus {
    ACCEPTED,
    DECLARED,
    REJECTED
}

type PcsVessel {
    name: String
}

type PcsOwner{
    portAuthorityId: String
}

type PcsDeclarant {
    fullName: String,
    emailAddress: String,
    phoneNumber: String
}

/**
 * The summary of the Maritime Health Declaration information of the port visit
 */
type PcsHealthDeclaration {
    vesselMaster: PcsVesselContact?,
    vesselSurgeon: PcsVesselContact?,
    additionalContact: PcsVesselContact?,
    arrivingFrom: PcsPort?,
    sailingTo: PcsPort?,
    portsFromCommencementOfVoyage: PcsPortOfVoyage[]?,
    healthQuestions: PcsHealthQuestions?,
    generalQuestions: PcsGeneralQuestions?,
    numberOfCrew: Integer?,
    numberOfPassengers: Integer?,
    recoveredIllOrDeadPeople: PcsRecoveredIllOrDeadPerson[]?,
    peopleJoinedVessel: PcsPersonJoinedVessel[]?,
    remarks: String?
}

/** date is iso-8601 */
type PcsVesselContact{
    name: String?,
    email: String?,
    phoneNumber: String?,
    thruthfullyDeclared: Boolean?,
    date: Date?
}
type PcsPort{
    name: String?,
    locationUnCode:String?,
    countryUnCode: String?,
    euPort:Boolean?
}

type PcsPortOfVoyage{
    port: PcsPort?,
    departureDate: Date?
}

type PcsHealthQuestions{
    nonAccidentalDeathOfPersonOnBoard: PcsQuestion?,
    caseOfInfectiousDiseaseOnBoard: PcsQuestion?,
    illPassengersOnBoardIsGreaterThanNormal: PcsQuestion?,
    illPersonOnBoardNow: PcsQuestion?,
    awareOfConditionOnBoardWhichMayLeadToInfection: PcsQuestion?,
    sanitaryMeasuresApplied: PcsQuestion?,
    stowawaysOnBoard: PcsQuestion?,
    sickAnimalsOnBoard: PcsQuestion?,
    medicalPractitionerConsulted: PcsQuestion?
}
type PcsGeneralQuestions{
    hasSanitationCertificateOnBoard: PcsQuestion?,
    vesselVisitedAffectedArea: PcsQuestion?,
    reinspectionRequired: PcsQuestion?
}
type PcsRecoveredIllOrDeadPerson{
    name: String?,
    gender: GenderEnum?,
    nationality: String?,
    dateOfBirth: Date?,
    jobTitleOrRank: String?,
    joinedVesselAtPort: PcsPort?,
    joinedVesselAtDate: Date?,
    natureOfIllness: String?,
    dateOnsetOfSymptoms: Date?,
    reportedToMedicalOfficer: Boolean?,
    disposalOfCase: String?,
    treatmentGiven: String?,
    comments: String?

}
type PcsPersonJoinedVessel{
    name: String?,
    joinedVesselAtPorts: PcsPort[]?
}

type PcsQuestion{
    answer: Boolean?,
    additionalInfo: PcsAdditionalInfo?
}
enum GenderEnum{
    NOT_KNOWN,
    MALE,
    FEMALE
}
type PcsAdditionalInfo{
    date: Date?,
    `type`: String?,
    issuedAt: String?,
    port: String?,
    joinedAt: String?,
    place: String?,
    totalNumberOfDeaths: Integer?,
    numberOfIllPeople: Integer?
}
