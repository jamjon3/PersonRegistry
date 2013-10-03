package edu.usf.PersonRegistry

import grails.converters.*

class GenealogyController {
    def genealogyService
    def exportGenealogy() {
        JSON.use("deep") { 
            response.setHeader "Content-disposition", "attachment; filename=RCBackup.json"
            response.contentType = 'application/json'
            response.outputStream << (genealogyService.exportGenealogy() as JSON).toString(true)
            response.outputStream.flush()        
        }
    }
    def importGenealogy() {
        withFormat {
            html {
                return genealogyService.importGenealogy(params.geneology)
            }
            xml {
                render genealogyService.importGenealogy(params.geneology) as XML
            }
            json {
                JSON.use("deep") { render genealogyService.importGenealogy(params.geneology) as JSON }
            }
        }                    
    }
}
