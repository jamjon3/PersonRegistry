package edu.usf.PersonRegistry

import grails.converters.*

class NameController {
    def nameService
    def addName() {
        withFormat {
            html {
                return nameService.addName(params.name,params.personId,params.sor)
            }
            xml {
                render nameService.addName(params.name,params.personId,params.sor) as XML
            }
            json {
                JSON.use("deep") { render nameService.addName(params.name,params.personId,params.sor) as JSON }
            }
        }                            
    }
    
    def getNames(String personId,String sor) {
        withFormat {
            html {
                return nameService.getNames(params.personId,params.sor)
            }
            xml {
                render nameService.getNames(params.personId,params.sor) as XML
            }
            json {
                JSON.use("deep") { render nameService.getNames(params.personId,params.sor) as JSON }
            }
        }                            
    }
}
