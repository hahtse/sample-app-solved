package contacts.task02


org.springframework.cloud.contract.spec.Contract.make {
    request {
        description("""
                Szenario:
                    Ein Contact wird ohne das Pflichtfeld angelegt.
                given:
                    Ein Contact ohne Pflichtfeld ist definiert.
                when:
                    Der Contact angelegt werden soll
                then:
                    wird ein Fehler mit Status 400 und optional einer Fehlerbeschreibung
                    zurückgegeben.
                """)
        method 'POST'
        url '/contacts'
        body([
            firstname: 'Max'
        ])
        headers {
            header('Content-Type', 'application/json;charset=UTF-8')
        }
    }
    response {
        status 400
        body([])
        headers {
            header('Content-Type': value(
                producer('application/problem+json'),
                consumer('application/problem+json'))
            )
        }
    }
}
