package de.sample.app.contacts.model;

import javax.validation.constraints.NotNull;

/**
 * Used Lombok, this file is delomboked.
 */
public class Contact
{

    private String id;

    // This field is mandatory!
    private String lastname;

    private String firstname;

    private EMailAdresse mail;

    //TODO Sicherstellen, dass das Child existiert
    //Machen wir über Spring. Das hier ist die Injektion, das andere machen wir über die xml
    private Contact childContact;

    public Contact(String id, String lastname, String firstname, EMailAdresse mail)
    {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.mail = mail;
    }

    public Contact(String id, String lastname, String firstname, EMailAdresse mail, Contact childContact){
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.mail = mail;
        this.childContact = childContact;
    }
    public Contact()
    {
    }

    public static ContactBuilder builder()
    {
        return new ContactBuilder();
    }

    public String getId()
    {
        return this.id;
    }

    public String getLastname()
    {
        return this.lastname;
    }

    public String getFirstname()
    {
        return this.firstname;
    }

    public EMailAdresse getMail()
    {
        return this.mail;
    }

    public Contact getChildContact() {
        return childContact;
    }

    public void setChildContact(Contact childContact) {
        this.childContact = childContact;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public void setMail(EMailAdresse mail)
    {
        this.mail = mail;
    }

    public boolean equals(Object o)
    {
        if (o == this)
            return true;
        if (!(o instanceof Contact))
            return false;
        final Contact other = (Contact) o;
        if (!other.canEqual((Object) this))
            return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id))
            return false;
        final Object this$lastname = this.getLastname();
        final Object other$lastname = other.getLastname();
        if (this$lastname == null ? other$lastname != null : !this$lastname.equals(other$lastname))
            return false;
        final Object this$firstname = this.getFirstname();
        final Object other$firstname = other.getFirstname();
        if (this$firstname == null ? other$firstname != null : !this$firstname.equals(other$firstname))
            return false;
        final Object this$mail = this.getMail();
        final Object other$mail = other.getMail();
        if (this$mail == null ? other$mail != null : !this$mail.equals(other$mail))
            return false;
        if(!this.getChildContact().equals(other.getChildContact())) //Children müssen auch übereinstimmen...das kann auch gerne tief runter gehen
            return false;
        return true;
    }

    public int hashCode()
    {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $lastname = this.getLastname();
        result = result * PRIME + ($lastname == null ? 43 : $lastname.hashCode());
        final Object $firstname = this.getFirstname();
        result = result * PRIME + ($firstname == null ? 43 : $firstname.hashCode());
        final Object $mail = this.getMail();
        result = result * PRIME + ($mail == null ? 43 : $mail.hashCode());
        final Object $child = this.getChildContact();
        result = result * PRIME + ($child == null ? 43 : $child.hashCode());
        return result;
    }

    protected boolean canEqual(Object other)
    {
        return other instanceof Contact;
    }

    public String toString()
    {
        if(getChildContact() != null)
            return "de.sample.app.contacts.model.Contact(id=" + this.getId() + ", lastname=" + this.getLastname()
                + ", firstname=" + this.getFirstname() + ", mail=" + this.getMail() + ", child=" + getChildContact().getId() + ")";
        else
            return "de.sample.app.contacts.model.Contact(id=" + this.getId() + ", lastname=" + this.getLastname()
                    + ", firstname=" + this.getFirstname() + ", mail=" + this.getMail() + ")";
    }

    public static class ContactBuilder
    {
        private String id;
        private String lastname;
        private String firstname;
        private EMailAdresse mail;
        private Contact childContact;

        ContactBuilder()
        {
        }

        public Contact.ContactBuilder id(String id)
        {
            this.id = id;
            return this;
        }

        public Contact.ContactBuilder lastname(String lastname)
        {
            this.lastname = lastname;
            return this;
        }

        public Contact.ContactBuilder firstname(String firstname)
        {
            this.firstname = firstname;
            return this;
        }

        public Contact.ContactBuilder mail(EMailAdresse mail)
        {
            this.mail = mail;
            return this;
        }

        public Contact.ContactBuilder childContact(Contact childContact){
            this.childContact = childContact;
            return this;
        }

        public Contact build()
        {
            return new Contact(id, lastname, firstname, mail, childContact);
        }

        public String toString()
        {
            if(childContact != null)
                return "de.sample.app.contacts.model.Contact.ContactBuilder(id=" + this.id + ", lastname="
                    + this.lastname + ", firstname=" + this.firstname + ", mail=" + this.mail + ", child=" + childContact.getId() +")";
            else
                return "de.sample.app.contacts.model.Contact.ContactBuilder(id=" + this.id + ", lastname="
                        + this.lastname + ", firstname=" + this.firstname + ", mail=" + this.mail + ")";
        }
    }
}
