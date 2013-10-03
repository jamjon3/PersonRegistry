class UrlMappings {

	static mappings = {
                "/person"(controller:"ruleSet",parseRequest: true){ 
                    action = [GET:"error", PUT:"addPerson", DELETE:"error", POST:"error"] 
                } 
                "/identifierType/$name"(controller:"ruleSet",parseRequest: true){ 
                    action = [GET:"getIdentifierType", PUT:"addIdentifierType", DELETE:"error", POST:"error"] 
                } 

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
