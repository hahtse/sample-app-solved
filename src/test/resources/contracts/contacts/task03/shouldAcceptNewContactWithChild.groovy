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
                then: wird ein Status 200 mit dem angelegten Contact im Body zurückgegeben.
                """)
        method 'POST'
        url '/contacts'
        body([
                firstname: 'Melanie',
                lastname: "Muster",
                mail: "melanie@muster.de",
                child: "ID-1"
        ])
        headers {
            header('Content-Type', 'application/json;charset=UTF-8')
        }
    }
    response {
        status 200
        body([
                id: "ID-2",
                firstname: "Melanie",
                lastname: "Muster",
                mail: "melanie@muster.de",
                child: "ID-1"
        ])
        headers {
            header('Content-Type': value(
                    producer('application/json;charset=UTF-8'),
                    consumer('application/json;charset=UTF-8'))
            )
        }
    }
}