package contacts.task03

/**
 * Created by HC on 01.05.2017.
 */
org.springframework.cloud.contract.spec.Contract.make {
    request {
        description("""
                Szenario:
                    Ein Contact wird mit Pflichtfeld angelegt
                given:
                    Ein Contact mit Pflichtfeld ist definiert, Contact hat ein Child mit angegeben, das über seine ID identifiziert ist..
                when: der Contact angelegt werden soll
                then: wird ein Fehler mit Status 400 und optional einer Fehlerbeschreibung
                    zurückgegeben.
                """)
        method 'POST'
        url '/contacts'
        body([
                firstname: 'Melanie',
                lastname: "Muster",
                mail: "melanie@muster.de",
                child: "ID-3"
        ])
        headers {
            header('Content-Type', 'application/json;charset=UTF-8')
        }
    }
    response {
        status 200
        body([])
        headers {
            header('Content-Type': value(
                    producer('application/problem+json'),
                    consumer('application/problem+json'))
            )
        }
    }
}