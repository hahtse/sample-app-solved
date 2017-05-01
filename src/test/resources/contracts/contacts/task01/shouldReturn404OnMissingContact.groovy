package contacts.task01


org.springframework.cloud.contract.spec.Contract.make {
    request {
        description("""
                Szenario:
                    Ein nichtexistierender Contact soll abgefragt werden.
                given:
                    Id eines nicht vorhandenen Contacts.
                when:
                    der Contact abgefragt wird.
                then:
                    wird ein 404 zurückgegeben.
                """)
        method 'GET'
        url '/contacts/ID-2'
        body('')
        headers {
            header('Content-Type', 'application/json;charset=UTF-8')
        }
    }
    response {
        status 404
        body([])
        headers {
            header('Content-Type': value(
                producer('application/json;charset=UTF-8'),
                consumer('application/json;charset=UTF-8'))
            )
        }
    }
}
