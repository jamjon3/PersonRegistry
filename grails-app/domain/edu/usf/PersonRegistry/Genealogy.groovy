package edu.usf.PersonRegistry

class Genealogy {
    String nickname
    String realname
    String relationship
    static constraints = {
        relationship(inList:['root','multi','match','akin'],blank:false,nullable:false,unique: ['realname', 'nickname'])
        nickname(blank:false,nullable:false,unique: ['realname', 'relationship'])
        realname(blank:false,nullable:false,unique: ['nickname', 'relationship'])
        
    }
}
