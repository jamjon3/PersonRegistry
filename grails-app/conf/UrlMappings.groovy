class UrlMappings {

	static mappings = {
                "/person"(controller:"person",parseRequest: true){ 
                    action = [GET:"error", PUT:"addPerson", DELETE:"error", POST:"error"] 
                } 
                "/identifierType/$name"(controller:"identifier",parseRequest: true){ 
                    action = [GET:"getIdentifierType", PUT:"addIdentifierType", DELETE:"error", POST:"error"] 
                } 
                "/name/$personId"(controller:"identifier",parseRequest: true){ 
                    action = [GET:"getNames", PUT:"addName", DELETE:"error", POST:"error"] 
                } 
                "/source/$name"(controller:"source",parseRequest: true){ 
                    action = [GET:"getSource", PUT:"addSource", DELETE:"error", POST:"error"] 
                } 
                "/genealogy/data/download"(controller:"genealogy",action: "exportGenealogy",parseRequest: true)
                "/genealogy/data/upload"(controller:"genealogy",parseRequest: true){ 
                    action = [GET:"error", PUT:"importGenealogy", DELETE:"error", POST:"error"] 
                }

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
