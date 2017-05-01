package contacts.task01


org.springframework.cloud.contract.spec.Contract.make {
    request {
        description("""
                Szenario:
                    Ein Contact wird erfolgreich über seine ID abgefragt.
                given:
                    ID des Kontakts ist bekannt.
                when:
                    Der Contact abgefragt wird.
                then:
                    Geben wir den Contact zurück.
                """)
        method 'GET'
        url '/contacts/ID-1'
        body('')
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
