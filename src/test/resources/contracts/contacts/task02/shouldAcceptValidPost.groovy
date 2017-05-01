package contacts.task02


org.springframework.cloud.contract.spec.Contract.make {
    request {
        description("""
                Szenario:
                    Ein Contact wird mit Pflichtfeld angelegt.
                given:
                    Ein Contact mit Pflichtfeld ist definiert.
                when: der Contact angelegt werden soll
                then: wird ein Status 200 mit dem angelegten Contact im Body zurückgegeben.
                """)
        method 'POST'
        url '/contacts'
        body([
                firstname: 'Max',
                lastname: "Muster",
                mail: "max@muster.de"
        ])
        headers {
            header('Content-Type', 'application/json;charset=UTF-8')
        }
    }
    response {
        status 200
        body([
            id: "ID-1",
            firstname: "Max",
            lastname: "Muster",
            mail: "max@muster.de"
        ])
        headers {
            header('Content-Type': value(
                producer('application/json;charset=UTF-8'),
                consumer('application/json;charset=UTF-8'))
            )
        }
    }
}
