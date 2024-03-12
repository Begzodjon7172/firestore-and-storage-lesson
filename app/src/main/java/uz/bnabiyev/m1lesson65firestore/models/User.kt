package uz.bnabiyev.m1lesson65firestore.models

class User {
    var id: Int? = null
    var name: String? = null
    var email: String? = null
    var age: Int? = null

    constructor(id: Int?, name: String?, email: String?, age: Int?) {
        this.id = id
        this.name = name
        this.email = email
        this.age = age
    }

    constructor()

    override fun toString(): String {
        return "User(id=$id, name=$name, email=$email, age=$age)"
    }


}