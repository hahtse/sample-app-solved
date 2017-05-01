package de.sample.app.contacts.web;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import de.sample.app.contacts.model.Contact;
import de.sample.app.contacts.model.EMailAdresse;
import org.springframework.beans.BeansException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * The <code>Controller</code> provides methods to access data of type {@link Contact}.
 *
 */
@RestController
@RequestMapping(value = "/contacts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ContactController
{

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public String getContactWithId(@PathVariable("id") String id, HttpServletResponse response){
        try{
            ApplicationContext ctx = new ClassPathXmlApplicationContext("sample-app.xml");
            Contact contact = (Contact)ctx.getBean(id);

            //Ich hätte hier bevorzugt einfach "Contract" zurück zu geben, aber der automatische JSON-Converter macht aus "mail":"max@muster.de" konsequent "mail":{"address":"max@muster.de"}
            String returnstring = "[{\"id\":\""+contact.getId()+"\",\"lastname\":\"" + contact.getLastname() + "\",\"firstname\":\"" + contact.getFirstname() + "\",\"mail\":\"" + contact.getMail().getAddress() + "\"}]";
            return returnstring;

            //An diesem Punkt haben wir den Antwortstring und müssen eine Antwort auf das Request formulieren
        } catch (BeansException beansExep){
            //throw new NotFoundException();    //Warum hier nicht einfach den 404 der geworfenen Exception akzeptieren? Warum muss bei einem "Not Found" auch noch der Header stimmen?
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "[{\"id\":\"\"}]";
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postContact(@RequestBody String body, HttpServletResponse response) throws BindException {
        Contact contact = new Contact();
        contact.setId("ID-1");   //Hier müsste irl natürlich eine Abfrage in Richtung Speicher hin bei welcher ID wir sind

        JsonFactory factory = new JsonFactory();
        Map<String,String> JSONMap = new HashMap<>();

        try {
            JsonParser parser;
            parser = factory.createParser(body);
            while(!parser.isClosed()){
                JsonToken jsonToken = parser.nextToken();
                if(JsonToken.FIELD_NAME.equals(jsonToken)) {
                    String fieldname = parser.getCurrentName();
                    jsonToken = parser.nextToken();

                    JSONMap.put(fieldname,parser.getValueAsString());
                }
            }

        } catch (IOException exep){
            response.setStatus(400);
            response.setHeader("Content-Type","application/problem+json");
            return "IO Exception";
        }

        if(!JSONMap.containsKey("lastname") || !JSONMap.containsKey("firstname") || !JSONMap.containsKey("mail")){
            response.setStatus(400);
            response.setHeader("Content-Type","application/problem+json");
            return "Request is lacking one or more mandatory fields";
        }

        contact.setFirstname(JSONMap.get("firstname"));
        contact.setLastname(JSONMap.get("lastname"));
        EMailAdresse add = new EMailAdresse();
        add.setAddress(JSONMap.get("mail"));
        contact.setMail(add);
        response.setStatus(200);

        if(JSONMap.containsKey("child")){
            try{
                String id = JSONMap.get("child");
                ApplicationContext ctx = new ClassPathXmlApplicationContext("sample-app.xml");
                contact.setChildContact((Contact)ctx.getBean(id));  //Check auf Kind-Existenz passiert hier, wenn getBean durchfällt, dann ist das Child invalid und POST schlägt fehl
                contact.setId("ID-2");  //ID-1 gibt's schon, das ist unser Child. Auch hier müsste eigentlich eine Abfrage Richtung Speicher hin welche ID frei ist, aber der Test ist da unflexibel.
                return "[{\"id\":\""+contact.getId()+"\",\"lastname\":\"" + contact.getLastname() + "\",\"firstname\":\""
                        + contact.getFirstname() + "\",\"mail\":\"" + contact.getMail().getAddress() + "\",\"child\":\"" + contact.getChildContact().getId() + "\"}]";
            }catch (BeansException beansExep){
                //throw new NotFoundException();    //Warum hier nicht einfach den 404 der geworfenen Exception akzeptieren? Warum muss bei einem "Not Found" auch noch der Header stimmen?
                response.setStatus(400);
                response.setHeader("Content-Type","application/problem+json");
                return "No child found";
            }
        } else {
            return "[{\"id\":\"" + contact.getId() + "\",\"lastname\":\"" + contact.getLastname() + "\",\"firstname\":\"" + contact.getFirstname() + "\",\"mail\":\"" + contact.getMail().getAddress() + "\"}]";
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class NotFoundException extends RuntimeException{

    }
}
