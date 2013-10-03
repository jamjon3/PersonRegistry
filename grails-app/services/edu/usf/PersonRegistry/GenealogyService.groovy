package edu.usf.PersonRegistry

class GenealogyService {
    static transactional = true
    def grailsApplication
    def exportGenealogy() {
        return [
            genealogy: Genealogy.createCriteria().list(sort: 'realname', order:'desc') {
                resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                projections {
                    property('realname', 'realname')
                    property('nickname', 'nickname')
                    property('relationship','relationship')
                }
            }        
        ]
    }
    def importGenealogy(genealogy) {
        if(!!genealogy) {
            if('genealogy' in genealogy) {
                def summary = [
                    existing: 0,
                    added: 0,
                    errored: 0
                ]
                genealogy.genealogy.each { g ->
                    if(g.relationship in Genealogy.constraints.relationship.inList) {
                        def gexists = Genealogy.find(g as Genealogy)
                        if(!!!gexists) {
                            gexists = g as Genealogy
                            if(!gexists.save(failOnError:false, flush: true, insert: true, validate: true)) {
                                log.warn "'${gexists.errors.fieldError.field}' value '${gexists.errors.fieldError.rejectedValue}' rejected" 
                                summary.errored++
                            } else {
                                summary.added++
                            }                            
                        } else {
                            summary.existing++
                        }
                    } else {
                        summary.errored++
                        log.warn "relationship type is invalid"
                    }
                }
                return summary
            }
            return [ error: "Your data doesn't contain any entries"]
        }
        return [ error: "You must provide Geneological data for the import"]
    }
}
