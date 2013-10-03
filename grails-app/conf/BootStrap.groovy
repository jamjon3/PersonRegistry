import grails.util.GrailsNameUtils
import grails.util.GrailsUtil
import grails.converters.*
import edu.usf.PersonRegistry.Identifier
import edu.usf.PersonRegistry.IdentifierType
import edu.usf.PersonRegistry.Person
import edu.usf.PersonRegistry.Source
import javax.crypto.Cipher
import java.security.NoSuchAlgorithmException
import org.hibernate.ScrollMode
import org.jasypt.util.text.StrongTextEncryptor
import org.hibernate.ScrollableResults


class BootStrap {
    def grailsApplication
    // def quartzScheduler
    def identifierService
    def personService
    def sourceService

    def init = { servletContext ->
        
        int allowedKeyLength = 0;

        try {
          allowedKeyLength = Cipher.getMaxAllowedKeyLength("AES");
        } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
        }

        System.out.println("The allowed key length for AES is: " + allowedKeyLength);
        
        println identifierService.addIdentifierType("ssn") as JSON
        def identifierType = identifierService.getIdentifierType("ssn").identifierType
        def personId = personService.addPerson().personId
        def person = Person.get(personId)
        def sourceName = sourceService.addSource("bogus").source
        def source = Source.findByName(sourceName)
        def identifier = new Identifier(value:"12345667", person:person, source:source)
        identifierType.addToIdentifiers(identifier)
        if(!identifierType.save(failOnError:false, flush: true, insert: true, validate: true)) {
            println "'${identifierType.errors.fieldError.field}' value '${identifierType.errors.fieldError.rejectedValue}' rejected" 
        }
//        identifierType.addToIdentifiers(new Identifier(value:"12345667", person:person))
//        if(!identifierType.save(failOnError:false, flush: true, insert: true, validate: true)) {
//            println "Name value '${identifierType.errors.fieldError.rejectedValue}' rejected" 
//        }
//        if(!identifierType.save(failOnError:false, flush: true, insert: true, validate: true)) {
//            return [ error : "Name value '${identifierType.errors.fieldError.rejectedValue}' rejected" ]
//        }
        // identifierType.addToIdentifiers([value: "12345667"] as Identifier)
//        StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
//        textEncryptor.setPassword(grailsApplication.config.jasypt.password);
//        // String plainText = textEncryptor.decrypt(myEncryptedText)
//        println Identifier.createCriteria().list {
//            eq('identifierType',identifierType)            
//        }.findAll {
//            it.value == "12345667"
//        } as JSON
        
        
        
        
//        println IdentifierType.list() as JSON
        
        switch(GrailsUtil.environment){
            case "development":
                println "#### Development Mode (Start Up)"
                break
            case "test":
                println "#### Test Mode (Start Up)"
                break
            case "production":
                println "#### Production Mode (Start Up)"
                break
        }        
    }
    def destroy = {
        switch(GrailsUtil.environment){
            case "development":
                println "#### Development Mode (Shut Down)"
                break
            case "test":
                println "#### Test Mode (Shut Down)"
                break
            case "production":
                println "#### Production Mode (Shut Down)"
                break
        }                
    }
}
