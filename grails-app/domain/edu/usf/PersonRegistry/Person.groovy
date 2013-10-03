package edu.usf.PersonRegistry

class Person {
    String id
    static hasMany = [
        identifiers:Identifier,
        names:Name,
        nickNames:NickName
    ]
    static mapping = {
        id generator: 'uuid'
    }
    static constraints = {
    }
}
