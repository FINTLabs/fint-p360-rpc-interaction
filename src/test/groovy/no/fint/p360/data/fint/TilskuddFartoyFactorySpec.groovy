package no.fint.p360.data.fint

import no.fint.model.resource.administrasjon.arkiv.JournalpostResource
import no.fint.p360.data.testutils.P360ObjectFactory
import no.fint.p360.rpc.TitleFormats
import no.fint.p360.rpc.data.kulturminne.TilskuddFartoyFactory
import no.fint.p360.rpc.data.noark.common.NoarkFactory
import no.fint.p360.rpc.data.noark.journalpost.JournalpostFactory
import no.fint.p360.rpc.data.noark.part.PartFactory
import no.fint.p360.rpc.p360Service.DocumentService
import no.fint.p360.rpc.repository.KodeverkRepository
import no.fint.p360.rpc.service.TitleService
import no.p360.model.DocumentService.Document__1
import spock.lang.Specification

class TilskuddFartoyFactorySpec extends Specification {

    private TilskuddFartoyFactory tilskuddFartoyFactory
    private JournalpostFactory journalpostFactory
    private DocumentService documentService
    private P360ObjectFactory p360ObjectFactory
    private NoarkFactory noarkFactory
    private KodeverkRepository kodeverkRepository
    private PartFactory partFactory
    private TitleService titleService

    void setup() {
        titleService = new TitleService(new TitleFormats(format: [
                'tilskuddfartoy': '${kallesignal} - ${fartoyNavn} - Tilskudd - ${kulturminneId} - ${soknadsnummer.identifikatorverdi}'
        ]))
        documentService = Mock()
        kodeverkRepository = Mock()
        journalpostFactory = Mock()
        partFactory = Mock()
        noarkFactory = new NoarkFactory(
                documentService: documentService,
                journalpostFactory: journalpostFactory,
                partFactory:  partFactory,
                kodeverkRepository: kodeverkRepository,
                titleService: titleService
        )
        tilskuddFartoyFactory = new TilskuddFartoyFactory(
                noarkFactory: noarkFactory
        )
        p360ObjectFactory = new P360ObjectFactory()
    }

    def "Convert from P360 case to Tilskudd fartoy"() {
        given:
        def caseResult = p360ObjectFactory.newP360Case()


        when:
        def fint = tilskuddFartoyFactory.toFintResource(caseResult)

        then:
        1 * documentService.getDocumentBySystemId(_ as String) >> new Document__1()
        1 * journalpostFactory.toFintResource(_ as Document__1) >> new JournalpostResource()
        1 * kodeverkRepository.getSaksstatus() >> []
        fint
        fint.getMappeId().identifikatorverdi == "19/12345"
    }

    def "Convert list of P360 cases to Tilskudd fartoy"() {

        when:
        def fartoys = tilskuddFartoyFactory.toFintResourceList(p360ObjectFactory.newP360CaseList())
        def result = fartoys.size()

        then:
        result == 2
        2 * kodeverkRepository.getSaksstatus() >> []
    }
}
