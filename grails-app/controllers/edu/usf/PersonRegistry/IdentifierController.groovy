package edu.usf.PersonRegistry

import grails.converters.*

class IdentifierController {
    def identifierService
    def addIdentifierType() {
        withFormat {
            html {
                return identifierService.addIdentifierType(params.name,params.validator)
            }
            xml {
                render identifierService.addIdentifierType(params.name,params.validator) as XML
            }
            json {
                JSON.use("deep") { render identifierService.addIdentifierType(params.name,params.validator) as JSON }
            }
        }                    
    }
    def getIdentifierType() {
        withFormat {
            html {
                return identifierService.getIdentifierType(params.name)
            }
            xml {
                render identifierService.getIdentifierType(params.name) as XML
            }
            json {
                JSON.use("deep") { render identifierService.getIdentifierType(params.name) as JSON }
            }
        }                    
    }
}
